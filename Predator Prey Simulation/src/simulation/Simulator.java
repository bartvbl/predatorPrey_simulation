package simulation;

import core.SimulationSettings;
import rendering.geom.Point;
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
	private boolean robotsCollided;

	public void setWorld(World world) {
		this.world = world;
	}
	
	public void init() {
		simulationQueue.init();
	}
	
	public void updateSimulation() {
		predatorDriver.simulate();
		preyDriver.simulate();
		
		if(world.checkCollissions()) {
			robotsCollided = true;
		}
		ticksElapsed++;
		if(ticksElapsed >= SimulationSettings.numRoundTicks) {
			persuitTimedOut = true;
		}
	}

	public boolean isFinished() {
		return robotsCollided | persuitTimedOut;
	}

	public void nextSimulation() {
		if(!isFirstSimulation) {			
			double timeFitness = (double) ticksElapsed / (double) SimulationSettings.numRoundTicks;
			Point predatorLocation = predatorDriver.getLocation();
			Point preyLocation = preyDriver.getLocation();
			double distanceFitness = 0.5 - 0.5 * (predatorLocation.distanceTo(preyLocation) / SimulationSettings.BOARD_WIDTH);
			double roundFitness = timeFitness + distanceFitness;
			double predatorFitness = 1.5 - roundFitness;
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
		world.reset();
		this.ticksElapsed = 0;
		persuitTimedOut = false;
		robotsCollided = false;
	}

	public void registerRoundOutcome() {
		
	}
}
