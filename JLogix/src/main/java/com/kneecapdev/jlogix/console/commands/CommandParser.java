package com.kneecapdev.jlogix.console.commands;

import java.lang.reflect.InvocationTargetException;

import com.kneecapdev.jlogix.api.log.LogixLogger;
import com.kneecapdev.jlogix.console.commands.arguments.CommandArgument;

public class CommandParser {

	public void parse(String input) {
		String[] args = input.split(" ");
		WrappedCommand cmd = CommandRegistry.getInstance().getCommand(args[0]);
		
		if(cmd == null) {
			LogixLogger.info(this, "Unkown command! ('" + args[0] + "')");
			return;
		}
		
		if(args.length - 1 != cmd.getArguments().length) {
			LogixLogger.info(this, "Wrong arguments! Usage: " + cmd.getCmdInfo().usage());
			return;
		}
		
		CommandArgument<?>[] arguments = new CommandArgument[args.length - 1];
		for(int i = 1; i < args.length; i++) {
			try {
				CommandArgument<?> arg = cmd.getArguments()[i - 1].getConstructor(String.class).newInstance(args[i]);
				if(arg.getValue() == null) {
					LogixLogger.info(this, "Unable to execute command '" + input + "'" + i + ". argument needs to be '" + arg.getDisplayName() + "'!");
					return;
				}
				arguments[i - 1] = arg;
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
		}
		
		cmd.invoke(arguments);
	}
	
}
