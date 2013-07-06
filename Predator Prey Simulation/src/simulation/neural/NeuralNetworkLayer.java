package simulation.neural;

import java.util.Arrays;

public class NeuralNetworkLayer {
	private final double[] axonValues;
	private final Neuron[] neurons;
	
	public final int length;

	public NeuralNetworkLayer(Neuron[] layerNeurons) {
		this.axonValues = new double[layerNeurons.length];
		this.neurons = layerNeurons;
		this.length = neurons.length;
	}
	
	public void simulate(double[] previousLayerAxonValues) {
		for(int i = 0; i < neurons.length; i++) {
			axonValues[i] = neurons[i].fire(previousLayerAxonValues);
		}
	}
	
	public double[] getAxonValues() {
		return axonValues;
	}

	public double[] getNeuronWeights(int neuron) {
		return neurons[neuron].dentriteWeights;
	}

	public double getNeuronThreshold(int neuron) {
		return neurons[neuron].threshold;
	}
}
