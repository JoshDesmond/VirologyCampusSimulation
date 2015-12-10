package com.gmail.jdesmond10.virology.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.gmail.jdesmond10.virology.time.SimpleDailyMeetingTimes;

public class DataStructuresTests {

	@Before
	public void setUp() throws Exception {
	}


	@Test
	public void testSimpleDailyMeetingTimeComparable() {
		SimpleDailyMeetingTimes s1 = new SimpleDailyMeetingTimes(5);
		SimpleDailyMeetingTimes s2 = new SimpleDailyMeetingTimes(7);

		assertTrue(s1.compareTo(s2) < 0);
		assertTrue(s1.compareTo(s1) == 0);
		assertTrue(s2.compareTo(s1) > 0);
	}

}
