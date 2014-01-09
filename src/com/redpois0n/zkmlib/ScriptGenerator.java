package com.redpois0n.zkmlib;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;

public class ScriptGenerator {
	
	private File[] classpath;
	private Configuration config;
	
	public ScriptGenerator(Configuration config) {
		this.config = config;
	}
	
	/**
	 * Generates the script file as string
	 * @return
	 * @throws Exception
	 */
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
		pw.println("open\t\"" + config.getInput().getAbsolutePath() + "\";");
		pw.println();
		
		/**
		 * trimExclude main class
		 */
		pw.println("trimExclude\t" + config.getMainClass() + "^ public static main(java.lang.String[]);");
		pw.println();
		
		
		/**
		 * obfuscate
		 */
		pw.println("obfuscate\tchangeLogFileIn=\"\"");
		pw.println("\tchangeLogFileOut=\"\"");
		pw.println("\tobfuscateFlow=" + config.getObfuscateFlow().toString());
		pw.println("\texceptionObfuscation=" + config.getExceptionObfuscation().toString());
		pw.println("\tencryptStringLiterals=" + config.getEncryptStringLiterals().toString());
		pw.println("\trandomize=true");
		if (config.isCollapsePackages()) {
			pw.println("\tcollapsePackagesWithDefault=\"" + config.getPackageName() + "\"");
		}
		pw.println("\tlocalVariables=delete");
		pw.println("\tlineNumbers=delete");
		pw.println("\tautoReflectionHandling=normal;");
		
		pw.println();

		/**
		 * saveAll
		 */
		pw.println("saveAll\tarchiveCompression=all \"" + config.getOutput().getAbsolutePath() + "\";");
		
		pw.close();
		
		return sw.toString();
	}

	public File[] getClasspath() {
		return classpath;
	}

	public void setClasspath(File[] classpath) {
		this.classpath = classpath;
	}


}
