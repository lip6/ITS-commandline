package fr.lip6.move.gal.itscl.application;

import fr.lip6.move.gal.Specification;
import fr.lip6.move.gal.itstools.BinaryToolsPlugin.Tool;

public class Problem implements IProblem{

	private Specification spec;
	private Tool tool;
	private String pwd;

	
	public Problem(Specification spec, Tool tool,String pwd) {
		this.spec = spec;
		this.tool = tool;
		this.pwd=pwd;
	}

	public Specification getSpec() {
		return spec;
	}
	
	public Tool getTool() {
		return tool;
	}

	public String getPwd() {
		return pwd;
	}

}
