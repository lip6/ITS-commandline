package fr.lip6.move.gal.itscl.application;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.eclipse.core.runtime.IStatus;

import fr.lip6.move.gal.application.Ender;
import fr.lip6.move.gal.itstools.CommandLine;
import fr.lip6.move.gal.itstools.ProcessController.TimeOutException;
import fr.lip6.move.gal.itstools.Runner;

public class SolverSeq extends ItsSolver implements ISolver, Callable<Integer>{
		
	
	private ByteArrayOutputStream baos = new ByteArrayOutputStream();
	private String output;

	public SolverSeq(Problem p, CommandLine cl) {
		super(p, cl);
	}
	

	@Override
	public Integer call(){
	
		try {			
				IStatus status;
				status = Runner.runTool(p.getTimeout() , getCmd(), baos, true);
				
				if (! status.isOK() && status.getCode() != 1) {
					throw new RuntimeException("Unexpected exception when executing commandline :"+ getCmd() +"\n" +status);
				}
				output= baos.toString();
				
				return isComplete();
				
					
		} catch (IOException | TimeOutException e) {
			e.printStackTrace();
			return -1;
		}
				
	}

	
	@Override
	public void currentState(){
		System.out.println(baos.toString());
	}
	
	@Override
	public int isComplete(){
		return output.contains("Error")? 1 : 0;
	}



	
	
	
}
	
