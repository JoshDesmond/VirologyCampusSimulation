package com.gmail.jdesmond10.virology.time;

import java.time.LocalDateTime;

/**
 * Class which represents when a lecture is occuring. This could be on a weekly
 * schedule or a daily schedule, or even a complicated schedule that includes a
 * full chart lookup for if the class is meeting.
 *
 */
public interface MeetingTimes {
	/**
	 * Returns true if the class meets at this particular time. It is assumed in
	 * the simulation that time will never be more specific than on the basis of
	 * an hour, but this could easily change. The time will reference the
	 * starting time of the class.
	 * 
	 * Note that for simple implementations of a schedule, aspects of the
	 * LocalDateTime can be ignored, like month and day specifically. If you
	 * know that the implementation will be ignoring year, you can give a blank
	 * year and it won't matter.
	 * 
	 * @param time
	 *            Representing starting time of class. If a class meets at a
	 *            certain hour, you could give it:
	 *            <code>LocalDateTime.of(int year, int month, int dayOfMonth, int hour, int minute)</code>
	 * @return True if the course is meeting
	 */
	public boolean meetsDuringDateTime(LocalDateTime time);

	/**
	 * Determines if there is any time conflict between the two courses.
	 * 
	 * @param otherMeetingTimes
	 *            MeetingTime to compare this to
	 * @return True if there is a conflict.
	 */
	public boolean conflictsWith(MeetingTimes otherMeetingTimes);

	/**
	 * @return true if the class implementing this interface also implements
	 *         Comparable<\self>.
	 */
	default public boolean isComparableWithSelf() {
		return false;
	}
}
