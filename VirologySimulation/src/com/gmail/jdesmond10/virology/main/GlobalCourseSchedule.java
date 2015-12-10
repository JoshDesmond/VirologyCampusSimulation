package com.gmail.jdesmond10.virology.main;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 * GlobalCourseSchedule allows for lookup of where all students are at at a
 * given moment. Primarily used for display. TODO convert this into an
 * interface, have LectureScheduler by a WPI subtype of it.
 *
 * @author Josh Desmond
 */
public class GlobalCourseSchedule {

	/** List of all lectures. */
	private List<Lecture> lectureList;

	public GlobalCourseSchedule() {
		this.lectureList = new LinkedList<Lecture>();
	}

	public void addLecture(Lecture lecture) {
		this.lectureList.add(lecture);
	}

	/**
	 * @return the master list of all lectures.
	 */
	public List<Lecture> getLectures() {
		return lectureList;
	}

}
