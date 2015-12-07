package com.gmail.jdesmond10.virology.main;

import java.util.Collection;
import java.util.LinkedList;

import sim.engine.Schedule;
import ec.util.MersenneTwisterFast;

/**
 * This class is responsible for initializing all of the students in the
 * simulation given the random generator from Campus State. It also schedules
 * the students, meaning CampusState should not have to do any scheduling.
 * 
 * TODO probably should be refactored into an abstract class if multiple
 * versions of simulation starts are going to be defined.
 */
public class SimulationStarter {

	/**
	 * Random number generator for use when generating anything random about new
	 * Students.
	 */
	protected MersenneTwisterFast random;

	/**
	 * Schedule which each student is to be added to.
	 */
	protected Schedule schedule;

	public SimulationStarter(MersenneTwisterFast random, Schedule schedule) {
		this.random = random;
		this.schedule = schedule;
	}

	/**
	 * This method should be called once and only once for each simulation. It
	 * creates a queue of students, and schedules each one.
	 * 
	 * TODO the current implementation of this method is just a stub. Needs to
	 * be written.
	 * 
	 * @return List of students which can be used for graphical display.
	 */
	public Collection<Student> createAndScheduleStudents() {
		// Create Students
		Student s = new Student();

		// Schedule Students
		schedule.scheduleRepeating(s);

		// build list of students and return it.
		Collection<Student> studentCollection = new LinkedList<Student>();
		studentCollection.add(s);
		return studentCollection;
	}

	/**
	 * This method should be called only after
	 * {@link #createAndScheduleStudents()} has been called. (This is in case
	 * future versions of SimulationStarter need to build the global schedule as
	 * the students are being instantiated.
	 * 
	 * @return GlobalCourseSchedule of all students.
	 */
	public GlobalCourseSchedule getGlobalSchedule() {
		// TODO Auto-generated method stub
		return null;
	}
}
