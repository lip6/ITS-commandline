package fr.lip6.move.gal.itscl.modele;

import java.util.concurrent.Callable;

/**
 * Add/remove solvers to a specific list. It extends Callable :
 * to be able to run the list of solvers and Ender : manages the termination of
 * the solvers
 */
public interface ISolverObservable extends Callable<Boolean>, Ender {

	public void attach(ISolverSeq obs);

	public void detach(ISolverSeq obs);

}
