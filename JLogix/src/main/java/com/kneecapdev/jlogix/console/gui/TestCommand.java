package com.kneecapdev.jlogix.console.gui;

import com.kneecapdev.jlogix.console.commands.CommandInfo;
import com.kneecapdev.jlogix.console.commands.arguments.BooleanArgument;
import com.kneecapdev.jlogix.console.commands.arguments.DecimalNumberArgument;
import com.kneecapdev.jlogix.console.commands.arguments.NumberArgument;
import com.kneecapdev.jlogix.console.commands.arguments.StringArgument;

public class TestCommand {

	@CommandInfo(cmd = "yolo", usage = "yolo <number> <decimal> <string> <boolean>")
	public void yoloCommand(NumberArgument nArg, DecimalNumberArgument dArg, StringArgument sArg, BooleanArgument bArg) {
		//Do some yolo in here
	}
	
}
