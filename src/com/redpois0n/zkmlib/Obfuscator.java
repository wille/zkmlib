package com.redpois0n.zkmlib;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Obfuscator {

	private File input;
	private File output;
	private String mainClass;
	private List<UpdateListener> listeners = new ArrayList<UpdateListener>();


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

		if (output.exists()) {
			output.delete();
		}
		
		JavaProcess jp = new JavaProcess(Runtime.getRuntime().exec(new String[] { Main.JAVA_HOME, "-jar", Main.getZKMJar().getAbsolutePath(), scriptFile.getAbsolutePath() }));

		for (UpdateListener listener : listeners) {
			jp.addListener(listener);
		}

		jp.waitFor();

		scriptFile.delete();

	}

	public void addListener(UpdateListener listener) {
		listeners.add(listener);
	}

	public void removeListener(UpdateListener listener) {
		listeners.remove(listener);
	}
}
