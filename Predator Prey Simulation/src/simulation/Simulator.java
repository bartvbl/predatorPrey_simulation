package simulation;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;

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
		if(Keyboard.isKeyDown(Keyboard.KEY_A)){
			world.moveLeftWheel(RobotType.PREDATOR_RED, -0.05);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_Q)){
			world.moveLeftWheel(RobotType.PREDATOR_RED, 0.05);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_W)){
			world.moveRightWheel(RobotType.PREDATOR_RED, 0.05);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)){
			world.moveRightWheel(RobotType.PREDATOR_RED, -0.05);
		}
	}

	public boolean isFinished() {
		return RoundEndVerifyer.hasRoundFinished(world);
	}

	public void reset() {
		
	}
}
