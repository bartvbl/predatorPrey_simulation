package rendering;

import java.util.Arrays;

import org.lwjgl.util.Color;

import core.SimulationSettings;

import rendering.geom.Point;
import simulation.Robot;
import simulation.RobotType;
import simulation.world.World;
import static org.lwjgl.opengl.GL11.*;


public class RobotDrawer {
	private static final int predatorRobotDisplayList = createRobotDisplayList(new Color(255, 0, 0));
	private static final int preyRobotDisplayList = createRobotDisplayList(new Color(0, 0, 255));

	public static void drawRobots(World world) {
		drawPredators(world);
		drawPrey(world);
	}

	private static void drawPredators(World world) {
		Integer[] predators = world.getRobotsByType(RobotType.PREDATOR_RED);
		for(int predatorID : predators) {
			Point location = world.getRobotLocation(predatorID);
			double rotation = world.getRobotRotation(predatorID);
			drawRobot(predatorRobotDisplayList, location, rotation);
		}
	}
	
	private static void drawPrey(World world) {
		Integer[] predators = world.getRobotsByType(RobotType.PREY_BLUE);
		for(int preyID : predators) {
			Point location = world.getRobotLocation(preyID);
			double rotation = world.getRobotRotation(preyID);
			drawRobot(preyRobotDisplayList, location, rotation);
		}
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
