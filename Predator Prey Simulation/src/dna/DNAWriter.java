package dna;

import java.nio.ByteBuffer;

import org.lwjgl.BufferUtils;

import neural.NeuralNetwork;

public class DNAWriter {
	private static final int numMetaBytes = 5;
	
	public byte[] toDNAString(NeuralNetwork network) {
		int byteCount = calculateDNAByteCount(network);
		ByteBuffer buffer = BufferUtils.createByteBuffer(byteCount);
		writeNetworkToBuffer(buffer, network);
		buffer.rewind();
		return buffer.array();
	}
	
	private void writeNetworkToBuffer(ByteBuffer buffer, NeuralNetwork network) {
		// TODO Auto-generated method stub
		
	}

	private int calculateDNAByteCount(NeuralNetwork network) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
