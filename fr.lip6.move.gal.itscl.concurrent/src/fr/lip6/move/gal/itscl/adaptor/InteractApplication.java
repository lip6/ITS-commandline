package fr.lip6.move.gal.itscl.adaptor;

import fr.lip6.move.gal.itscl.modele.IRunner;
import fr.lip6.move.gal.itscl.modele.ISolverSeq;

public class InteractApplication {

	public static ISolverSeq add(IRunner r) {
		return new Adapter(r);
	}

}
