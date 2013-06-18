package world;

import core.SimulationSettings;
import geom.Point;
import simulation.Robot;
import simulation.RobotType;

public class World {
	private final Robot predatorRobot = new Robot(RobotType.PREDATOR_RED);
	private final Robot preyRobot = new Robot(RobotType.PREY_BLUE);
	
	public void moveLeftWheel(RobotType type, double distance) {
		Robot robot = getRobotByType(type);
	
		double angle = -distance;
		double currentAngleRadians = Math.toRadians(robot.getRotation()) + Math.PI;

		double startPositionX = Math.cos(currentAngleRadians) * SimulationSettings.robotRadius;
		double startPositionY = Math.sin(currentAngleRadians) * SimulationSettings.robotRadius;
		double endPositionX = Math.cos(currentAngleRadians + angle) * SimulationSettings.robotRadius;
		double endPositionY = Math.sin(currentAngleRadians + angle) * SimulationSettings.robotRadius;
		
		double dx = endPositionX - startPositionX;
		double dy = endPositionY - startPositionY;

		robot.rotate(Math.toDegrees(angle));
		robot.move(dx, dy);
	}
	
	public void moveRightWheel(RobotType type, double distance) {
		Robot robot = getRobotByType(type);
		
		double angle = distance;
		double currentAngleRadians = Math.toRadians(robot.getRotation());

		double startPositionX = Math.cos(currentAngleRadians) * SimulationSettings.robotRadius;
		double startPositionY = Math.sin(currentAngleRadians) * SimulationSettings.robotRadius;
		double endPositionX = Math.cos(currentAngleRadians + angle) * SimulationSettings.robotRadius;
		double endPositionY = Math.sin(currentAngleRadians + angle) * SimulationSettings.robotRadius;
		
		double dx = endPositionX - startPositionX;
		double dy = endPositionY - startPositionY;

		robot.rotate(Math.toDegrees(angle));
		robot.move(dx, dy);
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
