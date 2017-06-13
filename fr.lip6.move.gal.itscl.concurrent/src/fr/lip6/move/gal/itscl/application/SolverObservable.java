package fr.lip6.move.gal.itscl.application;

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

	private Set<ISolver> obs = new HashSet<>();
	private List<Future<Integer>> fsolvers = null;
	private ExecutorService executor = Executors.newCachedThreadPool();

	public void attach(ISolver o) {
		obs.add(o);

	}

	public void detach(ISolver o) {
		obs.remove(o);
	}

	@Override
	public void killAll() {
		@SuppressWarnings("unused")
		List<Runnable> notFinished = executor.shutdownNow(); // savoir qui a
																// fini ?

		for (Future<Integer> o : fsolvers) {
			if (!o.isCancelled() || !o.isDone()) {
				// Erreur un thread n'a pas ete shutdown
				o.cancel(true);
			}

		}
	}

	public Boolean call() {
		for (ISolver o : obs) {
			fsolvers.add(executor.submit(o));
		}
		int nbSolver = obs.size(), i = 0;
		CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(executor);

		do {
			try {

				Future<Integer> solverDone = completionService.take();
				if (solverDone.isDone() && solverDone.get() == 0) {
					System.out.println("Solved by : " + solverDone.getClass());
					killAll();
					return true;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch( ExecutionException ee){
				ee.printStackTrace();
				return false;
			}
		} while (++i < nbSolver);

		System.out.println("Problem not solved");
		return false;
	}

}
