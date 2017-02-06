package com.kneecapdev.jlogix.console.commands;

import java.lang.reflect.InvocationTargetException;

import com.kneecapdev.jlogix.api.log.LogixLogger;
import com.kneecapdev.jlogix.console.commands.arguments.CommandArgument;
import com.kneecapdev.jlogix.console.commands.arguments.TextArgument;

public class CommandParser {

	private static CommandParser instance;
	
	private CommandParser() {}
	
	public void parse(String input) {
		String[] args = input.split(" ");
		WrappedCommand cmd = CommandRegistry.getInstance().getCommand(args[0]);
		
		if(cmd == null) {
			LogixLogger.info(this, "Unkown command! ('" + args[0] + "')");
			return;
		}
		
		if(args.length - 1 < cmd.getArguments().length) {
			LogixLogger.info(this, "Wrong arguments! Usage: " + cmd.getCmdInfo().usage());
			return;
		}
		
		CommandArgument<?>[] arguments = new CommandArgument[cmd.getArguments().length];
		for(int i = 0; i < cmd.getArguments().length; i++) {
			try {
				String sArg = args[i + 1];
				if(TextArgument.class.isAssignableFrom(cmd.getArguments()[i])) {
					StringBuilder sb = new StringBuilder();
					for(int ii = i + 1; ii < args.length; ii++) {
						if(ii == args.length) sb.append(args[ii]);
						else sb.append(args[ii] + " ");
					}
					sArg = sb.toString();
				}
				CommandArgument<?> arg = cmd.getArguments()[i].getConstructor(String.class).newInstance(sArg);
				if(arg.getValue() == null) {
					LogixLogger.info(this, "Unable to execute command '" + input + "' " + (i + 1) + ". argument needs to be a '" + arg.getDisplayName() + "'!");
					return;
				}
				arguments[i] = arg;
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
		}
		
		cmd.invoke(arguments);
	}
	
	public static CommandParser getInstance() {
		if(instance == null) instance = new CommandParser();
		return instance;
	}
	
}
