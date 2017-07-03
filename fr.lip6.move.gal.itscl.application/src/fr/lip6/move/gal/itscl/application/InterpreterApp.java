package fr.lip6.move.gal.itscl.application;


import fr.lip6.move.gal.itscl.interpreter.AbstractInterpreter;
import fr.lip6.move.gal.itscl.interpreter.InterpreteBytArray;

public class InterpreterApp extends AbstractInterpreter {

	private InterpreteBytArray bufferWIO;
	private SolverSeq sq;

	public InterpreterApp(InterpreteBytArray buff, SolverSeq sq) {
		this.bufferWIO = buff;
		this.sq = sq;
	}

	public void run() {
		String output = bufferWIO.getPout().toString();

		if (output.contains("Error"))
			sq.setResult();
		else
			System.out.println("  "+output);
		
		releaseResult();
	}

}
