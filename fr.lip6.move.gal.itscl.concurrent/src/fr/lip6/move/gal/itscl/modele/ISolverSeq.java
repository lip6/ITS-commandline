package fr.lip6.move.gal.itscl.modele;

import java.util.concurrent.Callable;

/**
 * Main runner
 */
public interface ISolverSeq extends Callable<Integer> {

	/**
	 * @return 0 : if solve all the props. 1 : didn't achieve them all
	 */
	public int hasComplete();

}
