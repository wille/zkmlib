package com.redpois0n.zkmlib;

import java.io.File;

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
	public static void main(String[] args) {
		if (argsContains("-zkmjar", args)) {
			zkmJar = new File(getArg("-zkmjar", args));
		}
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
