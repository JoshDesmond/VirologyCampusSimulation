package com.gmail.jdesmond10.virology.time;

import java.time.LocalDateTime;

/**
 * Daily meeting times, for when the specificity of the date never exceeds the
 * precision of a 24 hour day.
 */
public class SimpleDailyMeetingTimes implements MeetingTimes {

	private int hour;

	/**
	 * A MeetingTime which only checks to see if the date is at the current
	 * hour.
	 * 
	 * @param hour
	 *            int between 0 and 23, specifying when the course meets.
	 */
	public SimpleDailyMeetingTimes(int hour) {
		this.hour = hour;
	}

	@Override
	public boolean meetsDuringDateTime(LocalDateTime time) {
		return (time.getHour() == this.hour);
	}

	@Override
	public boolean conflictsWith(MeetingTimes otherMeetingTimes) {
		if (otherMeetingTimes instanceof SimpleDailyMeetingTimes) {
			// Then our logic becomes much much simpler.
			SimpleDailyMeetingTimes simpleOther = (SimpleDailyMeetingTimes) otherMeetingTimes;

			return (simpleOther.hour == this.hour);
		}

		// Isn't supported otherwise.
		throw new IllegalStateException(
				String.format(
						"You are comparing whether a simple meeting time "
								+ "conflicts with a meeting time %s, which isn't "
								+ "currently support by this version of CampusSimulation.",
								otherMeetingTimes));
	}
}
