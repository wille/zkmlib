package com.redpois0n.zkmlib;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;

public class ScriptGenerator {
	
	private File[] classpath;
	private File input;
	private File output;
	private String mainClass;
	
	public ScriptGenerator(File input, File output, String mainClass) {
		this.input = input;
		this.output = output;
		this.mainClass = mainClass;
	}
	
	public String generate() throws Exception {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		
		/**
		 * Generate classpath
		 */
		String[] classAbsolutePaths = System.getProperty("java.class.path").split(";");
		File[] javaClassPath = new File[classAbsolutePaths.length];
		
		for (int i = 0; i < javaClassPath.length; i++) {
			javaClassPath[i] = new File(classAbsolutePaths[i]);
		}
		
		pw.print("classpath\t");
		
		for (File file : javaClassPath) {
			pw.println("\"" + file.getAbsolutePath() + "\"");
			pw.print("\t");
		}
		
		pw.println(";");
		
		/**
		 * Write input file
		 */
		pw.println("open\t\"" + input.getAbsolutePath() + "\";");
		pw.println();
		
		/**
		 * trimExclude main class
		 */
		pw.println("trimExclude\t" + mainClass + "^ public static main(java.lang.String[]);");
		pw.println();
		
		
		/**
		 * obfuscate
		 */
		pw.println("obfuscate\tchangeLogFileIn=\"\"");
		pw.println("\tchangeLogFileOut=\"\"");
		pw.println("\tobfuscateFlow=aggressive");
		pw.println("\texceptionObfuscation=heavy");
		pw.println("\tencryptStringLiterals=flowObfuscate");
		pw.println("\trandomize=true");
		pw.println("\tcollapsePackagesWithDefault=\"defaultpackage\"");
		pw.println("\tlocalVariables=delete");
		pw.println("\tlineNumbers=delete");
		pw.println("\tautoReflectionHandling=normal;");
		
		pw.println();

		/**
		 * saveAll
		 */
		pw.println("saveAll\tarchiveCompression=all \"" + output.getAbsolutePath() + "\";");
		
		pw.close();
		
		return sw.toString();
	}

	public File[] getClasspath() {
		return classpath;
	}

	public void setClasspath(File[] classpath) {
		this.classpath = classpath;
	}

	public File getInput() {
		return input;
	}

	public void setInput(File input) {
		this.input = input;
	}

	public String getMainClass() {
		return mainClass;
	}

	public void setMainClass(String mainClass) {
		this.mainClass = mainClass;
	}

	public File getOutput() {
		return output;
	}

	public void setOutput(File output) {
		this.output = output;
	}

}
