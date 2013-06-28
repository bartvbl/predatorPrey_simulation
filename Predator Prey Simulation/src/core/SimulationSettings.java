package core;

import rendering.geom.Point;

public class SimulationSettings {
	public static final double BOARD_WIDTH = 15;
	public static final double BOARD_HEIGHT = 15;
	public static final double SCREEN_SIZE = 16;
	
	public static final int predatorPopulationSize = 100;
	public static final double predatorStartRotation = 270;
	public static final double predatorSpeed = 0.1;
	
	public static final int preyPopulationSize = 100;
	public static final double preyStartRotation = 90;
	public static final double preySpeed = 0.0002;
	
	public static final double robotRadius = 0.45;
	
	public static final int numRoundTicks = 3000;
	
	public static final int[] distanceSensorDirections = new int[]{};
	public static final double distanceSensorRange = 1;
	public static final double distanceSensorNoiseFactor = 0.03;
	
	public static final int neuralNetworkPredatorInputCount = 4 + 8 + 5;
	public static final int neuralNetworkPreyInputCount = 4 + 8;
	public static final int neuralNetworkOutputCount = 4;
	public static final int[] neuralNetworkHiddenLayerSizes = new int[]{30, 30, 30};
	
	public static final int hallOfFameSize = 10;
	
	public static final Point[] predatorStartLocations = new Point[]{new Point(0.5, 7.5), 
																	 new Point(0.5, 6.5),
																	 new Point(0.5, 8.5),
																	 new Point(0.5, 5.5),
																	 new Point(0.5, 9.5)};
	public static final Point[] preyStartLocations = new Point[]{new Point(14.5, 7.5), 
																 new Point(14.5, 6.5),
																 new Point(14.5, 8.5),
																 new Point(14.5, 5.5),
																 new Point(14.5, 9.5)};
	
}
