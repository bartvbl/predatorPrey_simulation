package simulation.dna;

import simulation.neural.NeuralNetwork;
import simulation.neural.NeuralNetworkLayer;

public class DNAWriter {
	public static float[] store(NeuralNetwork network) {
		int floatCount = calculateDNAFloatCount(network);
		float[] dna = new float[floatCount];
		writeNetworkToBuffer(dna, network);
		return dna;
	}
	
	private static void writeNetworkToBuffer(float[] dna, NeuralNetwork network) {
		NeuralNetworkLayer[] layers = network.getLayers();
		int dnaPointer = 0;
		//store neurons per layer
		for(int layerID = 0; layerID < layers.length; layerID++) {
			NeuralNetworkLayer layer = layers[layerID];
			for(int neuron = 0; neuron < layer.length; neuron++) {
				double[] neuronWeights = layer.getNeuronWeights(neuron);
				for(double weight : neuronWeights) {
					dna[dnaPointer] = (float) weight;
					dnaPointer++;
				}
			}
		}
	}

	private static int calculateDNAFloatCount(NeuralNetwork network) {
		int floatCount = 0;
		NeuralNetworkLayer[] layers = network.getLayers();
		int previousLayerOutputs = network.numInputNeurons;
		//neuron definition sizes
		for(int layerID = 0; layerID < layers.length; layerID++) {
			int layerLength = layers[layerID].length;
			//number of weights
			floatCount += layerLength * previousLayerOutputs;
			previousLayerOutputs = layerLength;
		}
		return floatCount;
	}

	
}
