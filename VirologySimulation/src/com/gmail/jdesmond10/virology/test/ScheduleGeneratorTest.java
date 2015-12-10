package com.gmail.jdesmond10.virology.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.gmail.jdesmond10.virology.data.EmptyScheduleGenerator;
import com.gmail.jdesmond10.virology.data.LectureScheduler;

import ec.util.MersenneTwisterFast;

public class ScheduleGeneratorTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testEmptyScheduleGeneratorRuns() {
		// Simply tests if the generateNewSchedule function builds a scheduler
		// with at least one lecture.
		LectureScheduler l = EmptyScheduleGenerator
				.generateNewSchedule(new MersenneTwisterFast());

		assertTrue(l.getNumberOfLectures() > 0);
	}
}
