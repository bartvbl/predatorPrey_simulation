package simulation;

import rendering.geom.Point;
import core.SimulationSettings;

public class Robot {
	private Point location = new Point(1, 1);
	private double rotation = 0;
	public final RobotType type;
	
	public Robot(RobotType type) {
		this.type = type;
	}

	public void setLocation(Point newLocation) {
		this.location = newLocation;
	}

	public void move(double dx, double dy) {
		this.location = new Point(location.x + dx, location.y + dy);
	}

	public Point getLocation() {
		return location;
	}

	public void setRotation(double rotation) {
		this.rotation = rotation;
	}

	public void rotate(double rotation) {
		this.rotation += rotation;
	}

	public double getRotation() {
		return rotation;
	}

	public double getWheelSpeed() {
		if(this.type == RobotType.PREDATOR_RED) {
			return SimulationSettings.predatorSpeed;
		} else {
			return SimulationSettings.preySpeed;
		}
	}
}
