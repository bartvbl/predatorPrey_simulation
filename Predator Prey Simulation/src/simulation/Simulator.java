package simulation;

import core.SimulationSettings;
import simulation.neural.NeuralNetwork;
import simulation.world.World;

public class Simulator {

	private World world;
	private int ticksElapsed = 0;
	private boolean persuitTimedOut = false;
	private SimulationQueue simulationQueue = new SimulationQueue();
	private NeuralRobotDriver predatorDriver;
	private NeuralRobotDriver preyDriver;
	private boolean isFirstSimulation = true;

	public void setWorld(World world) {
		this.world = world;
	}
	
	public void init() {
		simulationQueue.init();
	}
	
	public void updateSimulation() {
		predatorDriver.simulate();
		preyDriver.simulate();
		
		world.checkCollissions();
		ticksElapsed++;
		if(ticksElapsed >= SimulationSettings.numRoundTicks) {
			persuitTimedOut = true;
		}
	}

	public boolean isFinished() {
		return RoundEndVerifyer.hasRoundFinished(world, persuitTimedOut);
	}

	public void nextSimulation() {
		if(!isFirstSimulation) {			
			double roundFitness = (double) ticksElapsed / (double) SimulationSettings.numRoundTicks;
			double predatorFitness = 1 - roundFitness;
			double preyFitness = roundFitness;
			simulationQueue.registerRoundOutcome(predatorFitness, preyFitness);
		}
		
		persuitTimedOut = false;
		isFirstSimulation = false;
		
		reset();
				
		simulationQueue.next();
		
		spawnRobotDrivers();
		
		world.initRobotLocations();
	}

	private void spawnRobotDrivers() {		
		NeuralNetwork predatorNetwork = simulationQueue.getCurrentPredatorNetwork();
		int robotID = world.spawnRobot(RobotType.PREDATOR_RED);
		predatorDriver = new NeuralRobotDriver(predatorNetwork, robotID, world);
		
		NeuralNetwork preyNetwork = simulationQueue.getCurrentPreyNetwork();
		robotID = world.spawnRobot(RobotType.PREY_BLUE);
		preyDriver = new NeuralRobotDriver(preyNetwork, robotID, world);
	}

	private void reset() {
		this.ticksElapsed = 0;
		persuitTimedOut = false;
	}
}
