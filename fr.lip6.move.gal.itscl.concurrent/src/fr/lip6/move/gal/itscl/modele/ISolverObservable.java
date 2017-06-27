package fr.lip6.move.gal.itscl.modele;

import java.util.concurrent.Callable;

/**
 * Add/remove solvers to a specific list. It extends :
 * 
 * Callable : run the list of solvers
 * 
 * Ender : manages the termination of the solvers 
 * 
 * It also manages the synchronization with solvers and their interpreters
 */
public interface ISolverObservable extends Callable<Boolean>, Ender {

	public void attach(ISolverSeq obs);

	public void detach(ISolverSeq obs);

	public void waitInterpreters();

	public void notifySolver();

}
