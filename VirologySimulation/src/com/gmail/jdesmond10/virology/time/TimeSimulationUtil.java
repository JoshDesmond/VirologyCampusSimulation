package com.gmail.jdesmond10.virology.time;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Contains methods for transferring between units of time.
 *
 */
public class TimeSimulationUtil {

	/**
	 * LocalDateTime which simulation began
	 */
	private LocalDateTime initialSimulationTime;

	/**
	 * Creates a timeSimulationUtil for a given simulation
	 * 
	 * @param initialSimulationTime
	 *            LocalDateTime which simulation began
	 */
	public TimeSimulationUtil(LocalDateTime initialSimulationTime) {
		this.initialSimulationTime = initialSimulationTime;
	}

	/**
	 * Creates a LocalDateTime given just an hour.
	 * 
	 * @param hour
	 *            the hour-of-day to represent, from 0 to 23
	 * @return LocalDateTime with that hour
	 */
	public static LocalDateTime dateTimeOfHour(int hour) {
		return LocalDateTime.of(0, 0, 0, hour, 0);
	}

	/**
	 * Generates a LocalDateTime given the number of hours which have passed
	 * from an initial time
	 * 
	 * @param time
	 *            number of hours which have passed in simulation
	 * @param initialTime
	 *            LocalDateTime which simulation began
	 * @return LocalDateTime representing current hour.
	 */
	public LocalDateTime timeOfSimulation(long time) {
		// SUGGESTION Cache values if program is slow.
		long epochSecond = initialSimulationTime.toEpochSecond(ZoneOffset.UTC);
		epochSecond += (time * 3600);
		return LocalDateTime.ofEpochSecond(epochSecond, 0, ZoneOffset.UTC);
	}

}
