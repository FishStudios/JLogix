package com.kneecapdev.jlogix.console.commands.arguments;

public abstract class CommandArgument<T> {

	private String arg;
	private T value;
	
	public CommandArgument(String arg) {
		this.arg = arg;
		if(this.isArgumentValid()) this.value = this.parseArgument();
	}
	
	public String getArgument() {
		return arg;
	}

	public T getValue() {
		return value;
	}

	public abstract boolean isArgumentValid();
	public abstract T parseArgument();

	public abstract String getDisplayName();
	
}
