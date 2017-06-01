package fr.lip6.move.gal.itscl.application;

import java.io.IOException;

import org.eclipse.core.runtime.IStatus;

import fr.lip6.move.gal.itstools.CommandLine;
import fr.lip6.move.gal.itstools.ProcessController.TimeOutException;
import fr.lip6.move.gal.itstools.Runner;

public class SolverSeq extends ItsSolver{
	

	public SolverSeq(CommandLine cmd) {
		super(cmd);
	}

	synchronized public ResultP solve(Problem p){
		try {
			
			IStatus st = Runner.runTool(3500, this.getCmd(), System.out, true);
			if(st.isOK())
				return new ResultP(ResultP.OK);
			
		} catch (IOException | TimeOutException e) {
			return new ResultP(ResultP.KO);
		}

		return new ResultP(ResultP.UNKNOWN);
		
	}
	
	
	
}
