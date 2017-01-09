package de.hamster.model;

import java.io.Serializable;

import de.hamster.workbench.Utils;

/**
 * Diese Klasse umfasst Instruktionen, die sich auf das Territorium beziehen.
 * 
 * @author $Author: djasper $
 * @version $Revision: 1.1 $
 */
public class TerrainInstruction extends Instruction implements Serializable {
	public static final int COUNT_ROWS = 1;

	public static final int COUNT_COLS = 2;

	public static final int COUNT_CORN = 3;

	public static final int COUNT_HAMSTER = 4;

	public static final int GET_HAMSTER = 5;

	public TerrainInstruction(int type) {
		super(type);
	}

	public String toString() {
		switch (getType()) {
		case COUNT_ROWS:
			return Utils.getResource("hamster.getAnzahlReihen") + "()";
		case COUNT_COLS:
			return Utils.getResource("hamster.getAnzahlSpalten") + "()";
		case COUNT_CORN:
			return Utils.getResource("hamster.getAnzahlKoerner") + "()";
		case COUNT_HAMSTER:
			return Utils.getResource("hamster.getAnzahlHamster") + "()";
		case GET_HAMSTER:
			return Utils.getResource("hamster.getHamster") + "()";
		}
		return super.toString();
	}
}
