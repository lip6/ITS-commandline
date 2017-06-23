package fr.lip6.move.gal.itscl.application;

import fr.lip6.move.gal.Specification;
import fr.lip6.move.gal.itscl.modele.Problem;
import fr.lip6.move.gal.itstools.BinaryToolsPlugin.Tool;

public class ProblemSS extends Problem {

	private Tool tool;
	private String folder;
	private int timeout;

	public ProblemSS(Tool tool, String folder) {
		this.tool = tool;
		this.folder = folder;
	}

	public void configure(Specification spec, int timeout) {
		this.spec = spec;
		this.timeout = timeout;
	}
	
	public Tool getTool() {
		return tool;
	}

	public String getFolder() {
		return folder;
	}

	public int getTimeout() {
		return timeout;
	}

	

}