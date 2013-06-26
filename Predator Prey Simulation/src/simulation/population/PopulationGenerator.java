package simulation.population;

import simulation.dna.DNAGenerator;
import simulation.neural.NeuralNetwork;
import core.SimulationSettings;

public class PopulationGenerator {
	public static Population generatePredatorPopulation() {
		int size = SimulationSettings.predatorPopulationSize;
		NeuralNetwork[] neuralNetworks = new NeuralNetwork[size];
		for(int i = 0; i < size; i++) {
			neuralNetworks[i] = DNAGenerator.generatePredatorNetwork();
		}
		return new Population(neuralNetworks);
	}
	
	public static Population generatePreyPopulation() {
		int size = SimulationSettings.preyPopulationSize;
		NeuralNetwork[] neuralNetworks = new NeuralNetwork[size];
		for(int i = 0; i < size; i++) {
			neuralNetworks[i] = DNAGenerator.generatePreyNetwork();
		}
		return new Population(neuralNetworks);
	}
}
