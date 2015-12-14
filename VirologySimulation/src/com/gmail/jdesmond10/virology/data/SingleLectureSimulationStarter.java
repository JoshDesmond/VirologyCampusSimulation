package com.gmail.jdesmond10.virology.data;

import java.util.LinkedList;

import sim.engine.Schedule;

import com.gmail.jdesmond10.virology.main.AbstractSimulationStarter;
import com.gmail.jdesmond10.virology.main.GlobalCourseSchedule;
import com.gmail.jdesmond10.virology.main.Lecture;
import com.gmail.jdesmond10.virology.main.Student;
import com.gmail.jdesmond10.virology.time.SimpleDailyMeetingTimes;

import ec.util.MersenneTwisterFast;

public class SingleLectureSimulationStarter extends AbstractSimulationStarter {

	public SingleLectureSimulationStarter(MersenneTwisterFast random,
			Schedule schedule) {
		super(random, schedule);
	}

	@Override
	public void init() {
		this.students = new LinkedList<Student>();

		Lecture l1 = new Lecture(new SimpleDailyMeetingTimes(12), "Course 1");
		Lecture l2 = new Lecture(new SimpleDailyMeetingTimes(13), "Course 2");
		Lecture l3 = new Lecture(new SimpleDailyMeetingTimes(14), "Course 3");
		this.globalSchedule = new GlobalCourseSchedule();
		globalSchedule.addLecture(l1);
		globalSchedule.addLecture(l2);
		globalSchedule.addLecture(l3);

		for (int i = 0; i <= 500; i++) {
			Student s = initStudent(i);
			s.registerForClass(l1);
			s.registerForClass(l2);
			s.registerForClass(l3);
			if (i == 0) {
				s.isSick = true;
			}
		}

		schedule.scheduleRepeating(globalSchedule);
		wasInitiated = true;
	}

}
