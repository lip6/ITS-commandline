package fr.lip6.move.gal.itscl.application;

import fr.lip6.move.gal.Specification;

public abstract class Problem implements IProblem {

	protected Specification spec;

	public void configure(Specification spec) {
		this.spec = spec;
	}

	public Specification getSpec() {
		return spec;
	}

}
