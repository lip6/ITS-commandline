package fr.lip6.move.gal.itscl.application;

import java.util.HashSet;
import java.util.Set;

import fr.lip6.move.gal.application.Ender;

public class SolverObservable implements ISolverObservable,Ender {
	private Set<ISolverObserver> obs = new HashSet<>();
	
	@Override
	public void notifyObservers(ResultP res,ISolverObserver solver) {
	
		solver.notifyResult(res);

		if (res.isOK()){
			killAll();
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

	@Override
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
