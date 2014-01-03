package com.redpois0n.zkmlib.types;

public enum ObfuscateFlow {

	NONE("none"), LIGHT("light"), NORMAL("normal"), AGGRESSIVE("aggressive");

	private String s;

	private ObfuscateFlow(String s) {
		this.s = s;
	}

	public String toString() {
		return this.s;
	}

}
