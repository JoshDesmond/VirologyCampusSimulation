package com.gmail.jdesmond10.virology.data;

import java.util.ArrayList;

import sim.engine.Schedule;

import com.gmail.jdesmond10.virology.main.AbstractSimulationStarter;
import com.gmail.jdesmond10.virology.main.GlobalCourseSchedule;
import com.gmail.jdesmond10.virology.main.Lecture;
import com.gmail.jdesmond10.virology.main.Student;
import com.gmail.jdesmond10.virology.time.MeetingTimes;
import com.gmail.jdesmond10.virology.time.SimpleDailyMeetingTimes;

import ec.util.MersenneTwisterFast;

public class SimpleSimulationStarter extends AbstractSimulationStarter {

	public SimpleSimulationStarter(MersenneTwisterFast random, Schedule schedule) {
		super(random, schedule);
	}

	@Override
	public void init() {
		this.students = new ArrayList<Student>();
		this.globalSchedule = new GlobalCourseSchedule();

		// Calling initStudent adds them to list and schedules them and stuff.
		Student saa = initStudent();
		Student dez = initStudent();
		Student hayls = initStudent();

		dez.isSick = true;

		MeetingTimes cs = new SimpleDailyMeetingTimes(14);
		MeetingTimes math = new SimpleDailyMeetingTimes(14);
		MeetingTimes bcb = new SimpleDailyMeetingTimes(15);

		Lecture lcs = new Lecture(cs, "CS1102");
		Lecture lmath = new Lecture(math, "MA1024");
		Lecture lbcb = new Lecture(bcb, "BCB100x");

		globalSchedule.addLecture(lcs);
		globalSchedule.addLecture(lmath);
		globalSchedule.addLecture(lbcb);

		saa.registerForClass(lcs);
		saa.registerForClass(lbcb);
		dez.registerForClass(lcs);
		dez.registerForClass(lbcb);
		hayls.registerForClass(lbcb);
		hayls.registerForClass(lmath);


		schedule.scheduleRepeating(globalSchedule);
		this.wasInitiated = true;
	}

}
