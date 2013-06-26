package simulation.neural;

public class NeuralNetworkLayer {
	private final double[] axonValues;
	private final Neuron[] neurons;

	public NeuralNetworkLayer(Neuron[] layerNeurons) {
		this.axonValues = new double[layerNeurons.length];
		this.neurons = layerNeurons;
	}
	
	public void simulate(double[] previousLayerAxonValues) {
		for(int i = 0; i < neurons.length; i++) {
			axonValues[i] = neurons[i].fire(previousLayerAxonValues);
		}
	}
	
	public double[] getAxonValues() {
		return axonValues;
	}
}