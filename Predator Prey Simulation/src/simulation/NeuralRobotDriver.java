package simulation;

import java.util.Arrays;

import core.SimulationSettings;

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
		Arrays.fill(new double[SimulationSettings.numNeuralNetworkOutputs], 0.5);
	}

	public void simulate() {
		double[] inputAxonValues = previousNetworkOutput;
		
		double[] distanceSensorReadings = DistanceSensorReader.calculateDistanceSensorReadings(controlledRobotID, world);
		inputAxonValues = ArrayUtil.concat(inputAxonValues, distanceSensorReadings);
		
		if(controlledRobotType == RobotType.PREDATOR_RED) {
			double[] visionAxonValues = PredatorCamReader.calculatePredatorAxonValues(controlledRobotID, world);
			inputAxonValues = ArrayUtil.concat(distanceSensorReadings, visionAxonValues);
		}
		
		double[] networkOutput = neuralNetwork.simulate(inputAxonValues);
		
		assert(networkOutput.length == SimulationSettings.numNeuralNetworkOutputs);
		this.previousNetworkOutput = networkOutput;		
		
		double deltaLeftWheel = networkOutput[0] - networkOutput[1];
		double deltaRightWheel = networkOutput[2] - networkOutput[3];
		world.moveLeftWheel(controlledRobotID, deltaLeftWheel);
		world.moveRightWheel(controlledRobotID, deltaRightWheel);
	}

}
