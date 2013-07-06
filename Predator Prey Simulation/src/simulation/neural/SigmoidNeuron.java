package simulation.neural;

import java.util.Arrays;

import util.MathUtil;

public class SigmoidNeuron extends Neuron {

	public SigmoidNeuron(double threshold, double[] dentriteWeights) {
		super(threshold, dentriteWeights);
	}

	@Override
	public double fire(double[] dentriteValues) {
		if(dentriteValues.length != this.dentriteWeights.length) throw new IllegalArgumentException("The number of dentrite values must be equal to the number of weights of incoming connections to the neuron. Expected: " + dentriteWeights.length);
		double dentriteSum = MathUtil.weightedSum(dentriteValues, this.dentriteWeights);
		if(dentriteSum < threshold) {
			return 0;
		}
		double sigmoid = (1 / (1 + Math.exp(-dentriteSum))) - 0.5;
		return sigmoid;
	}

}
