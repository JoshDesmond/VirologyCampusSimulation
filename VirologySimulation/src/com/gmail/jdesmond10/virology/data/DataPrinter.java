package com.gmail.jdesmond10.virology.data;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import sim.field.network.Edge;
import sim.field.network.Network;

import com.gmail.jdesmond10.virology.main.Student;

/**
 * This class contains two quick scripts I wrote just to print out the adjacency
 * matrix. It's not actually useful for much, just keeping this around for
 * reference.
 * 
 */
@Deprecated
public class DataPrinter {

	@Deprecated
	public static void printSerializeNetwork(Network interactions) {

		Edge[][] matrix = interactions.getAdjacencyMatrix();
		CharSequence[][] newMatrix = new CharSequence[matrix.length][matrix[0].length];

		for (int j = 0; j < matrix[0].length; j++) {
			for (int i = 0; i < matrix.length; i++) {
				if (matrix[i][j] != null) {
					newMatrix[i][j] = "1";
				}

				else {
					newMatrix[i][j] = "0";
				}
			}
		}

		for (int i = 0; i < matrix.length; i++) {

		}

		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("file.txt"));
			for (int i = 0; i < newMatrix.length; i++) {
				out.write(String.join(", ", newMatrix[i]));
				out.newLine();
			}
			out.close();
		} catch (IOException e) {
		}

	}

	@Deprecated
	public static void printSocialNetwork(List<Student> students) {
		// Quick script to print all nodes and connections.
		Network interactions = new Network(false);

		for (Student s : students) {
			interactions.addNode(s);
			interactions.addEdge(s, null, null);
		}

	}
}
