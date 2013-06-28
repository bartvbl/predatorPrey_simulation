package simulation.neural;

import java.util.Arrays;

public class NeuralNetwork {
	private final NeuralNetworkLayer[] layers;
	public final int numInputNeurons;

	public NeuralNetwork(NeuralNetworkLayer[] layers, int numInputNeurons) {
		assert(layers.length >= 2);
		this.layers = layers;
		this.numInputNeurons = numInputNeurons;
	}
	
	public double[] simulate(double[] inputNeuronDentriteValues) {
		if(inputNeuronDentriteValues.length != numInputNeurons) throw new IllegalArgumentException("This network requires " + numInputNeurons + " input values. " + inputNeuronDentriteValues.length + " values were supplied.");
		
		double[] previousLayerAxonValues = inputNeuronDentriteValues;
		for(NeuralNetworkLayer layer : layers) {
			System.out.println("previous layer axon values: " + Arrays.toString(previousLayerAxonValues));
			layer.simulate(previousLayerAxonValues);
			previousLayerAxonValues = layer.getAxonValues();
		}
		return layers[layers.length - 1].getAxonValues();
	}

	public NeuralNetworkLayer[] getLayers() {
		return layers;
	}
}
