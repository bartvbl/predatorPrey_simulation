package core;

import geom.Point;

public class SimulationSettings {
	public static final double BOARD_WIDTH = 15;
	public static final double BOARD_HEIGHT = 15;
	public static final double SCREEN_SIZE = 16;
	
	public static final Point predatorStartLocation = new Point(0.5, 7.5);
	public static final double predatorStartRotation = 270;
	public static final double predatorSpeed = 0.05;
	
	public static final Point preyStartLocation = new Point(14.5, 7.5);
	public static final double preyStartRotation = 90;
	public static final double preySpeed = 0.1;
	
	public static final double robotRadius = 0.45;
	
	
}
