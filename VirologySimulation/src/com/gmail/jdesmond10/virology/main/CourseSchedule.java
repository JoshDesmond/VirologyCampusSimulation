package com.gmail.jdesmond10.virology.main;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.gmail.jdesmond10.virology.time.TimeSimulationUtil;

/**
 * A single students class schedule. Can be used to determine the students
 * immediately adjacent students.
 * 
 * Created by Saahil on 12/6/2015.
 */
public class CourseSchedule {

	private Set<Lecture> listOfLectures;

	public CourseSchedule(Set<Lecture> listOfLectures) {
		this.listOfLectures = listOfLectures;
	}

	public CourseSchedule() {
		this.listOfLectures = new HashSet<Lecture>();
	}

	/**
	 * Adds the given lecture to the set of registered classes. Throws an error
	 * if the lecture was already in the list of lectures. Also throws an error
	 * if adding the new lecture caused a time conflict.
	 * 
	 * @param lecture
	 */
	public void addLecture(Lecture lecture) {
		if (!this.listOfLectures.add(lecture)) {
			throw new IllegalArgumentException(
					String.format(
							"Lecture %s was already in this courseSchedule's set of lectures",
							lecture));
		}

		// Check if addition was valid.
		if (!validateSchedule()) {
			throw new IllegalStateException(
					String.format(
							"Class overlap after adding lecture %s to current schedule for CourseSchedule %s",
							lecture, this));
		}
	}

	/**
	 * Returns the lecture which this student will be attending at the given
	 * time. Can be used by a student in its step method to determine who its
	 * neighbors are.
	 * 
	 * @param time
	 *            The time, in the format of the simulations time.
	 * @return an optional of a Lecture, which will contain a list of students
	 *         also attending the given course.
	 */
	public Optional<Lecture> getLectureAtTime(long time, TimeSimulationUtil util) {
		final LocalDateTime currentTime = util.timeOfSimulation(time);

		for (Lecture l : listOfLectures) {
			if (l.time.meetsDuringDateTime(currentTime))
				return Optional.of(l);
		}

		return Optional.empty();
	}

	/**
	 * Checks if there is no class overlap
	 * 
	 * @return true if the schedule is valid
	 */
	@Deprecated
	public boolean validateSchedule() {
		// SUGGESTION write this method if there are problems with scheduling.
		return true;
	}

	/**
	 * @return the set of lectures from the schedule. Please don't alter it's
	 *         contents as it's an original copy.
	 */
	public Set<Lecture> getLectures() {
		return listOfLectures;
	}

}
