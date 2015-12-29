package com.gmail.jdesmond10.virology.main;

import sim.field.continuous.Continuous2D;
import sim.util.Double2D;

/**
 * This class is responsible for determining the (x,y) positions of students in
 * the network display of the simulation.
 * 
 * @author Josh Desmond
 */
class GUIStudentNetworkLocationBuilder {

	/** The length of the square grid of students */
	private int gridLength;
	/**
	 * The current count of which student your locating. Increments every time
	 * {@link #nextLocation()} is called.
	 */
	private int currentCount;

	/**
	 * Returns a new empty GUIStudentNetworkLocationBuilder.
	 * 
	 * @param numberOfStudents
	 *            The Number of students in the simulation being displayed.
	 */
	public GUIStudentNetworkLocationBuilder(int numberOfStudents) {
		this.gridLength = GlobalCourseSchedule
				.findSmallestSquareRoot(numberOfStudents);
		this.currentCount = 0;
	}

	/**
	 * @return a new Continuous 2D of the proper size for the number of students
	 *         this builder is using.
	 */
	public Continuous2D generateContinuousGrid() {
		return new Continuous2D(1, gridLength, gridLength);
	}

	/**
	 * 
	 * @return The (x, y) position of the student for use with the Continuous
	 *         grid returned by this instance of this class.
	 */
	public Double2D nextLocation() {
		// Use currentCount & gridLength to find next location
		int y;
		int x;

		// Determine x
		if (currentCount != 0) {
			x = currentCount % gridLength;
		} else {
			x = 0;
		}

		// Determine y
		if (currentCount > gridLength) {
			y = (currentCount - (currentCount % gridLength)) / gridLength;
		} else {
			y = 0;
		}

		currentCount++;
		return new Double2D(x, y);
	}
}
