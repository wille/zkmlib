package com.redpois0n.zkmlib;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.jar.JarFile;

public class Main {
	
	public static final String JAVA_HOME;
	
	private static File zkmJar;
	
	static {
		if (System.getProperty("os.name").toLowerCase().contains("win")) {
			JAVA_HOME = System.getProperty("java.home") + "\\bin\\javaw.exe";
		} else {
			JAVA_HOME = System.getProperty("java.home") + "/bin/java";
		}
	}
	
	/**
	 * Main entry point
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		if (argsContains("-zkmjar", args)) {
			zkmJar = new File(getArg("-zkmjar", args));
		}
		
		File input;
		
		if (argsContains("-input", args)) {
			input = new File(getArg("-input", args));
		} else {
			throw new FileNotFoundException("No -input specified");
		}
				
		File output;
		
		if (argsContains("-output", args)) {
			output = new File(getArg("-output", args));
		} else {
			output = new File(input.getParentFile(), "obf_" + input.getName());
		}
		
		Obfuscator obf = new Obfuscator(input, output, JarUtils.getMainClass(new JarFile(input)));
		
		obf.obfuscate();
	}
	
	public static String getArg(String arg, String[] args) {
		for (int i = 0; i < args.length; i++) {
			if (args[i].equalsIgnoreCase(arg)) {
				return args[i + 1];
			}
		}
		
		return null;
	}
	
	public static boolean argsContains(String arg, String[] args) {
		for (String s : args) {
			if (arg.equalsIgnoreCase(s)) {
				return true;
			}
		}
		
		return false;
	}
	
	public static File getZKMJar() {
		if (zkmJar == null) {
			return new File("ZKM.jar");
		} else {
			return zkmJar;
		}
	}

}
