package simulation.dna;

import java.nio.ByteBuffer;

import org.lwjgl.BufferUtils;

import simulation.neural.NeuralNetwork;
import simulation.neural.NeuralNetworkLayer;

public class DNAWriter {
	private static final int numMetaBytes = 3 * 4; //two ints
	private static final int header = 0x4E4E4554;
	
	public byte[] store(NeuralNetwork network) {
		int byteCount = calculateDNAByteCount(network);
		ByteBuffer buffer = BufferUtils.createByteBuffer(byteCount);
		writeNetworkToBuffer(buffer, network);
		buffer.rewind();
		return buffer.array();
	}
	
	private void writeNetworkToBuffer(ByteBuffer buffer, NeuralNetwork network) {
		NeuralNetworkLayer[] layers = network.getLayers();
		buffer.putInt(header);
		buffer.putInt(network.numInputNeurons);
		buffer.putInt(layers.length);
		
		//store neurons per layer
		for(int i = 0; i < layers.length; i++) {
			buffer.putInt(layers[i].length);
		}
	}

	private int calculateDNAByteCount(NeuralNetwork network) {
		int byteCount = numMetaBytes;
		NeuralNetworkLayer[] layers = network.getLayers();
		//neuron count per layer
		byteCount += 4 * layers.length;
		//neuron definition sizes
		for(NeuralNetworkLayer layer : layers) {
			for(int i = 0; i < layer.length - 1; i++) {				
				
			}
		}
		return 0;
	}

	
}
