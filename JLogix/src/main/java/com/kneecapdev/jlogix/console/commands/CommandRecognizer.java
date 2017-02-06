package com.kneecapdev.jlogix.console.commands;

import java.lang.reflect.Method;
import java.util.ArrayList;

import com.kneecapdev.jlogix.api.log.LogixLogger;
import com.kneecapdev.jlogix.console.commands.arguments.CommandArgument;
import com.kneecapdev.jlogix.console.commands.arguments.TextArgument;

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
			
			if(!checkArgumentTypes(arguments)) {
				LogixLogger.warn(this, "Unable to load command '" + commandInfo.cmd() + "'. 'TextArgument' can only be used alone or as last argument!'");
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
	
	public boolean checkArgumentTypes(Class<?>[] arguments) {
		if(arguments.length > 1) {
			for(int i = 0; i < arguments.length - 1; i++) {
				if(TextArgument.class.isAssignableFrom(arguments[i])) return false;
			}
		}
		return true;
	}
	
}
