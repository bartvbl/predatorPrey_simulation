package simulation;

import simulation.neural.NeuralNetwork;
import simulation.population.Population;
import simulation.population.PopulationGenerator;
import util.Queue;

public class SimulationQueue {
	private Queue<Individual> predatorRoundQueue = new Queue<Individual>();
	private Queue<Individual> preyRoundQueue = new Queue<Individual>();
	private Individual selectedPredator;
	private Individual selectedPrey;
	private RoundGenerator predatorRoundHandler;
	private RoundGenerator preyRoundHandler;
	
	public void init() {
		predatorRoundQueue.clear();
		preyRoundQueue.clear();
		Population startPredatorPopulation = PopulationGenerator.generatePredatorPopulation();
		Population startPreyPopulation = PopulationGenerator.generatePreyPopulation();
		predatorRoundHandler = new RoundGenerator(startPredatorPopulation);
		preyRoundHandler = new RoundGenerator(startPreyPopulation);
		nextBatch();
	}

	public void next() {
		selectedPredator = predatorRoundQueue.dequeue();
		selectedPrey = preyRoundQueue.peek();
		if(predatorRoundQueue.isEmpty()) {
			predatorRoundQueue.enqueueAll(predatorRoundHandler.getIndividuals());
			selectedPrey = preyRoundQueue.dequeue();
		}
		if(preyRoundQueue.isEmpty()) {
			nextBatch();
		}
	}
	
	private void nextBatch() {
		predatorRoundHandler.nextBatch();
		predatorRoundQueue.enqueueAll(predatorRoundHandler.getIndividuals());
		preyRoundHandler.nextBatch();
		preyRoundQueue.enqueueAll(preyRoundHandler.getIndividuals());
	}
	
	public void registerRoundOutcome(double predatorFitness, double preyFitness) {
		selectedPredator.registerFitnessValue(predatorFitness);
		selectedPrey.registerFitnessValue(preyFitness);
	}

	public NeuralNetwork getCurrentPredatorNetwork() {
		return selectedPredator.neuralNetwork;
	}

	public NeuralNetwork getCurrentPreyNetwork() {
		return selectedPrey.neuralNetwork;
	}
}
