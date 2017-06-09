package fr.lip6.move.gal.itscl.application;



import org.eclipse.emf.common.util.EList;

import fr.lip6.move.gal.itstools.BinaryToolsPlugin.Tool;

public class Problem implements IProblem{

	private Specification spec;
	private final Tool tool;
	private int timeout;
	private String folder;
	private Object Props;

	
	public Problem(Specification spec, Tool tool,int timeout, String folder) {
		this.spec = spec;
		this.tool= tool;
		this.timeout= timeout;
		this.folder= folder;
	}

	@Override
	public Specification getSpec() {
		return spec;
	}
	
	public String getFolder() {
		return folder;
	}

	public int getTimeout() {
		return timeout;
	}

	public Tool getTool() {
		return tool;
	}


	
	
	



}