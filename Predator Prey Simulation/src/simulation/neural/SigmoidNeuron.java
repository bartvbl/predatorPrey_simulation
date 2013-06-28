package simulation.neural;

import util.MathUtil;

public class SigmoidNeuron extends Neuron {

	public SigmoidNeuron(double threshold, double[] dentriteWeights) {
		super(threshold, dentriteWeights);
	}

	@Override
	public double fire(double[] dentriteValues) {
		if(dentriteValues.length != this.dentriteWeights.length) throw new IllegalArgumentException("The number of dentrite values must be equal to the number of weights of incoming connections to the neuron. Expected: " + dentriteWeights.length);
		double dentriteSum = MathUtil.weightedSum(dentriteValues, this.dentriteWeights);
		double sigmoid = 1/(1-Math.pow(Math.E, -dentriteSum));
		return sigmoid;
	}

}
