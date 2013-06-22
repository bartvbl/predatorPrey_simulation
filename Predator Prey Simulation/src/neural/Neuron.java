package neural;

public abstract class Neuron {
	protected final double threshold;
	
	public Neuron(double threshold) {
		this.threshold = threshold;
	}
	
	public abstract double fire(double[] dentriteValues);
}
