package com.redpois0n.zkmlib;

import java.io.File;

import com.redpois0n.zkmlib.types.EncryptStringLiterals;
import com.redpois0n.zkmlib.types.ExceptionObfuscation;
import com.redpois0n.zkmlib.types.ObfuscateFlow;

public class Configuration {
	
	private File input;
	private File output;
	
	private ObfuscateFlow obfuscateFlow;
	
	private ExceptionObfuscation exceptionObfuscation;
	
	private EncryptStringLiterals encryptStringLiterals;
	
	private boolean collapsePackages;
	private String packageName;
	
	

}
