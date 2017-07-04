package fr.lip6.move.gal.itscl.interpreter;

import java.util.ArrayList;

import fr.lip6.move.gal.itscl.modele.ISolverObservable;

public class InterpreteObservable implements IInterpreteObservable {

	private ArrayList<Thread> interpTh = new ArrayList<Thread>();
	private ISolverObservable so;

	public InterpreteObservable(ISolverObservable so) {
		this.so = so;
	}

	public void addThInterprete(Thread interpTh) {
		this.interpTh.add(interpTh);
	}

	/**
	 * Join all interpreters in the list to ensure they complete well.
	 */
	public Boolean call() {
		try {
			// waits SolverObservable to acquire a result from a solver OR no
			// one did
			so.waitSolver();

			for (int i = 0; i < interpTh.size(); i++) {
				Thread th = interpTh.get(i);
				if (!th.isInterrupted()) {
					th.join();
					System.out.println("Name threads : " + th.getName());

				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}

		// wake SolverObservable
		so.notifySolver();

		return true;
	}

}
