package com.gmail.jdesmond10.virology.main;

import java.util.Collection;

import com.gmail.jdesmond10.virology.time.MeetingTimes;

/**
 * An object representing a set of course meetings, which contains a unique ID,
 * and a set of students in the lecture/class.
 * 
 * Created by Saahil on 12/6/2015.
 */
public class Lecture {
	/**
	 * {@link MeetingTimes}
	 */
	public MeetingTimes time;
	/**
	 * ID which is <b>unique</b> to the class. No other Lecture may share the
	 * same ID in the simulation (Lectures at different times must have
	 * different ID's).
	 */
	public final String ID;
	/**
	 * List of students which are attending this class
	 */
	private Collection<Student> students;

	/**
	 * 
	 * @param time
	 *            MeetingTimes for the class
	 * @param ID
	 *            Unique ID for the entire course. May not overlap with any
	 *            other existing course ID's. There can only be once instance of
	 *            the class with the given ID in a simulation.
	 */
	public Lecture(MeetingTimes time, String ID) {
		this.time = time;
		this.ID = ID;
	}

	/**
	 * adds the given student to the class
	 * 
	 * @param s
	 *            Student to be added
	 */
	public void addStudent(Student student) {
		students.add(student);
	}

	/**
	 * 
	 * @return all students in this class.
	 */
	public Collection<Student> getStudents() {
		return students;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Lecture))
			return false;

		Lecture lecture = (Lecture) o;
		// Return true if they share the same unique ID.
		return !(ID != null ? !ID.equals(lecture.ID) : lecture.ID != null);
	}

	@Override
	public int hashCode() {
		int result = ID.hashCode();
		return result;
	}

	@Override
	public String toString() {
		// SUGGESTION Add list of students to toString();
		return String.format("Course %s ", this.ID);
	}

}
