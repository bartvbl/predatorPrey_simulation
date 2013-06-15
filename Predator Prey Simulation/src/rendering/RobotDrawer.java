package rendering;

import geom.Point;

import org.lwjgl.util.Color;

import core.SimulationSettings;

import simulation.RobotType;
import static org.lwjgl.opengl.GL11.*;

import world.World;

public class RobotDrawer {
	private static final int predatorRobotDisplayList = createRobotDisplayList(new Color(255, 0, 0));
	private static final int preyRobotDisplayList = createRobotDisplayList(new Color(0, 0, 255));

	public static void drawRobots(World world) {
		drawPredator(world);
		drawPrey(world);
	}

	private static void drawPredator(World world) {
		Point location = world.getRobotLocation(RobotType.PREDATOR_RED);
		double rotation = world.getRobotRotation(RobotType.PREDATOR_RED);
		drawRobot(predatorRobotDisplayList, location, rotation);
	}
	
	private static void drawPrey(World world) {
		Point location = world.getRobotLocation(RobotType.PREY_BLUE);
		double rotation = world.getRobotRotation(RobotType.PREY_BLUE);
		drawRobot(preyRobotDisplayList, location, rotation);
	}

	private static void drawRobot(int displayList, Point location, double rotation) {
		glPushMatrix();
		glTranslated(location.x, location.y, 0);
		glRotated(rotation, 0, 0, 1);
		glCallList(displayList);
		glPopMatrix();
	}

	private static int createRobotDisplayList(Color colour) {
		int listID = glGenLists(1);
		glNewList(listID, GL_COMPILE);
		
		double radius = SimulationSettings.robotRadius;
		
		glBegin(GL_TRIANGLE_FAN);
		glColor4d((double)colour.getRed() / 255d, (double)colour.getGreen() / 255d, (double)colour.getBlue() / 255d, 1);
		glVertex2d(0, 0);
		for(int angle = 0; angle <= 360; angle++) {
			double x = Math.sin(Math.toRadians(-angle)) * radius;
			double y = Math.cos(Math.toRadians(-angle)) * radius;
			glVertex2d(x, y);
		}
		glEnd();
		
		glBegin(GL_LINES);
		glColor4d(0, 0, 0, 1);
		glVertex2d(0, 0.1*radius);
		glVertex2d(0, 0.9*radius);
		glEnd();
		
		glEndList();
		return listID;
	}

}
