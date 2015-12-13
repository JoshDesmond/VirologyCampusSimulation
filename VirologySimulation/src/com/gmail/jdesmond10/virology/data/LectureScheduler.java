package com.gmail.jdesmond10.virology.data;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.gmail.jdesmond10.virology.main.Lecture;
import com.gmail.jdesmond10.virology.main.Student;

import ec.util.MersenneTwisterFast;

/**
 * automates the process of registering students for classes. The most important
 * method is {@link #registerAllStudents(Set)}. I intended for this class to be
 * created within EmptyScheduleGenerator, although if you want to build your
 * own, just use the constructor, and add classes using addClass().
 * 
 * @author Josh Desmond
 */
public class LectureScheduler {

	// SUGGESTION this should probably just be a Lecture...
	/**
	 * list of lectures. Must be treated like a set, although it needs to be
	 * able to access a random item so I'm using LinkedList.
	 */
	private List<Lecture> masterList;
	private MersenneTwisterFast random;

	public LectureScheduler(MersenneTwisterFast random) {
		this.random = random;
		this.masterList = new LinkedList<Lecture>();
	}

	/**
	 * Adds a lecture to the given list of lectures.
	 * 
	 * @throws IllegalArgumentException
	 *             if the lecture is already on the list of lectures.
	 * @param lecture
	 *            Lecture to add.
	 */
	public void addClass(Lecture lecture) {
		if (masterList.contains(lecture)) {
			throw new IllegalArgumentException(String.format(
					"Duplicate lecture %s trying to be added", lecture));
		}

		this.masterList.add(lecture);
	}

	/**
	 * Registers a list of students for the existing lectures within this
	 * LectureScheduler. Ensures that there are no meeting conflicts.
	 * 
	 * @param studentList
	 *            List of unique students to be registered for classes.
	 */
	public void registerAllStudents(Set<Student> studentList) {
		for (Iterator<Student> iter = studentList.iterator(); iter.hasNext();) {
			Student student = iter.next();

			this.registerStudentForClasses(student);
		}
	}

	/**
	 * Randomly assigns a student to three classes. Ensures there will be no
	 * time conflicts. Also ensures maximum capacity of classes will not be
	 * reached.
	 */
	public void registerStudentForClasses(Student student) {
		Collection<Lecture> threeLectures = getRandomThreeLectures();

		for (Iterator<Lecture> iterator = threeLectures.iterator(); iterator
				.hasNext();) {
			Lecture lecture = iterator.next();

			student.registerForClass(lecture);
		}
	}

	/**
	 * Ensures that the three lectures are unique, and do not have course timing
	 * conflicts. Also ensures maximum capacity of classes will not be reached.
	 * 
	 * @return Three different courses one student can register for.
	 */
	private Collection<Lecture> getRandomThreeLectures() {
		// SUGGESTION refactor this into smaller methods if you ever try to
		// change it.
		Lecture one;
		Lecture two = null;
		Lecture three = null;
		/*
		 * The logic for this method is a little wonky, but I was just trying to
		 * keep it simple. Basically what's going on is that first a Lecture one
		 * is picked. Then a lecture two is picked, and it's checked against
		 * lecture one to see if there are no time conflicts. The same is done
		 * for three then comparing to both two and one.
		 */

		one = getRandomLecture();
		while (one.isFull()) {
			one = getRandomLecture();
		}

		boolean searchingForSecondLecture = true;
		while (searchingForSecondLecture) {
			// SUGGESTION optimize this by keeping track of what meetings have
			// already been checked. That is you can say, getRandomLecture, if
			// it's been retrieved before, try again.
			two = getRandomLecture();

			if (two.equals(one) || two.time.conflictsWith(one.time)
					|| two.isFull()) {
				// If they're the same
				// or if they're conflicting
				// or if it's full
				continue;
			}

			// else
			searchingForSecondLecture = false;
			// SUGGESTION have this short circuit if you keep checking and you
			// run out of options. Otherwise keep track of how many spots are
			// remaining or something.
		}

		boolean searchingForThirdLecture = true;
		while (searchingForThirdLecture) {
			// See logic from second class loop.
			three = getRandomLecture();
			if (three.equals(one) || three.time.conflictsWith(one.time)
					|| three.isFull()) {
				continue;
			}
			if (three.equals(two) || three.time.conflictsWith(two.time)) {
				continue;
			}
			searchingForThirdLecture = false;
		}

		return Arrays.asList(one, two, three);
	}

	/**
	 * @return a random lecture from the list of lectures.
	 */
	private Lecture getRandomLecture() {
		int maxIndex = getNumberOfLectures();
		// SUGGESTION Ensure this is actually correct logic. No tests.
		int nextInt = Math.abs(random.nextInt());
		return masterList.get(nextInt % maxIndex);

		// TODO do a weighted retrieval of Lectures. That is don't make it
		// random, but make it so it chooses one based on the capacity of it,
		// with classes of higher capacity having a proportionally higher
		// weight.
	}

	public List<Lecture> getLectureList() {
		return this.masterList;
	}

	public int getNumberOfLectures() {
		return masterList.size();
	}

}
