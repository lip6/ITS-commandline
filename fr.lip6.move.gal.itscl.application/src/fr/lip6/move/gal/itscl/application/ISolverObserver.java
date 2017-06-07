package fr.lip6.move.gal.itscl.application;

public interface ISolverObserver {

	public void notifyResult(ResultP res);
	public void interrupt();
	public void join() throws InterruptedException;

}
