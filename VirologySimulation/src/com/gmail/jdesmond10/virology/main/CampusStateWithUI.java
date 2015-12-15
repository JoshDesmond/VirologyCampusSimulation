package com.gmail.jdesmond10.virology.main;

import javax.swing.JFrame;

import sim.display.Console;
import sim.display.Controller;
import sim.display.Display2D;
import sim.display.GUIState;
import sim.engine.SimState;
import sim.portrayal.Inspector;
import sim.portrayal.SimplePortrayal2D;
import sim.portrayal.continuous.ContinuousPortrayal2D;
import sim.portrayal.grid.ValueGridPortrayal2D;
import sim.portrayal.network.NetworkPortrayal2D;
import sim.portrayal.network.SimpleEdgePortrayal2D;
import sim.portrayal.network.SpatialNetwork2D;
import sim.portrayal.simple.CircledPortrayal2D;

public class CampusStateWithUI extends GUIState {

	public Display2D networkDisplay;
	public Display2D lectureGridDisplay;
	public JFrame lectureGridFrame;
	public JFrame networkFrame;
	public ValueGridPortrayal2D gridPortrayal = new ValueGridPortrayal2D();
	public NetworkPortrayal2D networkPortrayal = new NetworkPortrayal2D();
	public ContinuousPortrayal2D studentGridPortrayal = new ContinuousPortrayal2D();

	public Display2D timeDisplay;
	public JFrame timeFrame;
	public SimplePortrayal2D timePortrayal;

	public CampusStateWithUI(SimState state) {
		super(state);
	}

	public static void main(String[] args) {

		CampusStateWithUI vid = new CampusStateWithUI();
		Console c = new Console(vid);
		c.setVisible(true);
	}


	public CampusStateWithUI() {
		super(new CampusState(System.currentTimeMillis()));
	}

	public static String getName() {
		return "BCB Virology Simulation";
	}

	@Override
	public void start() {
		super.start();
		setupPortrayals();
		this.getInspector();
	}


	@Override
	public Inspector getInspector() {
		Inspector s = super.getInspector();
		s.setVolatile(true);
		return s;
	}

	@Override
	public void load(SimState state) {
		super.load(state);
		setupPortrayals();
	}

	public void setupPortrayals() {
		CampusState campusState = (CampusState) this.state;
		// tell the portrayals what to portray and how to portray them
		gridPortrayal.setField(campusState.lectureGrid);

		studentGridPortrayal.setField(campusState.studentGrid);
		CircledPortrayal2D circledPortrayal2D = new CircledPortrayal2D(
				new SimplePortrayal2D());
		circledPortrayal2D.scale = .1;
		studentGridPortrayal.setPortrayalForAll(circledPortrayal2D);

		networkPortrayal.setField(new SpatialNetwork2D(campusState.studentGrid,
				campusState.interactions));
		SimpleEdgePortrayal2D simpleEdgePortrayal2D = new SimpleEdgePortrayal2D();
		networkPortrayal.setPortrayalForAll(simpleEdgePortrayal2D);



		// reschedule the displayer
		networkDisplay.reset();
		lectureGridDisplay.reset();
		timeDisplay.reset();
		// redraw the display
		networkDisplay.repaint();
		lectureGridDisplay.repaint();
		timeDisplay.repaint();
	}

	@Override
	public void init(Controller c) {
		super.init(c);
		networkDisplay = new Display2D(600, 600, this);
		networkDisplay.setClipping(false);
		networkFrame = networkDisplay.createFrame();
		networkFrame.setTitle("BCB Realtime Simulation");
		c.registerFrame(networkFrame); // so the frame appears in the "Display"
		// list

		networkFrame.setVisible(true);
		// display.attach(grid, "Lectures");
		networkDisplay.attach(networkPortrayal, "Network");
		networkDisplay.attach(studentGridPortrayal, "Students");

		lectureGridDisplay = new Display2D(600, 600, this);

		lectureGridDisplay.setClipping(false);
		lectureGridFrame = lectureGridDisplay.createFrame();
		lectureGridFrame.setTitle("BCB Network Simulation");
		lectureGridFrame.setVisible(true);
		lectureGridDisplay.attach(gridPortrayal, "Grid");
		c.registerFrame(lectureGridFrame);

		timeDisplay = new Display2D(600, 100, this);
		timeFrame = timeDisplay.createFrame();
		timeFrame.setTitle("Simulation Time");
		//timeDisplay.attach(timePortrayal, "Time Display");
		c.registerFrame(timeFrame);
	}

	@Override
	public Object getSimulationInspectedObject() {
		return state;
	}

}
