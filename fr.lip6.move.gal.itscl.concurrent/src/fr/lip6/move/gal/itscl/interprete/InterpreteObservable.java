package fr.lip6.move.gal.itscl.interprete;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import fr.lip6.move.gal.itscl.modele.SolverObservable;

public class InterpreteObservable implements IInterpreteObservable {
	private ArrayList<Thread> interpTh = new ArrayList<Thread>();
	private SolverObservable so;
	private Semaphore complete = new Semaphore(0);

	public InterpreteObservable(SolverObservable so) {
		this.so = so;
	}

	/**
	 * wait a least five minutes till all interpreters have terminate
	 */
	public Boolean call() {
		try {
			// notify qu'ils ont été lancés avant
			so.wakeMeUp();
			
			for (int i = 0; i < interpTh.size(); i++) {
				Thread th = interpTh.get(i);
				if (!th.isInterrupted()) {
					th.join();
					System.out.println("Name threads : " + th.getName());
					
				}
				complete.release();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
		so.notifySolver();

		return true;
	}

	public void addThInterprete(Thread interpTh) {
		this.interpTh.add(interpTh);
	}

	public void acquireFinalResult() {
		try {
			complete.acquire();
		} catch (InterruptedException e) {
		}
	}
}
