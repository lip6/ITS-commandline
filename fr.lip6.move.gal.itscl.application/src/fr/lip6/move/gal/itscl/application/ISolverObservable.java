package fr.lip6.move.gal.itscl.application;


import fr.lip6.move.gal.application.Ender;

public interface ISolverObservable extends Ender {
	void attach(ISolver obs) ;
	void detach(ISolver obs) ;
	
}
