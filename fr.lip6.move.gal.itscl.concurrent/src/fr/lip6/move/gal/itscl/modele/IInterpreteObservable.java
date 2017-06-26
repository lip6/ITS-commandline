package fr.lip6.move.gal.itscl.modele;


public interface IInterpreteObservable extends Ender, Runnable {
	public void launchInterprete(IListener o);

}
