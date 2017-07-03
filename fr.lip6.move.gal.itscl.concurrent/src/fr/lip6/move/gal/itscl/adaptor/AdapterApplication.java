package fr.lip6.move.gal.itscl.adaptor;

import fr.lip6.move.gal.itscl.modele.IRunner;
import fr.lip6.move.gal.itscl.modele.ISolverSeq;

public class AdapterApplication {

	public static ISolverSeq add(IRunner r) {
		return new Adapter(r);
	}

}
