/**
 * 
 */
package com.gmail.jdesmond10.virology.main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

import sim.engine.SimState;

import com.gmail.jdesmond10.virology.data.RandomSimulationStarter;
import com.gmail.jdesmond10.virology.time.TimeSimulationUtil;

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
	/** All simulations will start at 2015/10/27 0:00:00. */
	private static final LocalDateTime STARTING_DATE = LocalDateTime.of(2015,
			10, 27, 0, 0, 0);
	/** Set to true to enable sysout statements */
	public static final boolean DEBUG = false;
	/** Representation of active classes. */
	private GlobalCourseSchedule globalCourseSchedule;
	/** List of students. Primarily intended to be used with displays. */
	private Collection<Student> studentList;
	/** Utility class, which students need to use in their step method. */
	public TimeSimulationUtil timeSimulationUtil;
	/** */
	private final AbstractSimulationStarter simStarter;

	/**
	 * @see sim.engine.SimState#SimState(long seed)
	 */
	public CampusState(long seed) {
		super(seed);

		simStarter = new RandomSimulationStarter(random, schedule);
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

		// Handle Initializing start time.
		this.timeSimulationUtil = new TimeSimulationUtil(STARTING_DATE);

		// Handle Initializing Students and globalSchedule
		simStarter.init();

		this.studentList = simStarter.getStudentList();
		System.out.println(studentList);
		this.globalCourseSchedule = simStarter.getGlobalSchedule();
	}

	public Collection<Student> getStudents() {
		return this.studentList;
	}

	/** Returns a string representing the current time */
	public String getCurrentRealTime() {
		LocalDateTime currentLocalDateTime = timeSimulationUtil
				.timeOfSimulation(schedule.getSteps());
		return currentLocalDateTime.format(DateTimeFormatter.ISO_DATE_TIME);
	}
}
