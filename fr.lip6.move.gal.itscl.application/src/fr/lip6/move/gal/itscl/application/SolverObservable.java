package fr.lip6.move.gal.itscl.application;

import java.util.HashSet;
import java.util.Set;

import fr.lip6.move.gal.application.Ender;

public class SolverObservable implements ISolverObservable,Ender {
	private Set<ISolverObserver> obs = new HashSet<>();
	
	public void notifyObservers(ResultP res,ISolverObserver solver) {
		solver.notifyResult(res);
		if (res.isOK())
			killAll();
		else
			this.detach(solver);
	}

	public void attach(ISolverObserver o) {
		obs.add(o);
	}

	public void detach(ISolverObserver o) {
		obs.remove(o);
	}

	public void killAll() {
		for (ISolverObserver o : obs) {
			o.interrupt();
			try {
				o.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}		
	}

}
