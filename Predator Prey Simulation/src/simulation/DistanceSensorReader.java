package simulation;

import java.util.Random;

import core.SimulationSettings;
import rendering.geom.Point;
import simulation.world.World;

public class DistanceSensorReader {
	private static final Random random = new Random(System.nanoTime());

	public static double[] calculateDistanceSensorReadings(int controlledRobotID, World world) {
		double[] angles = SimulationSettings.distanceSensorDirections;
		double noiseFactor = SimulationSettings.distanceSensorNoiseFactor;
		double robotAngle = world.getRobotRotation(controlledRobotID);
		double rayRange = SimulationSettings.distanceSensorRange;
		double radius = SimulationSettings.robotRadius;
		
		Point robotLocation = world.getRobotLocation(controlledRobotID);
		Point[] allRobotLocations = world.getAllRobotLocations();
		double[] distanceSensorReadings = new double[angles.length];
		
		for(int distanceSensor = 0; distanceSensor < distanceSensorReadings.length; distanceSensor++) {
			if(distanceSensor == controlledRobotID) continue;

			double angle = angles[distanceSensor];
			double rayEndX = Math.sin(Math.toRadians(angle + robotAngle)) * rayRange;
			double rayEndY = Math.cos(Math.toRadians(angle + robotAngle)) * rayRange;
			
			double dx = rayEndX - robotLocation.x;
			double dy = rayEndY - robotLocation.y;
			
			for(int robotID = 0; robotID < allRobotLocations.length; robotID++) {
				Point otherRobotLocation = allRobotLocations[robotID];
				Point relativeOtherLocation = new Point(robotLocation.x - otherRobotLocation.x, robotLocation.y - otherRobotLocation.y);
				
				double a = (dx * dx) + (dy * dy);
				double b = 2 * ((relativeOtherLocation.x * dx) + (relativeOtherLocation.y * dy));
				double c = ((relativeOtherLocation.x * relativeOtherLocation.x) + (relativeOtherLocation.y * relativeOtherLocation.y)) - (radius * radius);
				
				double discriminant = (b*b) - (4*a*c);
				
				//I omit the tangent case here (discriminant == 0)
				if(discriminant > 0) {
					double t1 = (-b - discriminant)/(2*a);
					double t2 = (-b + discriminant)/(2*a);
					
					if( t1 >= 0 && t1 <= 1 )
					{
						System.out.println("("+dx+", "+dy+", "+t1+")");
					}
				}				
			}
			
			dx += random.nextGaussian() * noiseFactor;
			dy += random.nextGaussian() * noiseFactor;
			
			distanceSensorReadings[distanceSensor] = Math.sqrt((dx*dx) + (dy*dy))  / rayRange;
		}
		
		return distanceSensorReadings;
	}

}
