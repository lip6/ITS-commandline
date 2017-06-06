package fr.lip6.move.gal.itscl.application;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.lip6.move.gal.Property;
import fr.lip6.move.gal.Specification;
import fr.lip6.move.gal.itstools.BinaryToolsPlugin.Tool;
import fr.lip6.move.serialization.SerializationUtil;

public class Problem implements IProblem{

	private Specification spec;
	private final Tool tool;
	private int timeout;
	private String folder;

	
	public Problem(Specification spec, Tool tool,int timeout, String folder) {
		this.spec = spec;
		this.tool= tool;
		this.timeout= timeout;
		this.folder= folder;
	}

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
	
	
	public void outputGalFile(Specification spec, String outpath) throws IOException {
		if (! spec.getProperties().isEmpty()) {
			List<Property> props = new ArrayList<Property>(spec.getProperties());
			spec.getProperties().clear();
			SerializationUtil.systemToFile(spec, outpath);
			spec.getProperties().addAll(props);
		} else {
			SerializationUtil.systemToFile(spec, outpath);
		}
	}
	



}