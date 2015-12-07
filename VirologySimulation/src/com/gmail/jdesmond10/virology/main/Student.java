package com.gmail.jdesmond10.virology.main;

import sim.engine.SimState;
import sim.engine.Steppable;

public class Student implements Steppable {

	private boolean isSick;

	public Student() {
		this.isSick = false;
	}

	@Override
	/*
	 * This method is called every step of the simulation. It will access
	 * campusState to determine which students are adjacent to itself, and use
	 * this information to alter the state of this student.
	 * 
	 * (non-Javadoc)
	 * 
	 * @see sim.engine.Steppable#step(sim.engine.SimState)
	 */
	public void step(SimState campusState) {
		CampusState state = (CampusState) campusState;


		if (state.getGlobalCourseSchedule().isInClass(this)) {

		}
	}



}
