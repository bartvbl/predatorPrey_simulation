package simulation;

import java.util.ArrayList;

import simulation.neural.NeuralNetwork;

public class Individual {
	public final NeuralNetwork neuralNetwork;

	private final ArrayList<Double> roundFitnessOutcomes = new ArrayList<Double>();
	
	public Individual(NeuralNetwork brain) {
		this.neuralNetwork = brain;
	}
	
	public double getAverageFitness() {
		double sum = 0;
		for(Double fitness : roundFitnessOutcomes) {
			sum += fitness;
		}
		return sum / (double)roundFitnessOutcomes.size();
	}

	public void registerFitnessValue(double fitness) {
		roundFitnessOutcomes.add(fitness);
	}
}
