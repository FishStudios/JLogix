package com.kneecapdev.jlogix.console.commands.arguments;

public class StringArgument extends CommandArgument<String> {

	public StringArgument(String arg) {
		super(arg);
	}

	@Override
	public boolean isArgumentValid() {
		return true;
	}

	@Override
	public String parseArgument() {
		return getArgument();
	}

	@Override
	public String getDisplayName() {
		return "string";
	}

}
