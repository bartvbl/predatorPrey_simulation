package simulation;

import core.SimulationSettings;
import simulation.neural.NeuralNetwork;
import simulation.population.Population;
import simulation.population.PopulationGenerator;
import util.Queue;

public class SimulationQueue {
	private Queue<Individual[]> predatorRoundQueue = new Queue<Individual[]>();
	private Queue<Individual[]> preyRoundQueue = new Queue<Individual[]>();
	private Individual[] selectedPredators;
	private Individual[] selectedPrey;
	private RoundGenerator predatorRoundHandler;
	private RoundGenerator preyRoundHandler;
	
	public void init() {
		predatorRoundQueue.clear();
		preyRoundQueue.clear();
		Population startPredatorPopulation = PopulationGenerator.generatePredatorPopulation();
		Population startPreyPopulation = PopulationGenerator.generatePreyPopulation();
		predatorRoundHandler = new RoundGenerator(startPredatorPopulation, SimulationSettings.predatorPackSize);
		preyRoundHandler = new RoundGenerator(startPreyPopulation, SimulationSettings.preyPackSize);
		nextBatch();
	}

	public void next() {
		selectedPredators = predatorRoundQueue.dequeue();
		selectedPrey = preyRoundQueue.dequeue();
		if(predatorRoundQueue.isEmpty()) {
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
		for(Individual predator : selectedPredators) {
			predator.registerFitnessValue(predatorFitness);
		}
		for(Individual prey : selectedPrey) {
			prey.registerFitnessValue(predatorFitness);
		}
	}

	public NeuralNetwork[] getCurrentPredatorNetworks() {
		NeuralNetwork[] networks = new NeuralNetwork[selectedPredators.length];
		for(int i = 0; i < networks.length; i++) {
			networks[i] = selectedPredators[i].neuralNetwork;
		}
		return networks;
	}

	public NeuralNetwork[] getCurrentPreyNetworks() {
		NeuralNetwork[] networks = new NeuralNetwork[selectedPrey.length];
		for(int i = 0; i < networks.length; i++) {
			networks[i] = selectedPrey[i].neuralNetwork;
		}
		return networks;
	}
}
