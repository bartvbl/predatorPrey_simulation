package neural;

import util.MathUtil;

public class SigmoidNeuron extends Neuron {

	public SigmoidNeuron(double threshold, double[] dentriteWeights) {
		super(threshold, dentriteWeights);
	}

	@Override
	public double fire(double[] dentriteValues) {
		assert(dentriteValues.length == this.dentriteWeights.length);
		double dentriteSum = MathUtil.weightedSum(dentriteValues, this.dentriteWeights);
		double sigmoid = 1/(1-Math.pow(Math.E, -dentriteSum));
		return sigmoid;
	}

}
