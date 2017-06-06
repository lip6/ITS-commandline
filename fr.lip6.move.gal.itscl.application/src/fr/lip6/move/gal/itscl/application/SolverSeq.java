package fr.lip6.move.gal.itscl.application;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.eclipse.core.runtime.IStatus;

import fr.lip6.move.gal.itstools.CommandLine;
import fr.lip6.move.gal.itstools.ProcessController.TimeOutException;
import fr.lip6.move.gal.itstools.Runner;

public class SolverSeq extends ItsSolver implements ISolverSeq, ISolverObserver {
	
	public SolverSeq(Problem p, CommandLine cl) {
		super(p, cl);
	}


	public Thread solve(){
			
		Thread runnerTh = new Thread(new Runnable (){
			
			public void run(){
				try {				
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						IStatus status = Runner.runTool(p.getTimeout() , getCmd(), baos, true);
						if (! status.isOK() && status.getCode() != 1) {
							throw new RuntimeException("Unexpected exception when executing ltsmin :"+ ltsmin +"\n" +status);
						}
						boolean result ;
						String output = baos.toString();
						ResultP res = output.contains("Problem ?") ? new ResultP(ResultP.KO) : new ResultP(ResultP.OK);
						// analyse 
						notifyObservers(res );
							
					} catch (IOException | TimeOutException e) {
						notifyObservers(new ResultP(ResultP.KO));
					}
					
		}
		});
		
		runnerTh.start();
		return runnerTh;
		
	}


	@Override
	public void notifyResult(ResultP res) {
		// TODO Auto-generated method stub
		
	}
}
	
