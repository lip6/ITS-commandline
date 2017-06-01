package fr.lip6.move.gal.itscl.application;

import fr.lip6.move.gal.Specification;
import fr.lip6.move.gal.itstools.BinaryToolsPlugin.Tool;

public class Problem{

	private Specification spec;
	private Tool tool;
	
	public Problem(Specification spec, Tool tool) {
		this.spec = spec;
		this.tool = tool;
	}

	public Specification getSpec() {
		return spec;
	}
	
	public Tool getTool() {
		return tool;
	}
}
