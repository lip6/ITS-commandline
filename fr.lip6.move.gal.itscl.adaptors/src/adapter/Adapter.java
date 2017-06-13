package adapter;

import fr.lip6.move.gal.itscl.application.IRunner;
import fr.lip6.move.gal.itscl.application.ISolverSeq;

public class Adapter implements ISolverSeq {

	protected IRunner runner;

	public Adapter(IRunner r) {
		this.runner = r;
	}

	public void currentState() {

	}

	public int isComplete() {
		return runner.taskDone() ? 0 : 1;
	}

	public Integer call() throws Exception {

		runner.solve();

		return isComplete();

	}

}
