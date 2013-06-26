package simulation;

import simulation.world.World;

public class RoundEndVerifyer {

	public static boolean hasRoundFinished(World world, boolean persuitTimedOut) {
		Integer[] preyIDs = world.getRobotsByType(RobotType.PREY_BLUE);
		boolean allPreyEaten = preyIDs.length == 0;
		
		return allPreyEaten || persuitTimedOut;
	}

}
