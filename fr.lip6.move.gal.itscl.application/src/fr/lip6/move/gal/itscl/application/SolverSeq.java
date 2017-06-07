package fr.lip6.move.gal.itscl.application;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.eclipse.core.runtime.IStatus;

import fr.lip6.move.gal.application.Ender;
import fr.lip6.move.gal.itstools.CommandLine;
import fr.lip6.move.gal.itstools.ProcessController.TimeOutException;
import fr.lip6.move.gal.itstools.Runner;

public class SolverSeq extends ItsSolver implements ISolverSeq, ISolverObserver {
	
	private Thread runnerTh;
	
	public SolverSeq(Problem p, CommandLine cl) {
		super(p, cl);
	}


	public void solve(Ender obs){
		ISolverObserver here=this;
			
		runnerTh = new Thread(new Runnable (){
			
			public void run(){
				try {				
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						IStatus status = Runner.runTool(p.getTimeout() , getCmd(), baos, true);
						if (! status.isOK() && status.getCode() != 1) {
							throw new RuntimeException("Unexpected exception when executing commandline :"+ getCmd() +"\n" +status);
						}
						String output = baos.toString();
						ResultP res = output.contains("Problem ?") ? new ResultP(ResultP.KO) : new ResultP(ResultP.OK);
						// analyse 
						obs.notifyObservers(res,here);
							
					} catch (IOException | TimeOutException e) {
						obs.notifyObservers(new ResultP(ResultP.KO),here);
					}
					
		}
		});
		
		runnerTh.start();

	}
	
	public void notifyResult(ResultP res) {
		System.out.println("furst threeadd!  exit :"+res);
	}
	
	public void interrupt(){
		if(runnerTh != null)
			runnerTh.interrupt();
	}
	
	public void join() throws InterruptedException{
		if(runnerTh != null)
			runnerTh.join();
	}
	
	
}
	
