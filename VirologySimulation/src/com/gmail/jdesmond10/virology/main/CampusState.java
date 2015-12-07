/**
 * 
 */
package com.gmail.jdesmond10.virology.main;

import java.util.Collection;

import sim.engine.SimState;

/**
 * This simulation is being written for a project at WPI's BCB100x Course. The
 * goal is to run a simulation in MASON which breaks the assumption of
 * homogeneous mixing by having agents follow a college class schedule.
 * 
 * A CampusState is the main object representing the state of the simulation.
 * See {@link SimState} for details about what this class can do.
 * 
 * @author Josh Desmond
 */
public class CampusState extends SimState {

	private static final long serialVersionUID = 1L;

	private GlobalCourseSchedule globalCourseSchedule;
	private Collection<Student> studentList;

	public CampusState(long seed) {
		super(seed);
	}

	/**
	 * Launches the simulation and system exits when the program is done.
	 */
	public static void main(String[] args) {
		doLoop(CampusState.class, args);
		System.exit(0);
	}

	@Override
	/*
	 * Called before simulation is run. Sets up all stuff in the simulation.
	 * 
	 * (non-Javadoc)
	 * 
	 * @see sim.engine.SimState#start()
	 */
	public void start() {
		super.start();

		SimulationStarter simStarter = new SimulationStarter(this.random, this.schedule);
		simStarter.createAndScheduleStudents();
		globalCourseSchedule = simStarter.createGlobalSchedule();
	}

	public GlobalCourseSchedule getGlobalCourseSchedule() {
		return this.globalCourseSchedule;
	}

	// TODO students should be able to ask CampusState for a collection of adjacent students.


}
