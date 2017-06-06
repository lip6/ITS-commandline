package fr.lip6.move.gal.itscl.application;

import java.util.HashSet;
import java.util.Set;

public class SolverObservable implements ISolverObservable {
	private Set<ISolverObserver> obs = new HashSet<>();
	
	@Override
	public void notifyObservers(ResultP res,ISolverObserver solver) {
		solver.notifyResult(res);

		if (res.isOK()){
			for (ISolverObserver o : obs) {
				o.problemIsSolved();
			}
		}
		else{
			this.detach(solver);
		}

	}

	@Override
	public void attach(ISolverObserver o) {
		obs.add(o);
	}

	@Override
	public void detach(ISolverObserver o) {
		obs.remove(o);
	}

}
