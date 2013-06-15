package rendering;

import static org.lwjgl.opengl.GL11.*;
import core.SimulationSettings;

public class BoardDrawer {
	public static void drawBoard() {
		glColor4d(1, 1, 1, 1);
		glBegin(GL_QUADS);
		glVertex2d(0, 0);
		glVertex2d(SimulationSettings.BOARD_WIDTH, 0);
		glVertex2d(SimulationSettings.BOARD_WIDTH, SimulationSettings.BOARD_HEIGHT);
		glVertex2d(0, SimulationSettings.BOARD_HEIGHT);
		glEnd();
	}
}
