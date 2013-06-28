package util;

public class MathUtil {

	public static double weightedSum(double[] values, double[] weights) {
		if(values.length != weights.length) throw new IllegalArgumentException("A weighted sum can only be taken of arrays of equal size (" + values.length + " versus "+weights.length+").");
		
		double sum = 0;
		for(int i = 0; i < values.length; i++) {
			sum += values[i]*weights[i];
		}
		return sum;
	}

}
