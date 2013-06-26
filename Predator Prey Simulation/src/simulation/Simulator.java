package simulation;

import core.SimulationSettings;
import simulation.neural.NeuralNetwork;
import simulation.world.World;

public class Simulator {

	private World world;
	private int ticksElapsed = 0;
	private boolean persuitTimedOut = false;
	private SimulationQueue simulationQueue = new SimulationQueue();
	private NeuralRobotDriver[] predatorDrivers;
	private NeuralRobotDriver[] preyDrivers;

	public void setWorld(World world) {
		this.world = world;
	}
	
	public void init() {
		simulationQueue.init();
	}
	
	public void updateSimulation() {
		if(ticksElapsed >= SimulationSettings.numRoundTicks) {
			persuitTimedOut = true;
		}
		for(NeuralRobotDriver predatorDriver : predatorDrivers) {
			predatorDriver.update();
		}
		for(NeuralRobotDriver preyDriver : preyDrivers) {
			preyDriver.update();
		}
		world.checkCollissions();
		ticksElapsed++;
	}

	public boolean isFinished() {
		return RoundEndVerifyer.hasRoundFinished(world, persuitTimedOut);
	}

	public void reset() {
		this.ticksElapsed = 0;
		persuitTimedOut = false;
	}

	public void nextSimulation() {
		simulationQueue.next();
		
		NeuralNetwork[] predatorNetworks = simulationQueue.getCurrentPredatorNetworks();
		NeuralNetwork[] preyNetworks = simulationQueue.getCurrentPreyNetworks();
		predatorDrivers = new NeuralRobotDriver[predatorNetworks.length];
		preyDrivers = new NeuralRobotDriver[preyNetworks.length];
		
		for(int i = 0; i < predatorNetworks.length; i++) {
			int robotID = world.spawnRobot(RobotType.PREDATOR_RED);
			predatorDrivers[i] = new NeuralRobotDriver(predatorNetworks[i], robotID, world);
		}
		for(int i = 0; i < preyNetworks.length; i++) {
			int robotID = world.spawnRobot(RobotType.PREY_BLUE);
			preyDrivers[i] = new NeuralRobotDriver(preyNetworks[i], robotID, world);
		}
		
		world.initRobotLocations();
	}
}
