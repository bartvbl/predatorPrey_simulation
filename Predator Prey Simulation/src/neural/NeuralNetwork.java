package neural;

public class NeuralNetwork {
	private final NeuralNetworkLayer[] layers;
	private final int numInputNeurons;

	public NeuralNetwork(NeuralNetworkLayer[] layers, int numInputNeurons) {
		assert(layers.length >= 2);
		
		this.layers = layers;
		this.numInputNeurons = numInputNeurons;
	}
	
	public double[] simulate(double[] inputNeuronDentriteValues) {
		assert(inputNeuronDentriteValues.length == numInputNeurons);
		
		double[] previousLayerAxonValues = inputNeuronDentriteValues;
		for(NeuralNetworkLayer layer : layers) {
			layer.simulate(previousLayerAxonValues);
			previousLayerAxonValues = layer.getAxonValues();
		}
		return layers[layers.length - 1].getAxonValues();
	}
}
