package simulation;

import simulation.world.World;

public class Simulator {

	private World world;

	public void setWorld(World world) {
		this.world = world;
	}
	
	public void init() {
	}
	
	public void updateSimulation() {
		
	}

	public boolean isFinished() {
		return RoundEndVerifyer.hasRoundFinished(world);
	}

	public void reset() {
		
	}
}
