package fr.lip6.move.gal.itscl.application;

import java.io.IOException;

import org.eclipse.core.runtime.IStatus;

import fr.lip6.move.gal.itstools.ProcessController.TimeOutException;
import fr.lip6.move.gal.itstools.Runner;

public class SolverSeq implements ItsSolver{
	
	
	synchronized public ResultProblem solve (Problem p){
		IStatus st=Status.;
		try {
			st = Runner.runTool(3500, p.getCmd(), System.out, true);
			
		} catch (IOException | TimeOutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ResultProblem(st);
		
	}

}
