package simulation;

import core.SimulationSettings;
import simulation.population.Population;

public class RoundGenerator {

	private Population population;
	private Individual[][] hallOfFame;
	
	private RoundState roundState = RoundState.FREE_FOR_ALL;
	private boolean isFirstCycle = true;
	
	private Individual[][] currentBatch;

	public RoundGenerator(Population startPopulation, int packSize) {
		this.population = startPopulation;
		this.hallOfFame = new Individual[SimulationSettings.hallOfFameSize][packSize];
	}

	public void nextBatch() {
		if(roundState == RoundState.CLASH_OF_THE_TITANS) {
		}
		if(roundState == RoundState.FREE_FOR_ALL) {			
			if(!isFirstCycle) {
				generateNextPopulationCycle();
			}
			isFirstCycle = false;
			roundState = RoundState.CLASH_OF_THE_TITANS;
		}
	}

	private void generateNextPopulationCycle() {
		
	}

	public Individual[][] getIndividuals() {
		return currentBatch;
	}

}
