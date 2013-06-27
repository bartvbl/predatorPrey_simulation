package simulation;

import simulation.neural.NeuralNetwork;
import simulation.population.Population;
import simulation.population.PopulationGenerator;
import util.Queue;

public class SimulationQueue {
	private Queue<Individual[]> predatorRoundQueue = new Queue<Individual[]>();
	private Queue<Individual[]> preyRoundQueue = new Queue<Individual[]>();
	private Individual[] selectedPredators;
	private Individual[] selectedPrey;
	private RoundHandler roundHandler;
	
	public void init() {
		predatorRoundQueue.clear();
		preyRoundQueue.clear();
		Population startPredatorPopulation = PopulationGenerator.generatePredatorPopulation();
		Population startPreyPopulation = PopulationGenerator.generatePreyPopulation();
		roundHandler = new RoundHandler(startPredatorPopulation, startPreyPopulation);
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
		roundHandler.nextBatch();
		predatorRoundQueue.enqueueAll(roundHandler.getPredatorIndividuals());
		preyRoundQueue.enqueueAll(roundHandler.getPreyIndividuals());
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
