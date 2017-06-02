package fr.lip6.move.gal.itscl.application;

import java.io.IOException;

import org.eclipse.core.runtime.IStatus;

import fr.lip6.move.gal.itstools.CommandLine;
import fr.lip6.move.gal.itstools.ProcessController.TimeOutException;
import fr.lip6.move.gal.itstools.Runner;

public class SolverSeq extends ItsSolver implements Runnable{
	
	
	
	public SolverSeq(Problem p, CommandLine cl) {
		super(p, cl);
	}

	public void run(){
		synchronized(getP()){
			try {
					IStatus st = Runner.runTool(3500, getCmd(), System.out, true);
					if(st.isOK())
						setResult(new ResultP(ResultP.OK));
					
				} catch (IOException | TimeOutException e) {
					setResult(new ResultP(ResultP.KO));
				}
				
		}	
	}
	
	
	
}
