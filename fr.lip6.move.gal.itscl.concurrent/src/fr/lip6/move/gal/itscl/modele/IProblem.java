package fr.lip6.move.gal.itscl.modele;

import fr.lip6.move.gal.Specification;

public interface IProblem {

	public Specification getSpec();

	public void configure(Specification spec);

}
