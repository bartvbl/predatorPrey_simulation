package simulation;

import java.util.Arrays;

import org.lwjgl.input.Keyboard;

import core.SimulationSettings;

import rendering.geom.Point;
import simulation.neural.NeuralNetwork;
import simulation.world.World;
import util.ArrayUtil;

public class NeuralRobotDriver {

	private final NeuralNetwork neuralNetwork;
	private final int controlledRobotID;
	private final World world;
	private final RobotType controlledRobotType;
	private double[] previousNetworkOutput;

	public NeuralRobotDriver(NeuralNetwork neuralNetwork, int robotID, World world) {
		this.neuralNetwork = neuralNetwork;
		this.controlledRobotID = robotID;
		this.world = world;
		this.controlledRobotType = world.getRobotType(robotID);
		previousNetworkOutput = new double[SimulationSettings.neuralNetworkOutputCount];
		Arrays.fill(previousNetworkOutput, 0.5);
	}

	public void simulate() {
		double[] inputAxonValues = previousNetworkOutput;
		
		double[] distanceSensorReadings = DistanceSensorReader.calculateDistanceSensorReadings(controlledRobotID, world);
		inputAxonValues = ArrayUtil.concat(inputAxonValues, distanceSensorReadings);
		
		
		if(controlledRobotType == RobotType.PREDATOR_RED) {
			double[] visionAxonValues = PredatorCamReader.calculatePredatorAxonValues(controlledRobotID, world);
			inputAxonValues = ArrayUtil.concat(inputAxonValues, visionAxonValues);
		}
		
		double[] networkOutput = neuralNetwork.simulate(inputAxonValues);

		if(networkOutput.length != SimulationSettings.neuralNetworkOutputCount) throw new RuntimeException("The neural network returned an invalid number of outputs. Supplied: "+networkOutput.length+" Expected: "+SimulationSettings.neuralNetworkOutputCount);
		this.previousNetworkOutput = networkOutput;		
		
		double deltaLeftWheel = networkOutput[0];
		double deltaRightWheel = networkOutput[1];
		
		world.moveLeftWheel(controlledRobotID, deltaLeftWheel);
		world.moveRightWheel(controlledRobotID, deltaRightWheel);
	}

	public Point getLocation() {
		return world.getRobotLocation(controlledRobotID);
	}

}
