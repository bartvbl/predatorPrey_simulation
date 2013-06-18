package simulation;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;

import core.SimulationSettings;

import world.World;

public class Simulator {

	private World world;

	public void setWorld(World world) {
		this.world = world;
	}
	
	public void init() throws LWJGLException {
		Keyboard.create();
	}
	
	public void updateSimulation() {
		if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
			world.moveLeftWheel(RobotType.PREDATOR_RED, SimulationSettings.predatorSpeed);
			world.moveRightWheel(RobotType.PREDATOR_RED, -SimulationSettings.predatorSpeed);
		}
	}

	public boolean isFinished() {
		return RoundEndVerifyer.hasRoundFinished(world);
	}

	public void reset() {
		
	}
}
