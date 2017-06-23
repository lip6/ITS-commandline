package fr.lip6.move.gal.itscl.modele;

import java.util.Set;

import fr.lip6.move.gal.Specification;


public interface IProblem {

	public Specification getSpec();

	public Set<String> getDoneProps();



}
