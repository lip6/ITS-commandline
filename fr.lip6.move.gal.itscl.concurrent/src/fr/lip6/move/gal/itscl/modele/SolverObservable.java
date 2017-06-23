package fr.lip6.move.gal.itscl.modele;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SolverObservable implements ISolverObservable {

	private Set<ISolverSeq> obs = new HashSet<>();
	private List<Future<Integer>> fsolvers = new ArrayList<Future<Integer>>();
	private ExecutorService executor = Executors.newCachedThreadPool();
	private InterpreteObservable interp;
	
	public void attach(ISolverSeq o) {
		obs.add(o);
	}

	public void detach(ISolverSeq o) {
		obs.remove(o);
	}

	/**
	 * Shutdown all threads that still running assure that they really been
	 * canceled call method to kill the interpreters of each runner
	 */
	public void killAll() {

		List<Runnable> notFinished = executor.shutdownNow(); // savoir qui a
		if (notFinished == null) {
			System.out.println("they all complete");// fini
		}
		for (Future<Integer> o : fsolvers) {
			if (!o.isDone()) {
				o.cancel(true);
			}
		}
		
		interp.notify();
	}

	/**
	 * wait a least five minutes till all interpeters have terminate
	 */
	

	/**
	 * wait till a solver has finish. if it done completely the task : kill all
	 * the solvers and their interpreters, if not : repeat the process
	 */
	public Boolean call() {

		CompletionService<Integer> runSolvers = new ExecutorCompletionService<Integer>(executor);

		for (ISolverSeq o : obs) {
			fsolvers.add(runSolvers.submit(o));
		}

	
		int nbSolver = obs.size(), i = 0;

		do {
			try {
				// waiting for the first solver to terminate
				Future<Integer> solverDone = runSolvers.take();
				// Test if it has completed with no error
				if (solverDone.get() == 0) {
					killAll();
					return true;
				}
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
				return false;

			}
		} while (++i < nbSolver);

		return false;
	}

}