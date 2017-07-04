package fr.lip6.move.gal.itscl.adaptor;

import fr.lip6.move.gal.itscl.modele.IRunner;
import fr.lip6.move.gal.itscl.modele.ISolverSeq;

public class Adapter implements ISolverSeq {

	protected IRunner runner;

	public Adapter(IRunner r) {
		this.runner = r;
	}

	public int hasComplete() {
		return runner.taskDone() ? 0 : 1;
	}

	public Integer call() throws Exception {
		runner.solve();
		return hasComplete();

	}

}
