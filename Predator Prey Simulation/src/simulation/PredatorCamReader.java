package simulation;

import core.SimulationSettings;
import rendering.geom.Point;
import simulation.world.World;

public class PredatorCamReader {

	public static double[] calculatePredatorAxonValues(int controlledRobotID, World world) {
		int visionNeuronCount = SimulationSettings.neuralNetworkPredatorVisionNeurons;
		double[] visionValues = new double[visionNeuronCount];
		Integer[] preyRobotIDs = world.getRobotsByType(RobotType.PREY_BLUE);
		Point robotLocation = world.getRobotLocation(controlledRobotID);
		double visionRange = SimulationSettings.neuralNetworkPredatorVisionAngle;
		
		for(int preyRobotID : preyRobotIDs) {
			Point preyLocation = world.getRobotLocation(preyRobotID);
			double preyAngle = calculateNormalizedAngle(robotLocation, preyLocation);
			
			//calculating the angle relative to the heading of the predator
			preyAngle -= world.getRobotRotation(controlledRobotID);
			
			//abort if the prey is not in view
			if((preyAngle > visionRange / 2) && (preyAngle < (360 - visionRange/2))) {
				continue;
			}
			
			//get the angle in a range from 0 to visionAngle
			preyAngle += visionRange / 2;
			
			//normalize the value 0 -> 1
			preyAngle /= visionRange;
			
			//gets the angle in a range from 0 -> visionNeuronCount + 1
			preyAngle *= (double) (visionNeuronCount + 1);
			
			//the range for every value is now +- 0.5
			preyAngle -= 0.5;
			
			//rounding gives the index for the vision neuron array
			int angleIndex = round(preyAngle);
			
			//eliminating possible out of bounds results due to rounding
			angleIndex = Math.max(0, Math.min(angleIndex, visionNeuronCount - 1));
			
			visionValues[angleIndex] = 1;
		}
		
		return visionValues;
	}

	private static int round(double preyAngle) {
		return (int) Math.floor(preyAngle + 0.5d);
	}

	private static double calculateNormalizedAngle(Point robotLocation, Point preyLocation) {
		double angle = Math.toDegrees(Math.atan2(preyLocation.x - robotLocation.x, preyLocation.y - robotLocation.y));
		
		if(angle < 0) {
	        angle += 360;
	    }
		
		return angle;
	}

}
