package com.redpois0n.zkmlib;

import java.io.File;

public class ScriptGenerator {
	
	private File[] classpath;
	
	public ScriptGenerator() {
		
	}
	
	public String generate() throws Exception {
		String[] classAbsolutePaths = System.getProperty("java.class.path").split(";");
		File[] javaClassPath = new File[classAbsolutePaths.length];
		
		for (int i = 0; i < javaClassPath.length; i++) {
			javaClassPath[i] = new File(classAbsolutePaths[i]);
		}
	}

	public File[] getClasspath() {
		return classpath;
	}

	public void setClasspath(File[] classpath) {
		this.classpath = classpath;
	}

}
