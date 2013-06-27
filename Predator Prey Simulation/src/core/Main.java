package core;

import javax.swing.JOptionPane;

import rendering.Renderer;
import simulation.Simulator;
import simulation.world.World;

public class Main {
	private final Simulator simulator;
	private final World world;
	private final Renderer renderer;
	private boolean isRunning = true;
	
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
		while(isRunning) {
			simulator.updateSimulation();
			renderer.renderFrame();
			if(renderer.isWindowCloseRequested()) {
				isRunning = false;
			}
		}
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
