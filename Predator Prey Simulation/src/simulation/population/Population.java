package simulation.population;

import java.util.Iterator;

import simulation.neural.NeuralNetwork;

public class Population implements Iterable<NeuralNetwork> {
	private final NeuralNetwork[] neuralNetworks;
	
	public Population(NeuralNetwork[] neuralNetworks) {
		this.neuralNetworks = neuralNetworks;
	}
	
	public Iterator<NeuralNetwork> iterator() {
		return null;
	}
}
