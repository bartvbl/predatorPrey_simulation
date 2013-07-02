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

	public RoundGenerator(Population startPopulation) {
		this.population = startPopulation;
		this.populationSize = startPopulation.length;
		this.hallOfFame = new Individual[SimulationSettings.hallOfFameSize];
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
			Arrays.sort(currentBatch);
			Individual[] newHallOfFame = Arrays.copyOf(hallOfFame, SimulationSettings.hallOfFameSize);
			hallOfFame = newHallOfFame;
			System.out.println(Arrays.toString(hallOfFame));
			resetFitnessValues(hallOfFame);
			Individual[] randomlyChosenIndividuals = ArrayUtil.pickRandom(currentSmallPopulation, SimulationSettings.randomlyPickedBatchSize);
			Individual[] reproductionIndividuals = ArrayUtil.concat(hallOfFame, randomlyChosenIndividuals);
			NeuralNetwork[] reproducedNetworks = Evolver.evolve(reproductionIndividuals);
			NeuralNetwork[] newRandomNetworks = DNAGenerator.generatePredatorNetworks(populationSize - reproducedNetworks.length);
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
		
		Individual[] currentPopulationTop = Arrays.copyOf(currentBigPopulation, SimulationSettings.hallOfFameSize);
		currentBatch = ArrayUtil.concat(hallOfFame, currentPopulationTop);
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
