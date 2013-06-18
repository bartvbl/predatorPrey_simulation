package simulation;

import core.SimulationSettings;
import geom.Point;
import world.World;

public class RoundEndVerifyer {

	public static boolean hasRoundFinished(World world) {
		Point predatorLocation = world.getRobotLocation(RobotType.PREDATOR_RED);
		Point preyLocation = world.getRobotLocation(RobotType.PREY_BLUE);
		double distanceBetweenRobots = predatorLocation.distanceTo(preyLocation);
		return distanceBetweenRobots <= 2 * SimulationSettings.robotRadius;
	}

}
