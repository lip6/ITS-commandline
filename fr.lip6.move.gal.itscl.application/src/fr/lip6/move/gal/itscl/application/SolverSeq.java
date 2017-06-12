package fr.lip6.move.gal.itscl.application;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.eclipse.core.runtime.IStatus;

import fr.lip6.move.gal.itstools.CommandLine;
import fr.lip6.move.gal.itstools.ProcessController.TimeOutException;
import fr.lip6.move.gal.itstools.Runner;

public class SolverSeq extends ItsSolver implements ISolverSeq{
		
	
	private ByteArrayOutputStream baos = new ByteArrayOutputStream();
	private String output;
	private IListener lst;

	
	public SolverSeq(Problem p, CommandLine cl) {
		super(p, cl);
	}
	

	public void currentState(){
		System.out.println(baos.toString());
	}
	
	public int isComplete(){
		return output.contains("Error")? 1 : 0;
	}
	
	public void configListener(IListener lst){
		this.lst=lst;
	}


	@Override
	public Integer call(){
	
		try {			
				IStatus status;
				status = Runner.runTool(p.getTimeout() , getCmd(), baos, true,lst);
				
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


	
	
	
}
	
