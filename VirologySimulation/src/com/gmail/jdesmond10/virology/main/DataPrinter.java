package com.gmail.jdesmond10.virology.main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import sim.field.network.Edge;
import sim.field.network.Network;

public class DataPrinter {
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
}
