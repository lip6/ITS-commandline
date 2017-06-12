package fr.lip6.move.gal.itscl.application;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

import fr.lip6.move.gal.CTLProp;
import fr.lip6.move.gal.LTLProp;
import fr.lip6.move.gal.Property;
import fr.lip6.move.gal.SafetyProp;
import fr.lip6.move.gal.Specification;
import fr.lip6.move.gal.instantiate.GALRewriter;
import fr.lip6.move.gal.itstools.CommandLine;
import fr.lip6.move.gal.itstools.CommandLineBuilder;
import fr.lip6.move.gal.itstools.BinaryToolsPlugin.Tool;
import fr.lip6.move.serialization.SerializationUtil;
import java.util.concurrent.TimeUnit;

 
public class Application implements IApplication {
	private static final String APPARGS = "application.args";
	private static final String INPUT_FILE = "-i";
	private static final String INPUT_TYPE = "-t";
	private static final String REACH_EXAM = "-reach";
	private static final String CTL_EXAM = "-ctl";
	private static final String LTL_EXAM = "-ltl";
	
	
	private ExecutorService exec;
	/* (non-Javadoc)
	 * @see org.eclipse.equinox.app.IApplication#start(org.eclipse.equinox.app.IApplicationContext)
	 */
	@Override
	public Object start(IApplicationContext context) throws Exception {
		
		String [] args = (String[]) context.getArguments().get(APPARGS);
		
		String inputff = null;
		String inputType = null;

		Tool tool = Tool.reach;
		boolean doIts = false;
		
	
		for (int i=0; i < args.length ; i++) {
			if (INPUT_FILE.equals(args[i])) {
				inputff = args[++i];
			} else if (INPUT_TYPE.equals(args[i])) {
				inputType = args[++i]; 
			} else if (REACH_EXAM.equals(args[i])) {
				tool = Tool.reach;
				doIts = true;
			} else if (CTL_EXAM.equals(args[i])) {
				tool = Tool.ctl;
				doIts = true;
			} else if (LTL_EXAM.equals(args[i])) {
				tool = Tool.ltl;
				doIts = true;
//			} else if (LTSMINPATH.equals(args[i])) {
//				ltsminpath = args[++i];
//				doLTSmin = true;
//			} else if (ITS.equals(args[i])) {
//				doITS = true;
//			} else if (disablePOR.equals(args[i])) {
//				doPOR = false;
//			} else if (ONLYGAL.equals(args[i])) {
//				onlyGal = true;
			}
		}
		
	
		if (inputff == null) {
			System.err.println("Please provide input file with -i option");
			return null;
		}
		
		if(!doIts){
			System.err.println("Please provide tool (ex: reach, ctl, ltl...)");
		}
		
		File ff = new File(inputff);
		if (! ff.exists()) {
			System.err.println("Input file "+inputff +" does not exist");
			return null;
		}
		
		String pwd = ff.getParent();
		String modelName = ff.getName().replace(".gal", "");
		SerializationUtil.setStandalone(true);
		
		//parse l'entrée
		long time = System.currentTimeMillis();
		Specification spec = SerializationUtil.fileToGalSystem(inputff);		
		System.out.println("Successfully read input file : " + inputff +" in " + (time - System.currentTimeMillis()) + " ms.");

		time = System.currentTimeMillis();
		Problem p = loadModel(pwd, spec, tool);
		System.out.println("Simplifications done in " + (time - System.currentTimeMillis()) + " ms.");

		time = System.currentTimeMillis();
		CommandLine cl= getCmdLine(spec,p.getFolder(),modelName,tool);
		System.out.println("Built GAL and property files in "+ (time - System.currentTimeMillis()) + " ms.");
		ChiefRunners superRunner= new ChiefRunners();
		exec = Executors.newSingleThreadExecutor();
		
		SolverSeq s = new SolverSeq(p,cl);
		superRunner.attach(s);
		//run les solvers
		superRunner.setTimeout(3500,TimeUnit.MILLISECONDS);
		Listener lst= new Listener();
		superRunner.configureListener(lst);
		exec.submit(superRunner);
	
		
		return IApplication.EXIT_OK;
	}
	
	
	
	// Traitement du problème : transformations + simplifications
	public Problem loadModel(String pwd,Specification spec, Tool tool) throws IOException{
		
		String cwd = pwd + "/work";
		File fcwd = new File(cwd);
		if (! fcwd.exists()) {
			if (! fcwd.mkdir()) {
				System.err.println("Could not set up work folder in "+cwd);
			}
		}		
		GALRewriter.flatten(spec, true);

		return new Problem(spec,tool,3500,cwd);
	}
	
	
	// On produit un fichier de modèle pour l'outil ligne de commande
	public CommandLine getCmdLine(Specification spec, String cwd, String modelName,Tool tool) throws IOException{
		
		String outpath = cwd+"/"+ modelName + ".gal";
		outputGalFile(spec, outpath);	
		
		CommandLine cl = buildCommandLine(outpath, tool);
	
		if (tool == Tool.reach) {
			spec.getProperties().removeIf( (p) -> ! (p.getBody() instanceof SafetyProp) );
			String proppath = cwd + "/" +modelName  +".prop";
			SerializationUtil.serializePropertiesForITSTools(outpath, spec.getProperties(), proppath);
			cl.addArg("-reachable-file");
			cl.addArg(proppath);
			// cl.addArg("--stats");
		} else if (tool == Tool.ctl) {
			spec.getProperties().removeIf( (p) -> ! (p.getBody() instanceof CTLProp) );
			String proppath = cwd + "/" +modelName  +".ctl";
			SerializationUtil.serializePropertiesForITSCTLTools(outpath, spec.getProperties(), proppath);
			cl.addArg("-ctl");
			cl.addArg(proppath);
		} else if (tool == Tool.ltl) {
			spec.getProperties().removeIf( (p) -> ! (p.getBody() instanceof LTLProp) );
			String proppath = cwd + "/" +modelName  +".ltl";
			SerializationUtil.serializePropertiesForITSLTLTools(outpath, spec.getProperties(), proppath);
			cl.addArg("-LTL");
			cl.addArg(proppath);	
			cl.addArg("-c");
			cl.addArg("-stutter-deadlock");
		}
		if (cl != null) {
			cl.setWorkingDir(new File(cwd));
		}
		
		return cl;

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
	
	private CommandLine buildCommandLine(String modelff, Tool tool) throws IOException {
		CommandLineBuilder cl = new CommandLineBuilder(tool);
		cl.setModelFile(modelff);
		cl.setModelType("CGAL");
		return cl.getCommandLine();
	}



	public void stop() {
		exec.shutdown();
		if (!exec.isShutdown())
			System.out.println("Problem to shutdown executor of runners");
	}
		
}
