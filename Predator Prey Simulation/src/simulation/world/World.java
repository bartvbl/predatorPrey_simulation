package simulation.world;

import java.util.ArrayList;

import core.SimulationSettings;
import rendering.geom.Point;
import simulation.Robot;
import simulation.RobotType;

public class World {
	private final ArrayList<Robot> robots = new ArrayList<Robot>();
	
	public void moveLeftWheel(int robotID, double distance) {
		Robot robot = getRobotByID(robotID);
		
		double angle = -distance;
		double currentAngleRadians = Math.toRadians(robot.getRotation()) + Math.PI;

		double startPositionX = Math.cos(currentAngleRadians) * SimulationSettings.robotRadius;
		double startPositionY = Math.sin(currentAngleRadians) * SimulationSettings.robotRadius;
		double endPositionX = Math.cos(currentAngleRadians + angle) * SimulationSettings.robotRadius;
		double endPositionY = Math.sin(currentAngleRadians + angle) * SimulationSettings.robotRadius;
		
		double dx = endPositionX - startPositionX;
		double dy = endPositionY - startPositionY;

		robot.rotate(Math.toDegrees(angle));
		moveRobot(robot, dx, dy);
	}

	
	public void moveRightWheel(int robotID, double distance) {
		Robot robot = getRobotByID(robotID);
		
		double angle = distance;
		double currentAngleRadians = Math.toRadians(robot.getRotation());

		double startPositionX = Math.cos(currentAngleRadians) * SimulationSettings.robotRadius;
		double startPositionY = Math.sin(currentAngleRadians) * SimulationSettings.robotRadius;
		double endPositionX = Math.cos(currentAngleRadians + angle) * SimulationSettings.robotRadius;
		double endPositionY = Math.sin(currentAngleRadians + angle) * SimulationSettings.robotRadius;
		
		double dx = endPositionX - startPositionX;
		double dy = endPositionY - startPositionY;

		robot.rotate(Math.toDegrees(angle));
		moveRobot(robot, dx, dy);
	}
	
	private void moveRobot(Robot robot, double dx, double dy) {
		Point robotLocation = robot.getLocation();
		double newX = robotLocation.x + dx;
		double newY = robotLocation.y + dy;
		double robotRadius = SimulationSettings.robotRadius;
		if(newX - robotRadius <= 0) {
			dx = 0;
		}
		if(newX + robotRadius >= SimulationSettings.BOARD_WIDTH) {
			dx = 0;
		}
		if(newY - robotRadius <= 0) {
			dy = 0;
		}
		if(newY + robotRadius >= SimulationSettings.BOARD_HEIGHT) {
			dy = 0;
		}
		robot.move(dx, dy);
	}
	
	public Point getRobotLocation(int robotID) {
		return getRobotByID(robotID).getLocation();
	}
	
	public double getRobotRotation(int robotID) {
		return getRobotByID(robotID).getRotation(); 
	}

	public RobotType getRobotType(int robotID) {
		return getRobotByID(robotID).type;
	}

	public void reset() {
		this.robots.clear();
	}

	private Robot getRobotByID(int robotID) {
		assert(robotID >= 0);
		assert(robotID < robots.size());
		
		Robot robot = robots.get(robotID);
		return robot;
	}

	public int spawnRobot(RobotType type) {
		int generatedID = robots.size();
		robots.add(new Robot(type));
		return generatedID;
	}

	public void initRobotLocations() {
		Integer[] predators = getRobotsByType(RobotType.PREDATOR_RED);
		Integer[] prey = getRobotsByType(RobotType.PREY_BLUE);
		for(int i = 0; i < predators.length; i++) {
			Robot predatorRobot = getRobotByID(predators[i]);
			predatorRobot.setLocation(SimulationSettings.predatorStartLocations[i]);
		}
		for(int i = 0; i < prey.length; i++) {
			Robot preyRobot = getRobotByID(prey[i]);
			preyRobot.setLocation(SimulationSettings.preyStartLocations[i]);
		}
	}
	
	public Integer[] getRobotsByType(RobotType type) {
		ArrayList<Integer> robotIDs = new ArrayList<Integer>();
		for(int i = 0; i < robots.size(); i++) {
			Robot robot = robots.get(i);
			if(robot.type == type) {
				robotIDs.add(i);
			}
		}
		return robotIDs.toArray(new Integer[robotIDs.size()]);
	}


	public void checkCollissions() {
		RobotCollissionDetector.removeCollidedPrey(robots);
	}

}
