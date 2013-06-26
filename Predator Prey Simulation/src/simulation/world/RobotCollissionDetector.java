package simulation.world;

import java.util.ArrayList;

import core.SimulationSettings;

import rendering.geom.Point;
import simulation.Robot;
import simulation.RobotType;

public class RobotCollissionDetector {

	public static void removeCollidedPrey(ArrayList<Robot> robots) {
		Point[] robotLocations = getRobotLocations(robots);
		ArrayList<Integer> removalQueue = new ArrayList<Integer>();
		for(int i = 0; i < robots.size(); i++) {
			if(robots.get(i).type == RobotType.PREDATOR_RED) {
				checkPreyCollissions(i, robots, removalQueue, robotLocations);
			}
		}
		for(int i = robots.size() - 1; i >= 0; i--) {
			if(removalQueue.contains(i)) {
				robots.remove(i);
			}
		}
	}

	private static Point[] getRobotLocations(ArrayList<Robot> robots) {
		Point[] locations = new Point[robots.size()];
		for(int i = 0; i < robots.size(); i++) {
			locations[i] = robots.get(i).getLocation();
		}
		return locations;
	}

	private static void checkPreyCollissions(int predatorRobotID, ArrayList<Robot> robots, ArrayList<Integer> removalQueue, Point[] robotLocations) {
		for(int i = 0; i < robots.size(); i++) {
			Robot robot = robots.get(i);
			if(robot.type == RobotType.PREY_BLUE) {
				Point predatorLocation = robotLocations[predatorRobotID];
				Point preyLocation = robotLocations[i];
				
				double distance = predatorLocation.distanceTo(preyLocation);
				if(distance <= 2 * SimulationSettings.robotRadius) {
					removalQueue.add(i);
				}
			}
		}
	}

}
