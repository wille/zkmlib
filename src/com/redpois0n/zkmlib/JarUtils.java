package com.redpois0n.zkmlib;

import java.util.Map;
import java.util.jar.JarFile;

import javax.swing.JOptionPane;

public class JarUtils {

	/**
	 * Gets main class from jar manifest
	 * @param jar
	 * @return
	 */
	public static String getMainClass(JarFile jar) {
		try {			
			Map<Object, Object> map = jar.getManifest().getMainAttributes();
			
			String mainClass = null;
			
			for (Object obj : map.keySet()) {
				if (obj.toString().equalsIgnoreCase("main-class")) {
					mainClass = map.get(obj).toString();
					break;
				}
			}
			
			
			return mainClass;
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Failed loading manifest: " + ex.getClass().getSimpleName() + ": " + ex.getMessage(), "jCrypt", JOptionPane.ERROR_MESSAGE);
		}
		
		return null;
	}
}
