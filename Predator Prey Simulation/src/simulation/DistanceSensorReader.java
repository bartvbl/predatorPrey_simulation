package simulation;

import java.util.Random;

import core.SimulationSettings;
import rendering.geom.Point;
import simulation.world.World;

public class DistanceSensorReader {
	private static final Random random = new Random(System.nanoTime());

	public static double[] calculateDistanceSensorReadings(int controlledRobotID, World world) {
		//it's all one function due to the many arguments that would have to be passed around.
		
		double[] angles = SimulationSettings.distanceSensorDirections;
		double noiseFactor = SimulationSettings.distanceSensorNoiseFactor;
		double robotAngle = world.getRobotRotation(controlledRobotID);
		double rayRange = SimulationSettings.distanceSensorRange;
		double radius = SimulationSettings.robotRadius;
		
		Point robotLocation = world.getRobotLocation(controlledRobotID);
		Point[] allRobotLocations = world.getAllRobotLocations();
		double[] distanceSensorReadings = new double[angles.length];
		
		for(int distanceSensor = 0; distanceSensor < distanceSensorReadings.length; distanceSensor++) {
			

			double angle = angles[distanceSensor];
			double rayEndX = Math.sin(Math.toRadians(angle - robotAngle)) * rayRange;
			double rayEndY = Math.cos(Math.toRadians(angle - robotAngle)) * rayRange;
			
			double dx = rayEndX;
			double dy = rayEndY;
			
			//clipping
			double slope = (dx == 0) ? dy : dy / dx;
			double rayX = robotLocation.x + dx;
			double rayY = robotLocation.y + dy;
			
			if(rayX < 0) {
				dx -= rayX;
				dy -= slope * rayX;
			}
			if(rayX > SimulationSettings.BOARD_WIDTH) {
				dx -= rayX - SimulationSettings.BOARD_WIDTH;
				dy -= slope * (rayX - SimulationSettings.BOARD_WIDTH);
			}
			
			//recalculating the ray end points as they may have changed in the previous checks
			rayX = robotLocation.x + dx;
			rayY = robotLocation.y + dy;
			
			if(rayY < 0) {
				dx -= rayY / slope;
				dy -= rayY;
			}
			if(rayY > SimulationSettings.BOARD_HEIGHT) {
				dx -= (rayY - SimulationSettings.BOARD_HEIGHT) / slope;
				dy -= rayY - SimulationSettings.BOARD_HEIGHT;
			}
			
			//robot collision detection
			for(int robotID = 0; robotID < allRobotLocations.length; robotID++) {
				if(robotID == controlledRobotID) continue;
				
				Point otherRobotLocation = allRobotLocations[robotID];
				Point relativeOtherLocation = new Point(robotLocation.x - otherRobotLocation.x, robotLocation.y - otherRobotLocation.y);
				
				double a = (dx * dx) + (dy * dy);
				double b = 2 * ((relativeOtherLocation.x * dx) + (relativeOtherLocation.y * dy));
				double c = ((relativeOtherLocation.x * relativeOtherLocation.x) + (relativeOtherLocation.y * relativeOtherLocation.y)) - (radius * radius);
				
				double discriminant = (b*b) - (4*a*c);
				
				//I omit the tangent case here (discriminant == 0)
				if(discriminant >= 0) {
					discriminant = Math.sqrt(discriminant);
					
					double t1 = (-b - discriminant)/(2*a);
					
					if( t1 >= 0 && t1 <= 1 )
					{
						dx = t1 * rayEndX;
						dy = t1 * rayEndY;
					}
				}				
			}
			
			dx += 2 * (random.nextDouble() - 0.5) * noiseFactor;
			dy += 2 * (random.nextDouble() - 0.5) * noiseFactor;

			distanceSensorReadings[distanceSensor] = Math.sqrt((dx*dx) + (dy*dy)) / rayRange;
		}
		return distanceSensorReadings;
	}

}
