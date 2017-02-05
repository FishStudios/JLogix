package com.kneecapdev.jlogix.console.commands;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.kneecapdev.jlogix.console.commands.arguments.CommandArgument;

public class WrappedCommand {

	private Object reference;
	private CommandInfo cmdInfo;
	private Method method;
	private Class<? extends CommandArgument<?>>[] arguments;
	
	public WrappedCommand(Object reference, CommandInfo cmdInfo, Method method, Class<? extends CommandArgument<?>>[] arguments) {
		this.reference = reference;
		this.cmdInfo = cmdInfo;
		this.method = method;
		this.arguments = arguments;
	}
	
	public Object getReference() {
		return this.reference;
	}

	public CommandInfo getCmdInfo() {
		return cmdInfo;
	}

	public Method getMethod() {
		return method;
	}

	public Class<? extends CommandArgument<?>>[] getArguments() {
		return arguments;
	}
	
	public void invoke(CommandArgument<?>... args) {
		try {
			method.invoke(this.getReference(), (Object[]) args);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
}
