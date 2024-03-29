package simulation.dna;

import java.util.Random;

import core.SimulationSettings;
import simulation.neural.NeuralNetwork;
import simulation.neural.NeuralNetworkLayer;
import simulation.neural.Neuron;
import simulation.neural.SigmoidNeuron;

public class DNAGenerator {
	private static final Random random = new Random(System.nanoTime());
	
	public static NeuralNetwork generatePredatorNetwork() {
		return buildNeuralNetwork(SimulationSettings.neuralNetworkPredatorInputCount);
	}
	
	public static NeuralNetwork generatePreyNetwork() {
		return buildNeuralNetwork(SimulationSettings.neuralNetworkPreyInputCount);
	}

	private static NeuralNetwork buildNeuralNetwork(int numInputNeurons) {
		int[] layerNeuronCounts = SimulationSettings.neuralNetworkHiddenLayerSizes;
		NeuralNetworkLayer[] layers = new NeuralNetworkLayer[layerNeuronCounts.length];
		
		int previousLayerSize = numInputNeurons;
		for(int i = 0; i < layerNeuronCounts.length; i++) {
			Neuron[] layerNeurons = generateLayer(previousLayerSize, layerNeuronCounts[i]);
			layers[i] = new NeuralNetworkLayer(layerNeurons);
			previousLayerSize = layerNeuronCounts[i];
		}
		return new NeuralNetwork(layers, numInputNeurons);
	}

	private static Neuron[] generateLayer(int previousLayerSize, int layerSize) {
		Neuron[] layer = new Neuron[layerSize];
		for(int i = 0; i < layerSize; i++) {
			double[] dentriteWeights = generateRandomWeights(previousLayerSize);
			double threshold = (2*random.nextDouble()) - 1;
			layer[i] = new SigmoidNeuron(threshold, dentriteWeights);
		}
		return layer;
	}

	private static double[] generateRandomWeights(int previousLayerSize) {
		double[] layerWeights = new double[previousLayerSize];
		double maxWeight = 1d / (double) previousLayerSize;
		for(int i = 0; i < layerWeights.length; i++) {
			layerWeights[i] = (2*random.nextDouble()) - 1;
		}
		return layerWeights;
	}

	public static NeuralNetwork[] generatePredatorNetworks(int count) {
		NeuralNetwork[] networks = new NeuralNetwork[count];
		for(int i = 0; i < count; i++) {
			networks[i] = generatePredatorNetwork();
		}
		return networks;
	}

	public static NeuralNetwork[] generatePreyNetworks(int count) {
		NeuralNetwork[] networks = new NeuralNetwork[count];
		for(int i = 0; i < count; i++) {
			networks[i] = generatePreyNetwork();
		}
		return networks;
	}
}
