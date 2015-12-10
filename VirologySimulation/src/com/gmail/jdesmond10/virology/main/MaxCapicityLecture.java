package com.gmail.jdesmond10.virology.main;

import com.gmail.jdesmond10.virology.time.MeetingTimes;

/**
 * A MaxCapcityLecture can only hold a certain number of students, and includes
 * a few utility methods for checking if the class is full.
 * 
 * @author Josh Desmond
 */
public class MaxCapicityLecture extends Lecture {

	private final int maxCapicity;

	public MaxCapicityLecture(MeetingTimes time, String ID, int maxCapicity) {
		super(time, ID);
		this.maxCapicity = maxCapicity;
	}

	/**
	 * 
	 * @see #hasRoomForAnotherStudent()
	 * @return maxCapicty of class.
	 */
	public int getMaxCapicity() {
		return maxCapicity;
	}

	@Override
	public void addStudent(Student student) {
		if (this.getNumberOfStudents() >= this.getMaxCapicity()) {
			throw new IllegalArgumentException(
					String.format(
							"Too many students added to class. Capicity of %s exceeded.",
							this.getMaxCapicity()));
		}

		super.addStudent(student);
	}

	/**
	 * 
	 * @return True if the Max Capicity hasn't been reached yet.
	 */
	public boolean hasRoomForAnotherStudent() {
		return this.getNumberOfStudents() < this.getMaxCapicity();
	}
}
