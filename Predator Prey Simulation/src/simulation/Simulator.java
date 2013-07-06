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
	private boolean robotsCollided = false;

	public void setWorld(World world) {
		this.world = world;
	}
	
	public void init() {
		simulationQueue.init();
	}
	
	public void updateSimulation() {
		double normalisedTicks = (double) this.ticksElapsed / (double) SimulationSettings.numRoundTicks;
		predatorDriver.simulate(normalisedTicks);
		preyDriver.simulate(normalisedTicks);
		
		if(world.checkCollissions()) {
			robotsCollided = true;
		}
		ticksElapsed++;
		if(ticksElapsed >= SimulationSettings.numRoundTicks) {
			persuitTimedOut = true;
		}
	}

	public boolean isFinished() {
		return robotsCollided || persuitTimedOut;
	}

	public void nextSimulation() {
		if(!isFirstSimulation) {			
			double roundFitness = (double) ticksElapsed / (double) SimulationSettings.numRoundTicks;
			double predatorFitness = 1 - roundFitness;
			double preyFitness = roundFitness;
			if(ticksElapsed <= SimulationSettings.ticksPreyCanWin) {
				//predatorFitness = roundFitness;
				//preyFitness = SimulationSettings.preyWinFitnessBoost * (1 - roundFitness);
			}
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
}
