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
		if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
			
		}
	}

	public boolean isFinished() {
		return false;
	}

	public void reset() {
		
	}
}
