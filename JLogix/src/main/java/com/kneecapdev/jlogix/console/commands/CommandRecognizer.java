package com.kneecapdev.jlogix.console.commands;

import java.lang.reflect.Method;
import java.util.ArrayList;

import com.kneecapdev.jlogix.api.log.LogixLogger;
import com.kneecapdev.jlogix.console.commands.arguments.CommandArgument;

public class CommandRecognizer {

	public ArrayList<WrappedCommand> recognize(Object obj) {
		ArrayList<WrappedCommand> result = new ArrayList<>();
		
		for(Method method: obj.getClass().getMethods()) {
			CommandInfo[] commandInfos = method.getAnnotationsByType(CommandInfo.class);
			
			if(commandInfos.length < 1) continue;
			
			CommandInfo commandInfo = commandInfos[0];
			
			Class<?>[] arguments = method.getParameterTypes();
			if(!validateParameters(arguments)) {
				LogixLogger.warn(this, "Unable to load command '" + commandInfo.cmd() + "'. Commands only accept arguments from the type 'CommandArgument'!");
				continue;
			}
			
			@SuppressWarnings("unchecked")
			WrappedCommand cmd = new WrappedCommand(obj, commandInfo, method, (Class<? extends CommandArgument<?>>[]) arguments);
			result.add(cmd);
		}
		
		return result;
	}
	
	public boolean validateParameters(Class<?>[] arguments) {
		for(Class<?> argument: arguments) {
			if(!CommandArgument.class.isAssignableFrom(argument)) return false;
		}
		return true;
	}
	
}
