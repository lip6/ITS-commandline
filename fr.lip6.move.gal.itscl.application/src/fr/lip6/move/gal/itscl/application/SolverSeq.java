package fr.lip6.move.gal.itscl.application;

import java.io.IOException;

import org.eclipse.core.runtime.IStatus;

import fr.lip6.move.gal.itscl.interpreter.InterpreteBytArray;
import fr.lip6.move.gal.itstools.CommandLine;
import fr.lip6.move.gal.itstools.ProcessController.TimeOutException;
import fr.lip6.move.gal.itstools.Runner;

public class SolverSeq extends ItsSolver {

	private InterpreteBytArray bufferWIO = new InterpreteBytArray();
	private Boolean complete = true;
	private InterpreterApp interp;

	public SolverSeq(ProblemSS p, CommandLine cl) {
		super(p, cl);
	}

	public void setResult() {
		this.complete =false;
	}

	public int hasComplete() {
		interp.acquireResult();
		return complete?0:1;
	}

	public Integer call() {

		try {
			IStatus status;
			interp = new InterpreterApp(bufferWIO,this);
			Thread interpTh = new Thread(interp, "InterpreterApp");
			inRunner.addThInterprete(interpTh);
			interpTh.start();
			status = Runner.runTool(p.getTimeout(), getCmd(), bufferWIO.getPout(), true);
			
			
			if (!status.isOK() && status.getCode() != 1) {
				throw new RuntimeException(
						"Unexpected exception when executing commandline :" + getCmd() + "\n" + status);
			}
			return hasComplete();

		} catch (IOException | TimeOutException e) {
			e.printStackTrace();
			return -1;
		}

	}

}
