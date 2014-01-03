package com.redpois0n.zkmlib.types;

public enum ExceptionObfuscation {
	
	NONE("none"), LIGHT("light"), HEAVY("heavy");
	
	private String s;
	
	private ExceptionObfuscation(String s) {
		this.s = s;
	}
	
	public String toString() {
		return this.s;
	}

}
