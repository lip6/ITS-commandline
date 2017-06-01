package fr.lip6.move.gal.itscl.application;

import fr.lip6.move.gal.Specification;
import fr.lip6.move.gal.itstools.BinaryToolsPlugin.Tool;

public class Problem{

	private Specification spec;
	private Tool tool;
	private String cwd;

	
	public Problem(Specification spec, Tool tool,String cwd) {
		this.spec = spec;
		this.tool = tool;
		this.cwd=cwd;
	}

	public Specification getSpec() {
		return spec;
	}
	
	public Tool getTool() {
		return tool;
	}

	public String getCwd() {
		return cwd;
	}

}
