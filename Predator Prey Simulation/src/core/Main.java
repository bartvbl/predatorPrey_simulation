package core;

import javax.swing.JOptionPane;

import rendering.Renderer;
import simulation.RoundGenerator;
import simulation.RoundState;
import simulation.Simulator;
import simulation.world.World;

public class Main {
	private final Simulator simulator;
	private final World world;
	private final Renderer renderer;
	private boolean isRunning = true;
	private int round = 0;
	public static boolean shouldRender = false;
	
	public Main() {
		this.simulator = new Simulator();
		this.world = new World();
		this.renderer = new Renderer();
	}
	
	private void run() {
		while(true) {
			runSimulation();
			if(!isRunning) {
				break;
			}
		}
		renderer.destroy();
	}
	
	private void runSimulation() {
		world.reset();
		simulator.nextSimulation();
		System.out.println("Round " + round);
		while(isRunning && !simulator.isFinished()) {
			simulator.updateSimulation();
			if(((round % 100) == 0) && (shouldRender)) {				
				renderer.renderFrame();
			}
			if(renderer.isWindowCloseRequested()) {
				isRunning = false;
			}
		}
		round++;
	}

	private void init() {
		try {
			renderer.init();
			renderer.setWorld(world);
			simulator.init();
			simulator.setWorld(world);
		} catch (Exception e) {
			exitOnLaunchError(e);
		}
	}

	private void exitOnLaunchError(Exception e) {
		e.printStackTrace();
		JOptionPane.showMessageDialog(null, "Simulation failed to launch.\nReason: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		renderer.destroy();
		System.exit(0);
	}

	public static void main(String[] args) {
		Main main = new Main();
		main.init();
		main.run();	
	}

}
