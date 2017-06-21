package fr.lip6.move.gal.itscl.modele;

import fr.lip6.move.gal.Specification;

public abstract class Problem implements IProblem {

	protected Specification spec;

	public void configue(Specification spec) {
		this.spec = spec;
	}

	public Specification getSpec() {
		return spec;
	}

}
