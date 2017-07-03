package fr.lip6.move.gal.itscl.modele;

import java.util.concurrent.Callable;

import fr.lip6.move.gal.itscl.interpreter.IInterpreteObservable;

/**
 * It extends :
 * 
 * Callable : run solvers
 * 
 * Ender : manages the completion of solvers
 * 
 * It also manages the synchronization with solvers and their interpreters
 */
public interface ISolverObservable extends Callable<Boolean>, Ender {

	public void attach(ISolverSeq obs);

	public void detach(ISolverSeq obs);

	
	/**
	 * wait till all interpreters end correctly
	 */
	public void interpreterDone();

	/**
	 * Notify SolverObservable that interpreters have complete
	 */
	public void notifySolver();
	
	
	/**
	 * {@link IInterpreteObservable} blocks for future creation of interpreter
	 */
	public void waitSolver() throws InterruptedException;

	/**
	 * notify {@link IInterpreteObservable} to start joining interpreters
	 * threads, when a solver has done the task or none.
	 */
	public void notifyInterpreter();

}
