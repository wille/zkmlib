package com.redpois0n.zkmlib;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JavaProcess {
	
	private List<UpdateListener> listeners = new ArrayList<UpdateListener>();
	private Process process;
	
	public JavaProcess(File file) throws Exception {
		this(new String[] { file.getAbsolutePath() } );
	}
	
	public JavaProcess(String[] args) throws Exception {
		this(Runtime.getRuntime().exec(args));
	}
	
	public JavaProcess(String s) throws Exception {
		this(Runtime.getRuntime().exec(s));
	}
	
	/**
	 * Execute process with readers for input and error stream
	 * @param p
	 * @throws Exception
	 */
	public JavaProcess(final Process p) throws Exception {
		this.process = p;
		
		new Thread(new Reader(p.getInputStream())).start();
		new Thread(new Reader(p.getErrorStream())).start();
	}
	
	public void addListener(UpdateListener listener) {
		listeners.add(listener);
	}
	
	public void removeListener(UpdateListener listener) {
		listeners.remove(listener);
	}
	
	public void waitFor() {
		try {
			process.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	class Reader implements Runnable {
		
		private InputStream input;
		
		public Reader(InputStream input) {
			this.input = input;
		}
		
		@Override
		public void run() {
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(input));
				
				String line;
				
				while ((line = reader.readLine()) != null) {
					if (Main.printOutput) {
						System.out.println(line);
					}
					
					for (UpdateListener listener : listeners) {
						listener.onInput(line);
					}
				}
				
				reader.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

}
