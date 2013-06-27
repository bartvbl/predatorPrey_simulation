package util;

import java.util.Arrays;

import simulation.Individual;

public class ArrayUtil {

	public static double[] concat(double[] startArray, double[] endArray) {
		double[] newArray = Arrays.copyOf(startArray, startArray.length + endArray.length);
		System.arraycopy(endArray, 0, newArray, startArray.length, endArray.length);
		return newArray;
	}

	public static Individual[] concat(Individual[] startArray, Individual[] endArray) {
		Individual[] newArray = Arrays.copyOf(startArray, startArray.length + endArray.length);
		System.arraycopy(endArray, 0, newArray, startArray.length, endArray.length);
		return newArray;
	}

}
