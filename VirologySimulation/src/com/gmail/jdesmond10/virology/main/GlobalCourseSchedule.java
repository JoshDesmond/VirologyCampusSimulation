package com.gmail.jdesmond10.virology.main;

import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import sim.engine.SimState;
import sim.engine.Steppable;
import sim.field.grid.DoubleGrid2D;

/**
 * 
 * GlobalCourseSchedule allows for lookup of where all students are at at a
 * given moment. Primarily used for display. It contains a method
 * {@link #generateLectureGrid()} which creates a DoubleGrid2D, which is used by
 * CampusState to build a 2D simulation display.
 *
 * @author Josh Desmond
 */
public class GlobalCourseSchedule implements Steppable {
	private static final long serialVersionUID = 1L;

	/** List of all lectures. */
	private SortedSet<Lecture> lectureList;
	/**  */
	private DoubleGrid2D lectureGrid;

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

	/**
	 * Instantializes the Lecture grid, and returns a reference to it.
	 * 
	 * @return
	 */
	public DoubleGrid2D generateLectureGrid() {
		int dimension = findSmallestSquareRoot(lectureList.size());

		double[][] doubleValArray = new double[dimension][dimension];

		this.lectureGrid = new DoubleGrid2D(doubleValArray);

		updateGridValues();

		return lectureGrid;
	}

	/**
	 * Finds the smallest square number greater than the one given, then returns
	 * the square root of that number.
	 * 
	 * For example, given 250, it would return 16, since 256 is closest square
	 * and 16 is the sqrt.
	 */
	private static int findSmallestSquareRoot(int n) {

		return ((int) Math.sqrt((float) n) + 1);
	}

	@Override
	public void step(SimState state) {
		updateGridValues();
		CampusState campusState = (CampusState) state;
		campusState.lectureGrid = this.lectureGrid;

		// FIXME delete this
		if (state.schedule.getTime() == 2500) {
			campusState.printSerializeNetwork();
			campusState.kill();
		}

	}

	private void updateGridValues() {
		double[][] grid = lectureGrid.field;

		Iterator<Lecture> lectureListIterator = lectureList.iterator();
		assert lectureListIterator.hasNext();

		for (int j = 0; j < grid[0].length; j++) {
			for (int i = 0; i < grid.length; i++) {
				if (!lectureListIterator.hasNext()) {
					return;
				}


				Lecture lecture = lectureListIterator.next();

				grid[i][j] = lecture.getSicknessScore();
			}
		}

	}
}
