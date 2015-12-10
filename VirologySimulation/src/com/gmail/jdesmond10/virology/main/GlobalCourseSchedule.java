package com.gmail.jdesmond10.virology.main;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 * GlobalCourseSchedule allows for lookup of where all students are at at a
 * given moment. Primarily used for display. TODO hold off on using this until
 * display n' shit is actually needed.
 *
 * @author Josh Desmond
 */
@Deprecated
public class GlobalCourseSchedule {

	private List<Lecture> lectureList;

	public GlobalCourseSchedule() {
		this.lectureList = new LinkedList<Lecture>();
	}

	public void addLecture(Lecture lecture) {
		this.lectureList.add(lecture);
	}
}
