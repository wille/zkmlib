package com.redpois0n.zkmlib;

import java.io.File;
import java.io.FileOutputStream;

public class Obfuscator {
	
	private File input;
	private File output;
	private String mainClass;
	
	public Obfuscator(File input, File output, String mainClass) {
		this.input = input;
		this.output = output;
		this.mainClass = mainClass;
	}
	
	public void obfuscate() throws Exception {		
		ScriptGenerator sg = new ScriptGenerator(input, output, mainClass);
		
		String script = sg.generate();
		
		File scriptFile = File.createTempFile("zkmlib", ".txt");
		FileOutputStream fos = new FileOutputStream(scriptFile);
		fos.write(script.getBytes("UTF-8"));
		fos.close();
		
		JavaProcess jp = new JavaProcess(Runtime.getRuntime().exec(new String[] { Main.JAVA_HOME, "-jar", Main.getZKMJar().getAbsolutePath(), scriptFile.getAbsolutePath() }));
		
	
	
	
	
	
	}
}
