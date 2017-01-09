package com.gmail.jdesmond10.virology.data;

import java.util.Map;

import com.gmail.jdesmond10.virology.main.Lecture;
import com.gmail.jdesmond10.virology.time.MeetingTimes;
import com.gmail.jdesmond10.virology.time.SimpleDailyMeetingTimes;

import com.google.common.collect.ImmutableMap;

import ec.util.MersenneTwisterFast;

public class EmptyScheduleGenerator {

	private int currentCourseCode = 0;
	private MersenneTwisterFast random;

	/**
	 * This is a hardcoded map of (lecture size -> number of lectures). That is,
	 * the key value pair (75,10) means to have 10 lectures of 75 people be
	 * generated. Uses Guava's ImmutableMap.
	 */
	//@formatter:off
	private  Map<Integer, Integer> lectureRules =
			ImmutableMap.<Integer, Integer>builder()
			.put(500, 45)
			.put(300, 300)
			.put(100, 1500)
			.put(200, 400)
			.put(30, 900)
			.put(10, 10000)
			.build();
	//@formatter:on

	/** default constructor, no input given. */
	public EmptyScheduleGenerator(MersenneTwisterFast random) {
		this.random = random;
		this.lectureRules = ImmutableMap.<Integer, Integer> builder()
				.put(500, 45).put(300, 300).put(100, 1500).put(200, 400)
				.put(30, 900).put(10, 10000).build();
	}

	/** Constructor with given lectureRules */
	public EmptyScheduleGenerator(MersenneTwisterFast random2,
			Map<Integer, Integer> lectureRules2) {
		this.random = random2;
		this.lectureRules = lectureRules2;
	}

	/**
	 * This generates a brand new empty LectureScheduler, populated with empty
	 * classes (but existing classes).
	 * 
	 * @see LectureScheduler
	 * @return LectureScheduler to be populated with Classes.
	 */
	public LectureScheduler generateSchedule() {
		LectureScheduler lectureScheduler = new LectureScheduler(random);

		for (Map.Entry<Integer, Integer> entry : lectureRules.entrySet()) {
			Integer lectureSize = entry.getKey();
			Integer numOfLectures = entry.getValue();

			for (int i = numOfLectures; i > 0; i--) {

				lectureScheduler.addClass(new Lecture(getNextCourseTime(),
						getNextCourseCode(lectureSize), lectureSize));
			}
		}

		return lectureScheduler;
	}

	/**
	 * Returns a unique course code. Includes the capacity just for making the
	 * course code include some information about itself.
	 * 
	 * @param lectureSize
	 *            Capacity of course
	 * @return String to be used for course code.
	 */
	private String getNextCourseCode(int lectureSize) {
		currentCourseCode++;
		return String.format("C%s-S%s", currentCourseCode, lectureSize);
	}

	/**
	 * Generates a new meeting time for use when generating the schedule.
	 * 
	 * @param lectureSize
	 * @return
	 */
	private MeetingTimes getNextCourseTime() {
		// SUGGESTION Make this random, not evenly distributed.
		// time represents the starting time of the course. This will rotate
		// between 8 to 14
		final int time = currentCourseCode % 7 + 8;

		return new SimpleDailyMeetingTimes(time);
	}

}
