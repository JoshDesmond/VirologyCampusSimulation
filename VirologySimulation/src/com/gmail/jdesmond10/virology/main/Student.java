package com.gmail.jdesmond10.virology.main;

import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

import sim.engine.SimState;
import sim.engine.Steppable;

public class Student implements Steppable {

	private static final long serialVersionUID = 1L;
	public boolean isSick;
	private CourseSchedule schedule;
	/**
	 * Optional field in Student. If it exists, it will be used for toString()
	 * output.
	 */
	private int ID;

	public Student() {
		this.isSick = false;
		this.schedule = new CourseSchedule();
	}

	/**
	 * Optional constructor which creates a student with an ID. If an ID is
	 * given, the toString() method will print out the students ID.
	 * 
	 * @param ID
	 */
	public Student(int ID) {
		this.schedule = new CourseSchedule();
		this.ID = ID;
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

		Lecture currentLecture = l.get();

		// If in class
		Collection<Student> neighbors = currentLecture.getStudents();

		stepSicknessOdds(neighbors, state);
	}

	@Deprecated
	private void tempMethod(long simTime, CampusState state) {
		// FIXME Delete this am just testing
		if (false) {
			System.out.println(state.getCurrentRealTime() + ": "
					+ state.getGlobalLectures().toString());

			state.kill();
		}
	}

	/**
	 * Given a list of neighbors and an algorithm, determines if this student
	 * will get sick.
	 * 
	 * @param virusAlgorithm
	 *            The algorithm from the state of the simulation which will be
	 *            used to calculate sickness.
	 * @param neighbors
	 *            a list of adjacent students (or list of students in class with
	 *            this)
	 * @param random
	 * 
	 */
	private void stepSicknessOdds(Collection<Student> neighbors,
			CampusState state) {

		for (Iterator<Student> iterator = neighbors.iterator(); iterator
				.hasNext();) {
			Student otherStudent = iterator.next();

			if (otherStudent.equals(this)) {
				continue;
			}

			if (CampusState.DEBUG) {
				System.out.println(String.format("checking %s with %s",
						otherStudent.toString(), this.toString()));
			}

			if (state.virusAlgorithm.simulateContact(this, otherStudent, state)) {
				this.isSick = true;
			}

			// TODO Auto-generated method stub

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

	/**
	 * Registers the student to be at the current lecture.
	 * 
	 * Throws an error if there is a time conflict.
	 * 
	 * @param lecture
	 *            Lecture to add student to.
	 */
	public void registerForClass(Lecture lecture) {
		boolean classRegisteredStudent = lecture.getStudents().contains(this);

		if (!classRegisteredStudent) {
			lecture.addStudent(this);
		}

		// TODO this logic is redundant AF. fix it. Look through to see if
		// addStudent handles this part below.
		boolean isAlreadyRegistered = schedule.getLectures().contains(lecture);

		if (!isAlreadyRegistered) {
			schedule.addLecture(lecture);
		} else {
			if (CampusState.DEBUG) {
				System.out
				.println("A call was made to registerForClass in Student,"
						+ " when the student already had that class on their "
						+ "schedule...");
			}
		}

		if (classRegisteredStudent && isAlreadyRegistered) {
			throw new IllegalArgumentException(
					String.format("A call to registerForClass was given when the "
							+ "student is already registered for the class, and when"
							+ " the class already had the student registered."));
			// SUGGESTION add some more details to the error message if needed.
		}
	}

	@Override
	public String toString() {
		if (ID == 0) {
			super.toString();
		}

		return String.valueOf(this.ID);
	}
}
