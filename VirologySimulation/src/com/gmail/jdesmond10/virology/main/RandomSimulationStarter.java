package com.gmail.jdesmond10.virology.main;

import java.util.HashSet;
import java.util.Set;

import sim.engine.Schedule;

import com.gmail.jdesmond10.virology.data.EmptyScheduleGenerator;
import com.gmail.jdesmond10.virology.data.LectureScheduler;

import ec.util.MersenneTwisterFast;

public class RandomSimulationStarter extends AbstractSimulationStarter {

	public RandomSimulationStarter(MersenneTwisterFast random, Schedule schedule) {
		super(random, schedule);
	}

	@Override
	public void init() {
		this.students = new HashSet<Student>(500);

		for (int i = 1; i <= 500; i++) {
			initStudent(i);
		}

		LectureScheduler lectureScheduler = EmptyScheduleGenerator
				.generateNewSchedule(random);

		lectureScheduler.registerAllStudents((Set<Student>) this.students);
		this.wasInitiated = true;
	}

}
