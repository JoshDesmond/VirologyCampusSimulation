package com.gmail.jdesmond10.virology.main;

import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

import sim.engine.SimState;
import sim.engine.Steppable;

public class Student implements Steppable {

	private static final long serialVersionUID = 1L;
	private boolean isSick;
	private CourseSchedule schedule;

	public Student() {
		this.isSick = false;
		this.schedule = new CourseSchedule();
	}

	@Override
	/*
	 * This method is called every step of the simulation. It will access
	 * campusState to determine which students are adjacent to itself, and use
	 * this information to alter the state of this student.
	 * 
	 * (non-Javadoc)
	 * 
	 * @see sim.engine.Steppable#step(sim.engine.SimState)
	 */
	public void step(SimState campusState) {
		// get campusState and simulation Time
		CampusState state = (CampusState) campusState;
		final long simTime = state.schedule.getSteps();

		// Now determine neighboring students.
		Optional<Lecture> l = schedule.getLectureAtTime(simTime,
				state.timeSimulationUtil);

		// If not in class
		if (!l.isPresent())
			return;

		// If in class
		Collection<Student> neighbors = l.get().getStudents();

		stepSicknessOdds(neighbors);
	}

	/**
	 * Given a list of neighbors, determines if this student will get sick.
	 * 
	 * @param neighbors
	 *            a list of adjacent students (or list of students in class with
	 *            this)
	 */
	private void stepSicknessOdds(Collection<Student> neighbors) {

		for (Iterator iterator = neighbors.iterator(); iterator.hasNext();) {
			Student otherStudent = (Student) iterator.next();
			// TODO Auto-generated method stub
			System.out.println(String.format("checking %s with %s",
					otherStudent.toString(), this.toString()));

			// Do something with student. SUGGESTION Perhaps we should use a
			// strategy pattern here. Look that up it's actually kind of
			// interesting. Things to use here are #student.isSick(), and uhh.
			// that's about it.
		}
	}

	/**
	 * @return true if the student is sick.
	 */
	public boolean getIsSick() {
		return isSick;
	}

}
