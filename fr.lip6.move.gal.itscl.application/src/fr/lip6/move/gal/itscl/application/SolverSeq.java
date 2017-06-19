package fr.lip6.move.gal.itscl.application;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.eclipse.core.runtime.IStatus;

import fr.lip6.move.gal.itscl.modele.ResultP;
import fr.lip6.move.gal.itstools.CommandLine;
import fr.lip6.move.gal.itstools.ProcessController.TimeOutException;
import fr.lip6.move.gal.itstools.Runner;

public class SolverSeq extends ItsSolver {

	private ByteArrayOutputStream baos = new ByteArrayOutputStream();
	private String output;

	public SolverSeq(ProblemSS p, CommandLine cl) {
		super(p, cl);
	}

	public int isComplete() {
		return output.contains("Error") ? ResultP.KO : ResultP.OK;
	}

	public Integer call() {

		try {
			IStatus status;
			status = Runner.runTool(p.getTimeout(), getCmd(), baos, true);

			if (!status.isOK() && status.getCode() != 1) {
				throw new RuntimeException(
						"Unexpected exception when executing commandline :" + getCmd() + "\n" + status);
			}
			output = baos.toString();

			return isComplete();

		} catch (IOException | TimeOutException e) {
			e.printStackTrace();
			return -1;
		}

	}

}
