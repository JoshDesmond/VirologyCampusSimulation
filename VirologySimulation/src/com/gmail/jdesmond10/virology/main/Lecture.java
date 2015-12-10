package com.gmail.jdesmond10.virology.main;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

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
	private Set<Student> students;
	/** Maximum number of students that can join the class. default value is 0. */
	private final int maxCapicity;

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
		this.students = new HashSet<Student>();
		this.maxCapicity = 0;
	}

	/**
	 * @see #Lecture(MeetingTimes, String)
	 * @param maxCapicity
	 *            Maximum number of students that can be registered in the
	 *            class.
	 */
	public Lecture(MeetingTimes time, String ID, int maxCapicity) {
		this.students = new HashSet<Student>();
		this.maxCapicity = maxCapicity;
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
		if (this.getNumberOfStudents() >= this.getMaxCapicity()) {
			throw new IllegalArgumentException(
					String.format(
							"Too many students added to class. Capicity of %s exceeded.",
							this.getMaxCapicity()));
		}

		boolean isNew = students.add(student);
		if (isNew) {
			student.registerForClass(this);
		}

		if (!isNew && CampusState.DEBUG) {
			System.out
			.println(String
					.format("Warning. Attempted to add a duplicate student to the lecture %s",
							this));
		}
	}

	/**
	 * 
	 * @return all students in this class.
	 */
	public Collection<Student> getStudents() {
		return students;
	}

	/**
	 * @return the number of students in the class.
	 */
	protected int getNumberOfStudents() {
		return this.students.size();
	}

	/**
	 * 
	 * @see #hasRoomForAnotherStudent()
	 * @return maxCapicty of class.
	 */
	public int getMaxCapicity() {
		return maxCapicity;
	}

	/**
	 * 
	 * @return True if the Max Capacity hasn't been reached yet.
	 */
	public boolean hasRoomForAnotherStudent() {
		if (this.getMaxCapicity() == 0) {
			return true;
		}
		return this.getNumberOfStudents() < this.getMaxCapicity();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ID == null) ? 0 : ID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lecture other = (Lecture) obj;
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.ID))
			return false;
		return true;
	}

	@Override
	public String toString() {
		// SUGGESTION Add list of students to toString();
		return String.format("Course %s ", this.ID);
	}

}
