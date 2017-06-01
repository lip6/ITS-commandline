package fr.lip6.move.gal.itscl.application;

import java.io.IOException;

import org.eclipse.core.runtime.IStatus;

import fr.lip6.move.gal.itstools.ProcessController.TimeOutException;
import fr.lip6.move.gal.itstools.Runner;

public class SolverSeq extends ItsSolver{
	
	
	synchronized public ResolveProblem solve(Problem p){
		try {
			IStatus st = Runner.runTool(3500, p.getCmd(), System.out, true);
			if(st.isOK())
				return new ResolveProblem(ResolveProblem.OK);
			
		} catch (IOException | TimeOutException e) {
			return new ResolveProblem(ResolveProblem.KO);
		}

		return new ResolveProblem(ResolveProblem.UNKNOWN);
		
	}

}
