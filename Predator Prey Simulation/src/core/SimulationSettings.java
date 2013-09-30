package core;

import rendering.geom.Point;
//problem with 10 neurons: averages
//priblem wutg 3-2 neurons: does not evolve
//problem with 2 neurons: prey too dumb to learn
public class SimulationSettings {
	public static final double BOARD_WIDTH = 10;
	public static final double BOARD_HEIGHT = 10;
	public static final double SCREEN_SIZE = 11;
	
	public static final int predatorPopulationSize = 100;
	public static final double predatorStartRotation = 270;
	public static final double predatorSpeed = 0.05;
	
	public static final int preyPopulationSize = 100;
	public static final double preyStartRotation = 90;
	public static final double preySpeed = 0.1;
	
	public static final double robotRadius = 0.45;
	
	public static final int numRoundTicks = 3000;
	public static final double ticksPreyCanWin = 1000;
	
	public static final double[] distanceSensorDirections = new double[]{70, 290, 47, 313, 24, 337, 160, 200};
	public static final double distanceSensorRange = 0.45;
	public static final double distanceSensorNoiseFactor = 0.2;
	
	public static final double visionSensorRange = 3;
	
	public static final int neuralNetworkPredatorVisionNeurons = 5;
	public static final double neuralNetworkPredatorVisionAngle = 36;
	public static final double neuralNetworkPredatorVisionRange = 4;
	public static final int neuralNetworkOutputCount = 2;
	public static final int numAgeNeurons = 1;
	public static final int neuralNetworkPredatorInputCount = numAgeNeurons + neuralNetworkOutputCount + distanceSensorDirections.length + neuralNetworkPredatorVisionNeurons;
	public static final int neuralNetworkPreyInputCount = numAgeNeurons + neuralNetworkOutputCount + distanceSensorDirections.length;
	public static final int[] neuralNetworkHiddenLayerSizes = new int[]{neuralNetworkOutputCount};
	
	public static final int hallOfFameSize = 10;
	public static final int randomlyPickedBatchSize = 10;
	public static final int reproductionCount = 3;
	public static final double flipBitProbability = 0.1;
	public static final double crossOverProbability = 0.1;
	public static final int hallOfFameBattleRepetitions = 10;
	
	public static final Point[] predatorStartLocations = new Point[]{new Point(2.5, 5), 
																	 new Point(1, 6),
																	 new Point(2.5, 4),
																	 new Point(1, 7),
																	 new Point(1, 3)};
	public static final Point[] preyStartLocations = new Point[]{new Point(7.5, 5), 
																 new Point(9, 6),
																 new Point(7.5, 4),
																 new Point(9, 7),
																 new Point(9, 3)};
	
}
