package fr.lip6.move.gal.itscl.application;

public interface ISolverObservable {
	void attach(ISolverObserver obs) ;
	void detach(ISolverObserver obs) ;
	void notifyObservers(ResultP res,ISolverObserver solver);
}
