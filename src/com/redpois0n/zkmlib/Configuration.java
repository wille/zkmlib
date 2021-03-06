package com.redpois0n.zkmlib;

import java.io.File;

import com.redpois0n.zkmlib.types.EncryptStringLiterals;
import com.redpois0n.zkmlib.types.ExceptionObfuscation;
import com.redpois0n.zkmlib.types.ObfuscateFlow;

public class Configuration {

	/**
	 * Input file
	 */
	private File input;
	
	/**
	 * Obfuscated output
	 */
	private File output;

	/**
	 * Main class
	 */
	private String mainClass;
	
	/**
	 * Type of obfuscation flow
	 */
	private ObfuscateFlow obfuscateFlow;

	/**
	 * Type of exception obfuscation
	 */
	private ExceptionObfuscation exceptionObfuscation;

	/**
	 * Type of string encryption
	 */
	private EncryptStringLiterals encryptStringLiterals;

	/**
	 * Should collapse packages
	 */
	private boolean collapsePackages;
	
	public Configuration(File input, File output, String mainClass, ObfuscateFlow obfuscateFlow, ExceptionObfuscation exceptionObfuscation, EncryptStringLiterals encryptStringLiterals, boolean collapsePackages, String packageName) {
		this.input = input;
		this.output = output;
		this.mainClass = mainClass;
		this.obfuscateFlow = obfuscateFlow;
		this.exceptionObfuscation = exceptionObfuscation;
		this.encryptStringLiterals = encryptStringLiterals;
		this.collapsePackages = collapsePackages;
		this.packageName = packageName;
	}

	private String packageName;

	public File getInput() {
		return input;
	}

	public void setInput(File input) {
		this.input = input;
	}

	public File getOutput() {
		return output;
	}

	public void setOutput(File output) {
		this.output = output;
	}

	public ObfuscateFlow getObfuscateFlow() {
		return obfuscateFlow;
	}

	public void setObfuscateFlow(ObfuscateFlow obfuscateFlow) {
		this.obfuscateFlow = obfuscateFlow;
	}

	public ExceptionObfuscation getExceptionObfuscation() {
		return exceptionObfuscation;
	}

	public void setExceptionObfuscation(ExceptionObfuscation exceptionObfuscation) {
		this.exceptionObfuscation = exceptionObfuscation;
	}

	public EncryptStringLiterals getEncryptStringLiterals() {
		return encryptStringLiterals;
	}

	public void setEncryptStringLiterals(EncryptStringLiterals encryptStringLiterals) {
		this.encryptStringLiterals = encryptStringLiterals;
	}

	public boolean isCollapsePackages() {
		return collapsePackages;
	}

	public void setCollapsePackages(boolean collapsePackages) {
		this.collapsePackages = collapsePackages;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getMainClass() {
		return mainClass;
	}

	public void setMainClass(String mainClass) {
		this.mainClass = mainClass;
	}

}
