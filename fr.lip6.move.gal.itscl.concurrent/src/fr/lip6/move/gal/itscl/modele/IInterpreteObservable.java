package fr.lip6.move.gal.itscl.modele;

import java.util.concurrent.Callable;

public interface IInterpreteObservable extends Ender, Callable<Boolean> {
	public void attach(IListener o);

	public void detach(IListener o);
}
