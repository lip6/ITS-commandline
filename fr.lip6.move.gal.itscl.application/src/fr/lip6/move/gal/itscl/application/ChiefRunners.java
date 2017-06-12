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
import java.util.concurrent.TimeUnit;


public class ChiefRunners implements  ISolverObservable {
	
	private Set<ISolver> obs = new HashSet<>();
	private List <Future<Integer>> fsolvers=null;
	private ExecutorService executor= Executors.newCachedThreadPool();
	private int timeout;
	private TimeUnit unit;
	
	
	public void attach(ISolver o) {
		obs.add(o);
		
	}

	public void detach(ISolver o) {
		obs.remove(o);
	}
	
	public void setTimeout(int timeout,TimeUnit u){
		this.timeout=timeout;
		unit=u;
	}
	
	

	public boolean taskDone(int result){
		if(result==0)
			return true;
		return false;
	}
	

	@Override
	public void killAll() {
		List<Runnable> notFinished = executor.shutdownNow();  // savoir qui a fini ?
		
		for (Future<Integer> o : fsolvers){
			if(o.isDone()){  //pas vraiment celle qui a fini 
				//celui qui a fini
			}else if(!o.isCancelled()){
				//Erreur un thread n'a pas ete shutdown
				o.cancel(true); 
			}
			
		}
	}
		
		
	public Boolean call(){
		try{
			for(ISolver o : obs){
				fsolvers.add(executor.submit(o));
			}
			int stillSolver=obs.size(), i=0;
			CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(executor);
		
			do{
				Future<Integer> solverDone=completionService.poll(timeout,unit);	
				if (solverDone.isDone() && taskDone(solverDone.get())){
					killAll();
					return true;
				}
				i++;
				
			}while(i<stillSolver);
			
		}catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			return false;
		}

		return false;
	}

	public void configureListener(Listener lst) {
		for(ISolver o: obs){
			o.configListener(lst);
		}
	}
	


}
