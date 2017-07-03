package fr.lip6.move.gal.itscl.modele;

import java.io.IOException;
import java.util.Set;

import fr.lip6.move.gal.Specification;
import fr.lip6.move.gal.itscl.interpreter.IInterpreteObservable;


/**
 * Adapter to the current Runners (Solvers)
 */

public interface IRunner {

	/**
	 * configure specification and initialize doneProps attribute to an empty keySet
	 */
	public void configure(Specification spec);

	public void configure(Specification z3Spec, Set<String> doneProps) throws IOException;

	/**
	 * run the solver ! 
	 */
	public void solve();
	
	/**
	 * initialize the manager of interpreters threads
	 */
	public void configureInterpreter(IInterpreteObservable ob);
	
	/**
	 * Returns if solver did or not well its job
	 */
	public Boolean taskDone();
	
}
