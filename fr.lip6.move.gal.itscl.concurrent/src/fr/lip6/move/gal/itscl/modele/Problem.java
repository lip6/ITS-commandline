package fr.lip6.move.gal.itscl.modele;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import fr.lip6.move.gal.Specification;

public abstract class Problem implements IProblem {

	protected Specification spec;
	protected Set<String> doneProps;

	public Specification getSpec() {
		return spec;
	}

	public Set<String> getDoneProps() {
		return doneProps;
	}

	public void configure(Specification z3Spec, Set<String> doneProps) throws IOException {
		this.spec = z3Spec;
		this.doneProps = doneProps;
	}
	
	public void configure(Specification spec) {
		try {
			configure(spec, ConcurrentHashMap.newKeySet());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
