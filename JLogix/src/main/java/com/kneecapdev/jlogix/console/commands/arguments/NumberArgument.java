package com.kneecapdev.jlogix.console.commands.arguments;

import com.google.common.primitives.Ints;

public class NumberArgument extends CommandArgument<Integer> {

	public NumberArgument(String arg) {
		super(arg);
	}

	@Override
	public boolean isArgumentValid() {
		return Ints.tryParse(getArgument()) != null;
	}

	@Override
	public Integer parseArgument() {
		return Ints.tryParse(getArgument());
	}

	@Override
	public String getDisplayName() {
		return "number";
	}

}
