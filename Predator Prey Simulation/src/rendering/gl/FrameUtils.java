package rendering.gl;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.gluOrtho2D;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import core.SimulationSettings;

public class FrameUtils {
	public static final int DEFAULT_WINDOW_WIDTH = 1024;
	public static final int DEFAULT_WINDOW_HEIGHT = 768;
	
	public static void initWindow() throws LWJGLException {
		Display.setDisplayMode(new DisplayMode(DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT));
		Display.create();
		Display.setResizable(true);
		Display.setTitle("Predator Prey");
		glViewport(0, 0, DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glMatrixMode(GL_MODELVIEW);
		glClearColor(0, 0, 0, 1);
		glClearDepth(1.0);
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glShadeModel(GL_SMOOTH);
	}
	
	public static void newFrame() {
		setViewport();
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT | GL_STENCIL_BUFFER_BIT);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
	}

	public static void set2DMode() {
		glDisable(GL_DEPTH_TEST);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		double aspectRatio = calculateAspectRatio();
		float screenWidth = (float)(aspectRatio * SimulationSettings.SCREEN_SIZE);
		float screenHeight = (float) SimulationSettings.SCREEN_SIZE;
		gluOrtho2D(-screenWidth/2, screenWidth/2, -screenHeight/2, screenHeight/2);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}

	public static void setViewport() {
		glViewport(0, 0, Display.getWidth(), Display.getHeight()); 
	}

	public static double calculateAspectRatio() {
		double windowWidth, windowHeight;
		if((Display.getWidth() == 0) || (Display.getHeight() == 0))
		{
			windowWidth = 100d;
			windowHeight = 100d;
		} else {
			windowWidth = Display.getWidth();
			windowHeight = Display.getHeight();
		}
		double aspectRatio = windowWidth/windowHeight;
		return aspectRatio;
	}

}
