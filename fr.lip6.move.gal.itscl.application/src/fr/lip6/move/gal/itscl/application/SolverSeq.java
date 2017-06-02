package fr.lip6.move.gal.itscl.application;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.eclipse.core.runtime.IStatus;

import fr.lip6.move.gal.itstools.CommandLine;
import fr.lip6.move.gal.itstools.ProcessController.TimeOutException;
import fr.lip6.move.gal.itstools.Runner;

public class SolverSeq implements ISolverSeq, Runnable, ISolverObservable {
	
	
	
	public SolverSeq(Problem p, CommandLine cl) {
		super(p, cl);
	}

	public void run(){
		synchronized(getP()){
			try {
				
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				int timeout = 3500;
				IStatus status = Runner.runTool(timeout , getCmd(), baos, true);
				if (! status.isOK() && status.getCode() != 1) {
					throw new RuntimeException("Unexpected exception when executing ltsmin :"+ ltsmin +"\n" +status);
				}
				boolean result ;
				String output = baos.toString();
				ResultP res = output.contains(" A voir") ? new ResultP(1) : new ResultP(0);
				// analyse 
				notifyObservers(res );
				IStatus st = Runner.runTool(3500, getCmd(), System.out, true);
					if(st.isOK())
						setResult(new ResultP(ResultP.OK));
					
				} catch (IOException | TimeOutException e) {
					setResult(new ResultP(ResultP.KO));
				}
				
		}	
	}
	
	
	
}
