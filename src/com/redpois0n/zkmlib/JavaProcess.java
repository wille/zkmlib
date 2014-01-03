package com.redpois0n.zkmlib;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JavaProcess {
	
	private List<UpdateListener> listeners = new ArrayList<UpdateListener>();
	
	public JavaProcess(File file) throws Exception {
		this(new String[] { file.getAbsolutePath() } );
	}
	
	public JavaProcess(String[] args) throws Exception {
		this(Runtime.getRuntime().exec(args));
	}
	
	public JavaProcess(String s) throws Exception {
		this(Runtime.getRuntime().exec(s));
	}
	
	public JavaProcess(final Process p) throws Exception {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
					
					String line;
					
					while ((line = reader.readLine()) != null) {
						System.out.println(line);
						
						for (UpdateListener listener : listeners) {
							listener.onInput(line);
						}
					}
					
					reader.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					BufferedReader reader = new BufferedReader(new InputStreamReader(p.getErrorStream()));
					
					String line;
					
					while ((line = reader.readLine()) != null) {
						System.out.println(line);
						
						for (UpdateListener listener : listeners) {
							listener.onInput(line);
						}
					}
					
					reader.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}).start();
	}
	
	public void addListener(UpdateListener listener) {
		listeners.add(listener);
	}
	
	public void removeListener(UpdateListener listener) {
		listeners.remove(listener);
	}

}
