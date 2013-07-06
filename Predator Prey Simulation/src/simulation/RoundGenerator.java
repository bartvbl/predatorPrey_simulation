package simulation;

import java.util.Arrays;

import core.Main;
import core.SimulationSettings;
import simulation.dna.DNAGenerator;
import simulation.evolution.Evolver;
import simulation.neural.NeuralNetwork;
import simulation.population.Population;
import util.ArrayUtil;

public class RoundGenerator {

	private Population population;
	
	private RoundState roundState = RoundState.POPULATION_BATTLE;
	private boolean isFirstCycle = true;
	private int populationSize;
	
	private Individual[] hallOfFame;
	private Individual[] currentBigPopulation;
	private Individual[] currentBatch;
	private Individual[] currentSmallPopulation;
	private int cycle = 0;

	private final RobotType type;

	public RoundGenerator(Population startPopulation, RobotType type) {
		this.population = startPopulation;
		this.populationSize = startPopulation.length;
		this.hallOfFame = new Individual[SimulationSettings.hallOfFameSize];
		this.type = type;
		currentSmallPopulation = new Individual[populationSize - SimulationSettings.hallOfFameSize];
	}

	public void nextBatch() {
		if(roundState == RoundState.POPULATION_BATTLE) {			
			Main.shouldRender = false;
			preparePopulationBattle();
		} else {
			Main.shouldRender = true;
			if(roundState == RoundState.HALL_OF_FAME_BATTLE) {			
				prepareHallOfFameBattle();
			}
		}
		System.out.println("next batch: " + currentBatch.length);
	}

	private void preparePopulationBattle() {
		if(!isFirstCycle) {
			cycle++;
			System.out.println("Completed cycle: " + cycle);
			Arrays.sort(currentBatch);
			Individual[] newHallOfFame = Arrays.copyOf(hallOfFame, SimulationSettings.hallOfFameSize);
			hallOfFame = newHallOfFame;
			System.out.println(Arrays.toString(hallOfFame));
			resetFitnessValues(hallOfFame);
			Individual[] randomlyChosenIndividuals = ArrayUtil.pickRandom(currentSmallPopulation, SimulationSettings.randomlyPickedBatchSize);
			Individual[] reproductionIndividuals = ArrayUtil.concat(hallOfFame, randomlyChosenIndividuals);
			NeuralNetwork[] reproducedNetworks = Evolver.evolve(reproductionIndividuals);
			NeuralNetwork[] newRandomNetworks;
			System.out.println("nun evolved nbetworks: " + reproducedNetworks.length + " and original population size: " + populationSize);
			if(type == RobotType.PREDATOR_RED) {				
				newRandomNetworks = DNAGenerator.generatePredatorNetworks(populationSize - reproducedNetworks.length);
			} else {
				newRandomNetworks = DNAGenerator.generatePreyNetworks(populationSize - reproducedNetworks.length);
			}
			NeuralNetwork[] nextPopulationNetworks = ArrayUtil.concat(reproducedNetworks, newRandomNetworks);
			population = new Population(nextPopulationNetworks);
		}

		currentBatch = population.generateIndividuals();
		currentBigPopulation = currentBatch;	
		roundState = RoundState.HALL_OF_FAME_BATTLE;
		
		if(isFirstCycle) {
			hallOfFame = Arrays.copyOf(currentBigPopulation, SimulationSettings.hallOfFameSize);
		}
	}

	private void prepareHallOfFameBattle() {
		Arrays.sort(currentBatch);
		Arrays.sort(currentBigPopulation);
		Individual[] currentPopulationTop = Arrays.copyOf(currentBigPopulation, SimulationSettings.hallOfFameSize);
		currentBatch = ArrayUtil.concat(hallOfFame, currentPopulationTop);
		Individual[] repeatedBatch = new Individual[0];
		for(int i = 0; i < SimulationSettings.hallOfFameBattleRepetitions; i++) {
			repeatedBatch = ArrayUtil.concat(repeatedBatch, currentBatch);
		}
		currentBatch = repeatedBatch;
		System.arraycopy(currentBigPopulation, SimulationSettings.hallOfFameSize, 
				currentSmallPopulation, 0, populationSize - SimulationSettings.hallOfFameSize);
		isFirstCycle = false;
		roundState = RoundState.POPULATION_BATTLE;
	}
	
	private void resetFitnessValues(Individual[] individuals) {
		for(Individual individual : individuals) {
			individual.resetFitness();
		}
	}

	public Individual[] getIndividuals() {
		return currentBatch;
	}

}
