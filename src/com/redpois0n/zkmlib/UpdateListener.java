package com.redpois0n.zkmlib;

public abstract interface UpdateListener {

	/**
	 * On input from any stream when executing ZKM
	 * @param s
	 */
	public abstract void onInput(String s);
}
