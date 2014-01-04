package com.redpois0n.zkmlib;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.jar.JarFile;

import com.redpois0n.zkmlib.types.EncryptStringLiterals;
import com.redpois0n.zkmlib.types.ExceptionObfuscation;
import com.redpois0n.zkmlib.types.ObfuscateFlow;

public class Main {

	public static final String JAVA_HOME;

	public static File zkmJar;

	public static boolean printOutput;

	static {
		if (System.getProperty("os.name").toLowerCase().contains("win")) {
			JAVA_HOME = System.getProperty("java.home") + "\\bin\\javaw.exe";
		} else {
			JAVA_HOME = System.getProperty("java.home") + "/bin/java";
		}
	}

	/**
	 * Main entry point
	 * 
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

		printOutput = argsContains("-debug", args);

		String obfuscateFlow = "aggressive";

		if (argsContains("-obfuscateflow", args)) {
			obfuscateFlow = getArg("-obfuscateflow", args);
		}

		String mainClass;

		if (argsContains("-mainclass", args)) {
			mainClass = getArg("-mainclass", args);
		} else {
			mainClass = JarUtils.getMainClass(new JarFile(input));
		}

		String exceptionObfuscation = "heavy";

		if (argsContains("-exceptionobfuscation", args)) {
			exceptionObfuscation = getArg("-exceptionobfuscation", args);
		}
		
		String encryptStringLiterals = "flow_obfuscate";

		if (argsContains("-encryptstrings", args)) {
			encryptStringLiterals = getArg("-encryptstrings", args);
		}
		
		String collapsePackages = null;
		
		if (argsContains("-collapse", args)) {
			collapsePackages = getArg("-collapse", args);
		}
		
		Configuration config = new Configuration(input, output, mainClass, ObfuscateFlow.valueOf(obfuscateFlow.toUpperCase()), ExceptionObfuscation.valueOf(exceptionObfuscation.toUpperCase()), EncryptStringLiterals.valueOf(encryptStringLiterals.toUpperCase()), collapsePackages != null, collapsePackages);

		Obfuscator obf = new Obfuscator(config);

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
