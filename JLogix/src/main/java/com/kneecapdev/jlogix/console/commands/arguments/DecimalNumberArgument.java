package com.kneecapdev.jlogix.console.commands.arguments;

import com.google.common.primitives.Floats;

public class DecimalNumberArgument extends CommandArgument<Float> {

	public DecimalNumberArgument(String arg) {
		super(arg);
	}

	@Override
	public boolean isArgumentValid() {
		return Floats.tryParse(getArgument()) != null;
	}

	@Override
	public Float parseArgument() {
		return Floats.tryParse(getArgument());
	}

	@Override
	public String getDisplayName() {
		return "decimal number";
	}

}
