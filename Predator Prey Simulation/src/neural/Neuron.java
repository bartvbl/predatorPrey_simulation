package neural;

public abstract class Neuron {
	protected final double threshold;
	protected final double[] dentriteWeights;
	
	public Neuron(double threshold, double[] dentriteWeights) {
		this.threshold = threshold;
		this.dentriteWeights = dentriteWeights;
	}
	
	public abstract double fire(double[] dentriteValues);
}
