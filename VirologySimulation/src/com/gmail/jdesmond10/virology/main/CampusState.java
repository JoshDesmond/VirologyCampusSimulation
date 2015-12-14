/**
 * 
 */
package com.gmail.jdesmond10.virology.main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Iterator;

import sim.engine.SimState;
import sim.field.continuous.Continuous2D;
import sim.field.grid.DoubleGrid2D;
import sim.field.network.Network;
import sim.util.Double2D;

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
	/** A representation of the state of simulation used for displays. */
	public DoubleGrid2D lectureGrid;
	public VirusAlgorithm virusAlgorithm = new VirusAlgorithm();

	public Network interactions = new Network(true);
	public Continuous2D studentGrid;

	/**
	 * @see sim.engine.SimState#SimState(long seed)
	 */
	public CampusState(long seed) {
		super(seed);

		// simStarter = new SimpleSimulationStarter(random, schedule);
		simStarter = new RandomSimulationStarter(random, schedule);

		// Handle Initializing start time.
		this.timeSimulationUtil = new TimeSimulationUtil(STARTING_DATE);
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

		// Handle Initializing Students and globalSchedule
		simStarter.init();

		this.studentList = simStarter.getStudentList();
		this.globalCourseSchedule = simStarter.getGlobalSchedule();

		studentGrid = new Continuous2D(1, 30, 30);

		lectureGrid = globalCourseSchedule.generateLectureGrid();
		a = 1;
		b = 0;

		for (Iterator<Student> iterator = studentList.iterator(); iterator
				.hasNext();) {
			Student student = iterator.next();

			interactions.addNode(student);
			Double2D nextLocation = nextLocation();
			studentGrid.setObjectLocation(student, nextLocation);
		}

	}

	private int a = 1; // TODO DELETE THESE
	private int b = 0; // TODO JUST TESTIGN DELETE

	private Double2D nextLocation() {
		if (a % 25 == 0) { // TODO actually build this with another class and
			// correctly.
			a = 0;
			b++;
		}

		a++;
		return new Double2D(a, b);
	}

	/** Returns a string representing the current time */
	public String getCurrentRealTime() {
		LocalDateTime currentLocalDateTime = timeSimulationUtil
				.timeOfSimulation(schedule.getSteps());
		return currentLocalDateTime.format(DateTimeFormatter.ISO_DATE_TIME);
	}

	public double getPercentageSick() {
		if (globalCourseSchedule == null) {
			return 0;
		}
		int totalCount = 0;
		int sickCount = 0;
		for (Lecture l : globalCourseSchedule.getLectures()) {
			for (Student s : l.getStudents()) {
				totalCount++;
				if (s.getIsSick()) {
					sickCount++;
				}
			}
		}

		return (double) (sickCount) / (double) (totalCount);
	}

	public void printSerializeNetwork() {
		DataPrinter.printSerializeNetwork(interactions);
	}
}
