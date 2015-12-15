package com.gmail.jdesmond10.virology.data;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import sim.engine.Schedule;

import com.gmail.jdesmond10.virology.main.AbstractSimulationStarter;
import com.gmail.jdesmond10.virology.main.GlobalCourseSchedule;
import com.gmail.jdesmond10.virology.main.Lecture;
import com.gmail.jdesmond10.virology.main.Student;

import com.google.common.collect.ImmutableMap;

import ec.util.MersenneTwisterFast;

public class RandomSimulationStarter extends AbstractSimulationStarter {

	private EmptyScheduleGenerator emptyScheduleGenerator;
	private final int numStudents;

	public RandomSimulationStarter(MersenneTwisterFast random, Schedule schedule) {
		super(random, schedule);
		numStudents = 50000;
		emptyScheduleGenerator = new EmptyScheduleGenerator(random);
	}

	/** Last minute constructor added in for the sake of presentation. */
	public RandomSimulationStarter(MersenneTwisterFast random,
			Schedule schedule, int i) {
		super(random, schedule);
		Map<Integer, Integer> lectureRules;
		if (i == 1) {
			lectureRules = ImmutableMap.<Integer, Integer> builder()
					.put(500, 45).put(300, 300).put(100, 1500).put(200, 400)
					.put(30, 900).put(10, 10000).build();
			numStudents = 50000;
		} else if (i == 2) {
			lectureRules = ImmutableMap.<Integer, Integer> builder()
					.put(75, 10).put(30, 30).put(10, 10).build();
			numStudents = 500;
		} else {
			lectureRules = ImmutableMap.<Integer, Integer> builder()
					.put(100, 15).put(35, 100).put(10, 109).build();
			numStudents = 2000;
		}

		emptyScheduleGenerator = new EmptyScheduleGenerator(random,
				lectureRules);

	}

	@Override
	public void init() {
		this.students = new HashSet<Student>(numStudents);
		this.globalSchedule = new GlobalCourseSchedule();

		for (int i = 1; i <= numStudents; i++) {
			Student newStudent = initStudent(i);

			if (i < 2) { // Simulation starts with one sick student
				newStudent.isSick = true;
			}
		}

		LectureScheduler lectureScheduler = emptyScheduleGenerator.generateSchedule();

		lectureScheduler.registerAllStudents((Set<Student>) this.students);

		final List<Lecture> lectureList = lectureScheduler.getLectureList();
		for (Iterator<Lecture> iterator = lectureList.iterator(); iterator
				.hasNext();) {
			Lecture lecture = iterator.next();
			globalSchedule.addLecture(lecture);
		}

		schedule.scheduleRepeating(globalSchedule);
		this.wasInitiated = true;
	}
}
