package fr.lip6.move.gal.itscl.application;

import java.io.IOException;
import java.util.Set;

import fr.lip6.move.gal.Specification;


public interface IRunner {


	void configure(Specification z3Spec, Set<String> doneProps) throws IOException;

	void solve();
	
	Specification getSpec();
	
	Set<String> doneProps();
	Boolean taskDone();
	
}
