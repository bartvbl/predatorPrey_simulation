package simulation.population;

import java.util.Iterator;

import simulation.Individual;
import simulation.neural.NeuralNetwork;

public class Population implements Iterable<NeuralNetwork> {
	private final NeuralNetwork[] neuralNetworks;
	public final int length;
	
	public Population(NeuralNetwork[] neuralNetworks) {
		length = neuralNetworks.length;
		this.neuralNetworks = neuralNetworks;
	}
	
	public Iterator<NeuralNetwork> iterator() {
		return null;
	}

	public Individual[] generateIndividuals() {
		Individual[] individuals = new Individual[neuralNetworks.length];
		for(int i = 0; i < neuralNetworks.length; i++) {
			individuals[i] = new Individual(neuralNetworks[i]);
		}
		return individuals;
	}
}
