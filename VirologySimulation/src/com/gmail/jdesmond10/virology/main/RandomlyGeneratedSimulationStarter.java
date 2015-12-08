package com.gmail.jdesmond10.virology.main;

import java.util.ArrayList;
import java.util.Collection;

import sim.engine.Schedule;
import ec.util.MersenneTwisterFast;

public class RandomlyGeneratedSimulationStarter extends
AbstractSimulationStarter {

	public RandomlyGeneratedSimulationStarter(MersenneTwisterFast random,
			Schedule schedule) {
		super(random, schedule);
	}

	private static final int STARTING_STUDENTS = 10;

	@Override
	public void init() {
		// build list of students
		Collection<Student> studentCollection = new ArrayList<Student>();


		for (int i = 0; i < STARTING_STUDENTS; i++) {
			Student s = new Student();
			schedule.scheduleRepeating(s);
			studentCollection.add(s);

			// TODO randomly generate students based off of some hardcoded list
			// of classes or something.
		}

		// TODO build global schedule.

		this.wasInitiated = true;
	}

}
