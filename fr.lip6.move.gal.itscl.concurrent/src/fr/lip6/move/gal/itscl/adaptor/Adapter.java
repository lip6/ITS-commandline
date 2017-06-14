package fr.lip6.move.gal.itscl.adaptor;

import fr.lip6.move.gal.itscl.application.IRunner;
import fr.lip6.move.gal.itscl.application.ISolverSeq;

public class Adapter implements ISolverSeq {

	protected IRunner runner;

	public Adapter(IRunner r) {
		this.runner = r;
	}

	public int isComplete() {
		System.out.println("Solved by :"+this.getClass().getName());

		return runner.taskDone() ? 0 : 1;
	}

	public Integer call() throws Exception {

		runner.solve();

		return isComplete();

	}

}
