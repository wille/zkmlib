package com.redpois0n.zkmlib;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Obfuscator {

	private Configuration config;
	private List<UpdateListener> listeners = new ArrayList<UpdateListener>();


	public Obfuscator(Configuration config) {
		this.config = config;
	}

	public void obfuscate() throws Exception {
		ScriptGenerator sg = new ScriptGenerator(config);

		String script = sg.generate();

		File scriptFile = File.createTempFile("zkmlib", ".txt");
		FileOutputStream fos = new FileOutputStream(scriptFile);
		fos.write(script.getBytes("UTF-8"));
		fos.close();

		if (config.getOutput().exists()) {
			config.getOutput().delete();
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
