package de.hamster.workbench;

import de.hamster.debugger.model.DebuggerModel;
import de.hamster.compiler.model.CompilerModel;
import de.hamster.lego.model.LegoModel;
import de.hamster.simulation.model.SimulationModel;

/**
 * Diese Klasse ist das Model der Werkbank. Sie erzeugt lediglich die
 * Model-Komponenten von Compiler, Debugger und Simulation und macht diese ueber
 * get-Methoden zugaenglich. Der Editor besitzt kein eigenes Model.
 * 
 * @author Daniel Jasper
 */
public class WorkbenchModel {
	private CompilerModel compilerModel;

	private DebuggerModel debuggerModel;

	private SimulationModel simulationModel;

	/* lego */
	private LegoModel legoModel;

	public WorkbenchModel(boolean simulatorOnly, SimulationModel simModel) {
		if (simulatorOnly) {
			// dibo
			simulationModel = simModel;
		} else {
			simulationModel = new SimulationModel();
		}
		debuggerModel = new DebuggerModel(simulationModel);
		compilerModel = new CompilerModel();
		if (Utils.LEGO) {
			legoModel = new LegoModel(); // lego
		}
	}

	public DebuggerModel getDebuggerModel() {
		return debuggerModel;
	}

	public SimulationModel getSimulationModel() {
		return simulationModel;
	}

	public CompilerModel getCompilerModel() {
		return compilerModel;
	}

	public LegoModel getLegoModel() {
		return legoModel; // lego
	}
}