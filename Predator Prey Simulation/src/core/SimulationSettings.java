package core;

import rendering.geom.Point;

public class SimulationSettings {
	public static final double BOARD_WIDTH = 15;
	public static final double BOARD_HEIGHT = 15;
	public static final double SCREEN_SIZE = 16;
	
	public static final int predatorPopulationSize = 100;
	public static final double predatorStartRotation = 270;
	public static final double predatorSpeed = 0.01;
	
	public static final int preyPopulationSize = 100;
	public static final double preyStartRotation = 90;
	public static final double preySpeed = 0.0002;
	
	public static final double robotRadius = 0.45;
	
	public static final int numRoundTicks = 5000000;
	
	public static final double[] distanceSensorDirections = new double[]{70, 290, 47, 313, 24, 337, 160, 200};
	public static final double distanceSensorRange = 4;
	public static final double distanceSensorNoiseFactor = 0.2;
	
	public static final double visionSensorRange = 7;
	
	public static final int neuralNetworkPredatorVisionNeurons = 5;
	public static final int neuralNetworkOutputCount = 2;
	public static final int neuralNetworkPredatorInputCount = neuralNetworkOutputCount + distanceSensorDirections.length + neuralNetworkPredatorVisionNeurons;
	public static final int neuralNetworkPreyInputCount = neuralNetworkOutputCount + distanceSensorDirections.length;
	public static final int[] neuralNetworkHiddenLayerSizes = new int[]{30, 30, 30, neuralNetworkOutputCount};
	
	public static final int hallOfFameSize = 10;
	
	public static final Point[] predatorStartLocations = new Point[]{new Point(1, 7.5), 
																	 new Point(1, 6.5),
																	 new Point(1, 8.5),
																	 new Point(1, 5.5),
																	 new Point(1, 9.5)};
	public static final Point[] preyStartLocations = new Point[]{new Point(14, 7.5), 
																 new Point(14, 6.5),
																 new Point(14, 8.5),
																 new Point(14, 5.5),
																 new Point(14, 9.5)};
	
}
