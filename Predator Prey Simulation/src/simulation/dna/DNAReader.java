package simulation.dna;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

import core.SimulationSettings;
import simulation.neural.NeuralNetwork;
import simulation.neural.NeuralNetworkLayer;
import simulation.neural.Neuron;
import simulation.neural.SigmoidNeuron;

public class DNAReader {
	public static NeuralNetwork read(FloatBuffer weightBuffer, int numInputNeurons) {
		NeuralNetworkLayer[] layers = new NeuralNetworkLayer[SimulationSettings.neuralNetworkHiddenLayerSizes.length];
		weightBuffer.rewind();
		int previousLayerAxonCount = numInputNeurons;
		for(int i = 0; i < layers.length; i++) {
			double[] weights = new double[previousLayerAxonCount];
			int layerLength = SimulationSettings.neuralNetworkHiddenLayerSizes[i];
			Neuron[] neurons = new Neuron[layerLength];
			
			for(int neuronID = 0; neuronID < layerLength; neuronID++) {
				double threshold = weightBuffer.get();
				for(int j = 0; j < weights.length; j++) {
					weights[j] = (double) weightBuffer.get();
				}
				neurons[neuronID] = new SigmoidNeuron(threshold, weights);
			}
			layers[i] = new NeuralNetworkLayer(neurons);
			
			previousLayerAxonCount = layerLength;
		}
		return new NeuralNetwork(layers, numInputNeurons);
	}
}
