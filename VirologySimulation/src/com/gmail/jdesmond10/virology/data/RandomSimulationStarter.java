package com.gmail.jdesmond10.virology.data;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import sim.engine.Schedule;

import com.gmail.jdesmond10.virology.main.AbstractSimulationStarter;
import com.gmail.jdesmond10.virology.main.GlobalCourseSchedule;
import com.gmail.jdesmond10.virology.main.Lecture;
import com.gmail.jdesmond10.virology.main.Student;

import ec.util.MersenneTwisterFast;

public class RandomSimulationStarter extends AbstractSimulationStarter {

	public RandomSimulationStarter(MersenneTwisterFast random, Schedule schedule) {
		super(random, schedule);
	}

	@Override
	public void init() {
		this.students = new HashSet<Student>(500);
		this.globalSchedule = new GlobalCourseSchedule();

		for (int i = 1; i <= 500; i++) {
			initStudent(i);
		}

		LectureScheduler lectureScheduler = EmptyScheduleGenerator
				.generateNewSchedule(random);

		lectureScheduler.registerAllStudents((Set<Student>) this.students);

		final List<Lecture> lectureList = lectureScheduler.getLectureList();
		for (Iterator<Lecture> iterator = lectureList.iterator(); iterator.hasNext();) {
			Lecture lecture = iterator.next();
			globalSchedule.addLecture(lecture);
		}

		this.wasInitiated = true;
	}

}
