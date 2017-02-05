package com.kneecapdev.jlogix.console.commands.arguments;

public class BooleanArgument extends CommandArgument<Boolean> {

	public BooleanArgument(String arg) {
		super(arg);
	}

	@Override
	public boolean isArgumentValid() {
		return getArgument().equalsIgnoreCase("true") || getArgument().equalsIgnoreCase("false");
	}

	@Override
	public Boolean parseArgument() {
		return Boolean.parseBoolean(getArgument());
	}

	public String getDisplayName() {
		return "boolean";
	}

}
