package simulation;

import java.util.Arrays;

import core.SimulationSettings;
import simulation.population.Population;
import util.ArrayUtil;

public class RoundGenerator {

	private Population population;
	private Individual[] hallOfFame;
	
	private RoundState roundState = RoundState.POPULATION_BATTLE;
	private boolean isFirstCycle = true;
	
	private Individual[] currentBigPopulation;
	private Individual[] currentBatch;

	public RoundGenerator(Population startPopulation) {
		this.population = startPopulation;
		this.hallOfFame = new Individual[SimulationSettings.hallOfFameSize];
	}

	public void nextBatch() {
		if(roundState == RoundState.HALL_OF_FAME_BATTLE) {
			Arrays.sort(currentBigPopulation);
			if(isFirstCycle) {
				hallOfFame = Arrays.copyOf(currentBigPopulation, SimulationSettings.hallOfFameSize);
			}
			Individual[] currentPopulationTop = Arrays.copyOf(currentBigPopulation, SimulationSettings.hallOfFameSize);
			currentBatch = ArrayUtil.concat(hallOfFame, currentPopulationTop);
			isFirstCycle = false;
		}
		if(roundState == RoundState.POPULATION_BATTLE) {			
			if(!isFirstCycle) {
				generateNextPopulationCycle();
			}
			currentBatch = population.generateIndividuals();
			currentBigPopulation = currentBatch;
			roundState = RoundState.HALL_OF_FAME_BATTLE;
		}
	}

	private void generateNextPopulationCycle() {
		
	}

	public Individual[] getIndividuals() {
		return currentBatch;
	}

}
