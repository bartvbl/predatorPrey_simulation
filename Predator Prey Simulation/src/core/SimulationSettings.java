package core;

public class SimulationSettings {
	public static final double BOARD_WIDTH = 15;
	public static final double BOARD_HEIGHT = 15;
	public static final double SCREEN_SIZE = 16;
	
	public static final double predatorStartRotation = 270;
	public static final double predatorSpeed = 0.1;
	
	public static final double preyStartRotation = 90;
	public static final double preySpeed = 0.0002;
	
	public static final double robotRadius = 0.45;
	
	public static final int numRoundTicks = 3000;
	
	public static final int[] distanceSensorDirections = new int[]{};
	public static final double distanceSensorRange = 1;
	public static final double distanceSensorNoiseFactor = 0.03;
	
	public static int numNeuralNetworkPredatorInputs = 4 + 8 + 5;
	public static int numNeuralNetworkPreyInputs = 4 + 8;
	public static int numNeuralNetworkOutputs = 4;
}
