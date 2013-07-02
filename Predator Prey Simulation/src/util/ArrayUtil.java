package util;

import java.util.Arrays;
import java.util.Random;

import simulation.Individual;
import simulation.neural.NeuralNetwork;

public class ArrayUtil {
	private static final Random random = new Random(System.nanoTime());

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

	public static NeuralNetwork[] concat(NeuralNetwork[] startArray, NeuralNetwork[] endArray) {
		NeuralNetwork[] newArray = Arrays.copyOf(startArray, startArray.length + endArray.length);
		System.arraycopy(endArray, 0, newArray, startArray.length, endArray.length);
		return newArray;
	}

	public static Individual[] pickRandom(Individual[] array, int count) {
		if(count >= array.length) { 
			throw new IllegalArgumentException("The supplied array does not have " + count + " elements."); 
		}
		Individual[] chosenIndividuals = new Individual[count];
		boolean[] usedIndividuals = new boolean[array.length];
		int chosenIndividualCount = 0;
		while(chosenIndividualCount < count) {
			int chosenIndex = random.nextInt(array.length - 1);
			if(!usedIndividuals[chosenIndex]) {
				usedIndividuals[chosenIndex] = true;
				chosenIndividuals[chosenIndividualCount] = array[chosenIndex];
				chosenIndividualCount++;
			}
		}
		return chosenIndividuals;
	}


}
