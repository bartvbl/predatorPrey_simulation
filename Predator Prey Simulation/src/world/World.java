package world;

import core.SimulationSettings;
import geom.Point;
import simulation.Robot;
import simulation.RobotType;

public class World {
	private final Robot predatorRobot = new Robot();
	private final Robot preyRobot = new Robot();
	
	public void setRobotLocation(RobotType type, Point newLocation) {
		getRobotByType(type).setLocation(newLocation);
	}
	
	public void moveRobot(RobotType type, double dx, double dy) {
		getRobotByType(type).move(dx, dy);
	}
	
	public void setRobotRotation(RobotType type, double rotation) {
		getRobotByType(type).setRotation(rotation);
	}
	
	public void rotateRobot(RobotType type, double rotation) {
		getRobotByType(type).rotate(rotation);
	}
	
	public Point getRobotLocation(RobotType type) {
		return getRobotByType(type).getLocation();
	}
	
	public double getRobotRotation(RobotType type) {
		return getRobotByType(type).getRotation(); 
	}

	public void reset() {
		predatorRobot.setLocation(SimulationSettings.predatorStartLocation);
		predatorRobot.setRotation(SimulationSettings.predatorStartRotation);
		preyRobot.setLocation(SimulationSettings.preyStartLocation);
		preyRobot.setRotation(SimulationSettings.preyStartRotation);
	}

	private Robot getRobotByType(RobotType type) {
		if(type == RobotType.PREDATOR_RED) {
			return this.predatorRobot;
		} else {
			return this.preyRobot;
		}
	}
}
