package com.gmail.jdesmond10.virology.main;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * 
 * GlobalCourseSchedule allows for lookup of where all students are at at a
 * given moment. Primarily used for display.
 *
 * @author Josh Desmond
 */
public class GlobalCourseSchedule {

	/** List of all lectures. */
	private SortedSet<Lecture> lectureList;

	public GlobalCourseSchedule() {
		this.lectureList = new TreeSet<Lecture>();
	}

	/**
	 * Builds a GlobalCourseSchedule with any existing set of lectures.
	 * 
	 * @param lectureList
	 */
	public GlobalCourseSchedule(Set<Lecture> lectureList) {
		this.lectureList = new TreeSet<Lecture>(lectureList);
	}

	/**
	 * Adds a lecture to the set of lectures.
	 * 
	 * @see #GlobalCourseSchedule(Set)
	 * @param lecture
	 */
	public void addLecture(Lecture lecture) {
		if (!this.lectureList.add(lecture)) {
			throw new IllegalArgumentException(
					String.format(
							"lecture %s was given to be added to GlobalCourseSchedule when it was already part of the set.",
							lecture.toString()));
		}
	}

	/**
	 * @return the master list of all lectures.
	 */
	public SortedSet<Lecture> getLectures() {
		return lectureList;
	}

	@Override
	public String toString() {
		return "GlobalCourseSchedule ["
				+ (lectureList != null ? "lectureList=" + lectureList : "")
				+ "]";
	}



}
