package com.redpois0n.zkmlib.types;

public enum EncryptStringLiterals {
	
	NONE("none"), NORMAL("normal"), AGGRESSIVE("aggressive"), FLOW_OBFUSCATE("flowObfuscate");
	
	private String s;
	
	private EncryptStringLiterals(String s) {
		this.s = s;
	}
	
	public String toString() {
		return this.s;
	}

}
