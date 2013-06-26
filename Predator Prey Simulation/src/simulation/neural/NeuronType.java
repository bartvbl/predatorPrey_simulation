package simulation.neural;

public enum NeuronType {
	PASSTHROUGH((byte) 0),
	SIGMOID((byte) 1);
	
	public final byte byteID;

	private NeuronType(byte byteValue) {
		this.byteID = byteValue;
	}
	
	public static NeuronType getTypeByByteID(byte byteID) {
		NeuronType[] values = NeuronType.values();
		for(NeuronType type : values) {
			if(type.byteID == byteID) {
				return type;
			}
		}
		return NeuronType.PASSTHROUGH;
	}
}
