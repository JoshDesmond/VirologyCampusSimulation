package com.gmail.jdesmond10.virology.data;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.gmail.jdesmond10.virology.main.Lecture;
import com.gmail.jdesmond10.virology.main.MaxCapicityLecture;
import com.gmail.jdesmond10.virology.main.Student;

import ec.util.MersenneTwisterFast;

public class LectureScheduler {

	// SUGGESTION this should probably just be a Lecture...
	// SUGGESTION change this to set as well?
	private List<MaxCapicityLecture> masterList;
	private MersenneTwisterFast random;

	public LectureScheduler(MersenneTwisterFast random) {
		this.masterList = new LinkedList<MaxCapicityLecture>();
	}

	/**
	 * Adds a lecture to the given list of lectures.
	 * 
	 * @param lecture
	 *            Lecture to add.
	 */
	public void addClass(MaxCapicityLecture lecture) {
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
	 * time conflicts.
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
	 * conflicts.
	 * 
	 * @return
	 */
	private Collection<Lecture> getRandomThreeLectures() {
		Lecture one;
		Lecture two = null;
		Lecture three = null;

		one = getRandomLecture();

		boolean searchingForSecondLecture = true;

		while (searchingForSecondLecture) {
			// SUGGESTION optimize this by keeping track of what meetings have
			// already been checked. That is you can say, getRandomLecture, if
			// it's been retrieved before, try again.
			two = getRandomLecture();

			if (!two.equals(one) && !two.time.conflictsWith(one.time)) {
				// If they're different
				// And if they're not conflicting

				searchingForSecondLecture = false;
			}

			// SUGGESTION have this short circuit if you keep checking and you
			// run out of options.
		}

		boolean searchingForThirdLecture = true;

		while (searchingForThirdLecture) {
			three = getRandomLecture();

			if (!three.equals(one) && !three.time.conflictsWith(one.time)) {
				// If they're different
				// And if they're not conflicting

				if (!three.equals(two) && !three.time.conflictsWith(two.time)) {
					searchingForThirdLecture = false;
				}
			}
		}

		return Arrays.asList(one, two, three);
	}

	/**
	 * @return a random lecture from the list of lectures.
	 */
	private Lecture getRandomLecture() {
		int maxIndex = getNumberOfLectures() - 1;
		// SUGGESTION Ensure this is actually correct logic. No tests.
		return masterList.get(random.nextInt() % maxIndex);
	}

	public int getNumberOfLectures() {
		return masterList.size();
	}

}
