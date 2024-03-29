package rendering;


import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import rendering.gl.FrameUtils;
import simulation.world.World;

import core.SimulationSettings;
import static org.lwjgl.opengl.GL11.*;


public class Renderer {
	private World world;

	public void init() throws LWJGLException {
		FrameUtils.initWindow();
		Keyboard.create();
	}
	
	public void renderFrame() {
		FrameUtils.newFrame();
		FrameUtils.set2DMode();
		
		//centers the board
		glTranslated(-SimulationSettings.BOARD_WIDTH/2, -SimulationSettings.BOARD_HEIGHT/2, 0);
		
		BoardDrawer.drawBoard();
		RobotDrawer.drawRobots(world);
		
		Display.update();
		Display.sync(100);
	}



	public void setWorld(World world) {
		this.world = world;
	}

	public void destroy() {
		Display.destroy();
	}

	public boolean isWindowCloseRequested() {
		return Display.isCloseRequested();
	}
	
	
}
