package fr.lip6.move.gal.itscl.modele;

import java.util.concurrent.Callable;

public interface ISolverSeq extends Callable<Integer> {

	public int isComplete();

}
