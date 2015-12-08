package com.gmail.jdesmond10.virology.main;

import java.util.Collection;

import sim.engine.Schedule;
import ec.util.MersenneTwisterFast;

/**
 * This class is responsible for initializing all of the students in the
 * simulation given the random generator from Campus State. It also schedules
 * the students, meaning CampusState should not have to do any scheduling.
 */
public abstract class AbstractSimulationStarter {

	/**
	 * Random number generator for use when generating anything random about new
	 * Students.
	 */
	protected MersenneTwisterFast random;
	/**
	 * Schedule which each student is to be added to.
	 */
	protected Schedule schedule;
	/**
	 * Collection of students in the simulation. To be created during
	 * {@link #init()}
	 */
	protected Collection<Student> students;
	/** Object representing global schedule. To be created during {@link #init()} */
	protected Object globalSchedule;

	public AbstractSimulationStarter(MersenneTwisterFast random,
			Schedule schedule) {
		this.random = random;
		this.schedule = schedule;
	}

	protected boolean wasInitiated = false;

	/**
	 * This method should be called once and only once for each simulation. It
	 * creates a collection of students, and schedules each one. It also builds
	 * the global schedule for the simulation. After calling init(), you can
	 * call {@link #getGlobalSchedule()} and {@link #getStudentList()} to
	 * populate the fields in the simulation.
	 * 
	 * Any method which extends init() must be sure to set wasInitiated to be
	 * true.
	 * 
	 * @return List of students which can be used for graphical display.
	 */
	public abstract void init();

	/**
	 * This method should be called only after {@link #init()} has been called.
	 * 
	 * @return {@link #students}
	 */
	public Collection<Student> getStudentList() {
		if (!wasInitiated) {
			throw new IllegalStateException(
					"getStudentList called before init() was called");
		}
		return students;
	}

	/**
	 * This method should be called only after {@link #init()} has been called.
	 * 
	 * @return
	 */
	public GlobalCourseSchedule getGlobalSchedule() {
		if (!wasInitiated) {
			throw new IllegalStateException(
					"getGlobalSchedule called before init() was called");
		}

		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Creates a new student, schedules it, and adds it to the list of students.
	 * Should not be called until students has been instantiated.
	 * 
	 * @return
	 */
	protected Student initStudent() {
		Student s = new Student();
		schedule.scheduleRepeating(s);
		this.students.add(s);
		return s;
	}
}
