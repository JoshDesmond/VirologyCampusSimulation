package com.gmail.jdesmond10.virology.main;

import sim.field.continuous.Continuous2D;
import sim.util.Double2D;

/**
 * TODO
 * @author Josh Desmond
 */
class GUILocationBuilder {

	private int a = 1; // TODO The method of generating nextLocation is silly.
	private int b = 0;

	public GUILocationBuilder(int size) {
		// TODO Auto-generated constructor stub
	}

	public Continuous2D generateContinousGrid() {
		// TODO
		return new Continuous2D(1, 30, 30);
	}



	/*
	 * EDIT: Okay, so I never got around towards making a nice version of this,
	 * so here's an explanation of what's going on:
	 * 
	 * Basically, there's a 25 by n grid of nodes being drawn, and when the
	 * simulation places students at locations, it does it by using this method.
	 */

	public Double2D nextLocation() {
		if (a % 25 == 0) { // TODO actually build this with another class and
			// correctly.
			a = 0;
			b++;
		}

		a++;
		return new Double2D(a, b);
	}

}
