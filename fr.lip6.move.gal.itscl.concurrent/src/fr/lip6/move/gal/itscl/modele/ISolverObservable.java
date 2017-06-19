package fr.lip6.move.gal.itscl.modele;

import java.util.concurrent.Callable;

public interface ISolverObservable extends Callable<Boolean>, Ender {

	public void attach(ISolverSeq obs);

	public void detach(ISolverSeq obs);

}
