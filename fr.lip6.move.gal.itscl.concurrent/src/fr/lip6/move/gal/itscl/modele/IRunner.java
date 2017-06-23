package fr.lip6.move.gal.itscl.modele;

import java.io.IOException;
import java.util.Set;

import fr.lip6.move.gal.Specification;

public interface IRunner {

	/**
	 * configure spec and initialize doneProps attribute to an empty keySet
	 */
	public void configure(Specification spec);

	public void configure(Specification z3Spec, Set<String> doneProps) throws IOException;

	public void solve();

	public Boolean taskDone();

}
