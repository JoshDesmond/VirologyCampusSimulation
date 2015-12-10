package com.gmail.jdesmond10.virology.main;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.gmail.jdesmond10.virology.time.MeetingTimes;
import com.gmail.jdesmond10.virology.time.SimpleDailyMeetingTimes;

/**
 * An object representing a set of course meetings, which contains a unique ID,
 * and a set of students in the lecture/class.
 * 
 */
public class Lecture implements Comparable<Lecture> {
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
	 * @see #isFull()
	 * @return maxCapicty of class.
	 */
	public int getMaxCapicity() {
		return maxCapicity;
	}

	/**
	 * 
	 * @return True if the Max Capacity has been reached.
	 */
	public boolean isFull() {
		if (this.getMaxCapicity() == 0) {
			return false;
		}
		return this.getNumberOfStudents() >= this.getMaxCapicity();
	}

	@Override
	public int compareTo(Lecture o) {
		// SUGGESTION this is wonky logic. Maybe I should have two types of
		// Lectures, one that's comparable one that's not. And comparable
		// Lectures have to use a comparable Meeting Time?
		int diff = 0;
		if (o.time instanceof SimpleDailyMeetingTimes
				&& this.time instanceof SimpleDailyMeetingTimes) {
			// Then compare via times & this.

			SimpleDailyMeetingTimes otherTime = (SimpleDailyMeetingTimes) o.time;
			SimpleDailyMeetingTimes thisTime = (SimpleDailyMeetingTimes) this.time;

			diff = thisTime.compareTo(otherTime);
			if (diff != 0) {
				return diff;
			}
		}

		// otherwise go of their capacity, then ID.

		diff = this.getMaxCapicity() - o.getMaxCapicity();

		if (diff != 0) {
			return diff;
		}

		diff = this.ID.compareTo(o.ID);

		return diff;
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
		StringBuilder builder = new StringBuilder();
		builder.append("Lecture [");
		if (time != null)
			builder.append("time=").append(time).append(", ");
		if (ID != null)
			builder.append("ID=").append(ID).append(", ");
		builder.append("maxCapicity=").append(maxCapicity)
		.append(", getNumberOfStudents()=")
		.append(getNumberOfStudents()).append("]");
		return builder.toString();
	}






}
