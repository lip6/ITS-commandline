package fr.lip6.move.gal.itscl.interprete;

import java.util.ArrayList;

import fr.lip6.move.gal.itscl.modele.SolverObservable;

public class InterpreteObservable implements IInterpreteObservable {
	private ArrayList<Thread> interpTh = new ArrayList<Thread>();
	private SolverObservable so;

	public InterpreteObservable(SolverObservable so) {
		this.so = so;
	}

	/**
	 * wait a least five minutes till all interpreters have terminate
	 */
	public Boolean call() {
		try {
			//notify qu'ils ont été lancés avant 
			for (int i = 0; i < interpTh.size(); i++) {
				Thread th = interpTh.get(i);
				if (!th.isInterrupted()){
					System.out.println("Name threads : "+th.getName());
					th.join();
				}
			}
			System.out.println("Interpreters are done !");
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
}
