package com.kneecapdev.jlogix.console.commands.arguments;

public class TextArgument extends CommandArgument<String> {

	public TextArgument(String arg) {
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
		return "text";
	}

}
