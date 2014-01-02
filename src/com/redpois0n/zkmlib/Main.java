package com.redpois0n.zkmlib;

public class Main {
	
	/**
	 * Main entry point
	 * @param args
	 */
	public static void main(String[] args) {
		
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

}
