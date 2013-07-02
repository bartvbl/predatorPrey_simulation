package simulation.evolution;

import java.nio.FloatBuffer;
import java.util.Random;

import org.lwjgl.BufferUtils;

import core.SimulationSettings;
import simulation.Individual;
import simulation.dna.DNAReader;
import simulation.dna.DNAWriter;
import simulation.neural.NeuralNetwork;

public class Evolver {
	private static final Random random = new Random(System.nanoTime());
	private static final int one = 0x8000;

	public static NeuralNetwork[] evolve(Individual[] reproductionIndividuals) {
		int individualCount = reproductionIndividuals.length;
		int reproductionCount = SimulationSettings.reproductionCount * individualCount;
		NeuralNetwork[] reproducedNetworks = new NeuralNetwork[reproductionCount];
		
		float[][] individualDNA = new float[individualCount][0];
		for(int i = 0; i < reproductionIndividuals.length; i++) {
			float[] dna = DNAWriter.store(reproductionIndividuals[i].neuralNetwork);
			individualDNA[i] = dna;
		}
		
		int reproductionIndex = 0;
		for(int i = 0; i < individualCount; i++) {
			for(int j = 0; j < SimulationSettings.reproductionCount; j++) {
				reproducedNetworks[reproductionIndex] = reproduce(individualDNA[i], reproductionIndividuals[i].neuralNetwork, individualDNA);
				reproductionIndex++;
			}
		}
		
		return reproducedNetworks;
	}

	private static NeuralNetwork reproduce(float[] individual, NeuralNetwork individualNetwork, float[][] individualDNA) {
		FloatBuffer reproducedBuffer = BufferUtils.createFloatBuffer(individual.length);
		for(int i = 0; i < individual.length; i++) {
			float DNAPart = individual[i];
			if(random.nextDouble() < SimulationSettings.crossOverProbability) {
				int row = random.nextInt(individualDNA.length);
				int column = random.nextInt(individualDNA[row].length);
				DNAPart = individualDNA[row][column];
			}
			if(random.nextDouble() < SimulationSettings.flipBitProbability) {
				int floatBits = Float.floatToIntBits(DNAPart);
				int bitToFlip = random.nextInt(24);
				floatBits = (one >> bitToFlip) ^ floatBits;
				DNAPart = Float.intBitsToFloat(floatBits);
			}
			reproducedBuffer.put(DNAPart);
		}
		reproducedBuffer.rewind();
		return DNAReader.read(reproducedBuffer, individualNetwork.numInputNeurons);
	}
	
}
