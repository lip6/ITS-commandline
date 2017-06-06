package fr.lip6.move.gal.itscl.application;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.eclipse.core.runtime.IStatus;

import fr.lip6.move.gal.itstools.CommandLine;
import fr.lip6.move.gal.itstools.ProcessController.TimeOutException;
import fr.lip6.move.gal.itstools.Runner;

public class SolverSeq extends ItsSolver implements ISolverSeq, ISolverObserver {
	
	private Thread runnerTh;
	public SolverSeq(Problem p, CommandLine cl,SolverObservable obs) {
		super(p, cl,obs);
	}


	public Thread solve(){
		ISolverObserver here=this;
			
		runnerTh = new Thread(new Runnable (){
			
			public void run(){
				try {				
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						IStatus status = Runner.runTool(p.getTimeout() , getCmd(), baos, true);
						if (! status.isOK() && status.getCode() != 1) {
							throw new RuntimeException("Unexpected exception when executing commandline :"+ getCmd() +"\n" +status);
						}
						boolean result ;
						String output = baos.toString();
						ResultP res = output.contains("Problem ?") ? new ResultP(ResultP.KO) : new ResultP(ResultP.OK);
						// analyse 
						obs.notifyObservers(res,here);
							
					} catch (IOException | TimeOutException e) {
						notifyObservers(new ResultP(ResultP.KO),here);
					}
					
		}
		});
		
		runnerTh.start();
		return runnerTh;
		
	}
	
	public void notifyResult(ResultP res) {
		System.out.println("furst threeadd!  exit :"+res);
	}
	
	public void problemIsSolved(){
		runnerTh.interrupt();
	}


}
	
