package simulation;

import simulation.population.Population;

public class RoundHandler {

	private Population predatorPopulation;
	private Population preyPopulation;

	public RoundHandler(Population startPredatorPopulation, Population startPreyPopulation) {
		this.predatorPopulation = startPredatorPopulation;
		this.preyPopulation = startPreyPopulation;
	}

	public void nextBatch() {
		
	}

	public Individual[][] getPredatorIndividuals() {
		return null;
	}

	public Individual[][] getPreyIndividuals() {
		return null;
	}

}
