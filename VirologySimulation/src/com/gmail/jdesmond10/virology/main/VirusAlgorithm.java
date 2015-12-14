package com.gmail.jdesmond10.virology.main;


public class VirusAlgorithm {

	public boolean simulateContact(Student student, Student otherStudent,
			CampusState state) {

		if (student.isSick)
			return true;
		if (!otherStudent.isSick)
			return false;

		if (state.random.nextBoolean(.005)) {
			state.interactions.addEdge(otherStudent, student, true);
			// TODO add nodes back.
			return true;
		}

		return false;
	}

}
