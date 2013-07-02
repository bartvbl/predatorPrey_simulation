package simulation.evolution;

import core.SimulationSettings;
import simulation.Individual;
import simulation.neural.NeuralNetwork;

public class Evolver {

	public static NeuralNetwork[] evolve(Individual[] reproductionIndividuals) {
		int individualCount = reproductionIndividuals.length;
		int reproductionCount = SimulationSettings.reproductionCount * individualCount;
		NeuralNetwork[] reproducedNetworks = new NeuralNetwork[individualCount * reproductionCount];
		
		int reproductionIndex = 0;
		for(int i = 0; i < individualCount; i++) {
			for(int j = 0; j < reproductionCount; j++) {
				reproducedNetworks[reproductionIndex] = reproduce(reproductionIndividuals[i]);
				reproductionIndex++;
			}
		}
		
		return reproducedNetworks;
	}

	private static NeuralNetwork reproduce(Individual individual) {
		return null;
	}
	
}
