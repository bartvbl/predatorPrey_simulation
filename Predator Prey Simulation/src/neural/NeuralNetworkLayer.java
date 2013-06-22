package neural;

public class NeuralNetworkLayer {
	private final double[] axonValues;
	private final int length;
	private final Neuron[] neurons;

	public NeuralNetworkLayer(int length) {
		this.axonValues = new double[length];
		this.length = length;
		this.neurons = new Neuron[length];
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
