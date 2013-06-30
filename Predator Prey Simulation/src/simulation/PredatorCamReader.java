package simulation;

import core.SimulationSettings;
import rendering.geom.Point;
import simulation.world.World;

public class PredatorCamReader {

	public static double[] calculatePredatorAxonValues(int controlledRobotID, World world) {
		int visionNeuronCount = SimulationSettings.neuralNetworkPredatorVisionNeurons;
		double visionRange = SimulationSettings.neuralNetworkPredatorVisionAngle;
		double camRange = SimulationSettings.neuralNetworkPredatorVisionRange;
		double[] visionValues = new double[visionNeuronCount];
		Integer[] preyRobotIDs = world.getRobotsByType(RobotType.PREY_BLUE);
		Point robotLocation = world.getRobotLocation(controlledRobotID);
		double robotRotation = world.getRobotRotation(controlledRobotID);
		
		for(int preyRobotID : preyRobotIDs) {
			Point preyLocation = world.getRobotLocation(preyRobotID);
			
			if(preyLocation.distanceTo(robotLocation) > camRange) {
				continue;
			}
			
			double preyAngle = calculateNormalizedAngle(robotLocation, robotRotation, preyLocation);
			//abort if the prey is not in view
			if((preyAngle > visionRange / 2) && (preyAngle < -(visionRange/2))) {
				continue;
			}
			
			//get the angle in a range from 0 to visionAngle
			preyAngle -= visionRange / 2;
			preyAngle *= -1;
			
			//normalize the value 0 -> 1
			preyAngle /= visionRange;
			
			//gets the angle in a range from 0 -> visionNeuronCount + 1
			preyAngle *= (double) (visionNeuronCount + 1);
			
			//the range for every value is now +- 0.5
			preyAngle -= 0.5;
			
			//rounding gives the index for the vision neuron array
			int angleIndex = round(preyAngle);
			
			//eliminating possible out of bounds results due to rounding
			if((angleIndex >= 0) && (angleIndex < visionNeuronCount)) {
				visionValues[angleIndex] = 1;
			}
			
		}
		return visionValues;
	}

	private static int round(double preyAngle) {
		return (int) Math.floor(preyAngle + 0.5d);
	}

	private static double calculateNormalizedAngle(Point robotLocation, double robotRotation, Point preyLocation) {
		double dx = preyLocation.x - robotLocation.x;
		double dy = preyLocation.y - robotLocation.y;
		
		double robotHeadingX = Math.sin(Math.toRadians(-robotRotation));
		double robotHeadingY = Math.cos(Math.toRadians(-robotRotation));
		
		double angle = Math.atan2(dy, dx) - Math.atan2(robotHeadingY, robotHeadingX);
		double angleDegrees = Math.toDegrees(angle);
		return angleDegrees;
	}

}
