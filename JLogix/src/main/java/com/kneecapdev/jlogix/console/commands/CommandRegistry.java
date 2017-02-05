package com.kneecapdev.jlogix.console.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import com.kneecapdev.jlogix.api.log.LogixLogger;

public class CommandRegistry {

	private static CommandRegistry instance;
	
	private HashMap<Object, ArrayList<WrappedCommand>> commands = new HashMap<>();
	private CommandRecognizer recognizer;
	
	private CommandRegistry() {
		recognizer = new CommandRecognizer();
	}
	
	public CommandRecognizer getCommandRecognizer() {
		return this.recognizer;
	}
	
	public void register(Object obj, ArrayList<WrappedCommand> command) {
		ArrayList<WrappedCommand> validCommands = new ArrayList<>();
		for(WrappedCommand cmd: command) {
			if(getCommand(cmd.getCmdInfo().cmd()) != null) {
				LogixLogger.warn(this, "Unable to register command '" + cmd.getCmdInfo().cmd() + "' from '" + obj.getClass().getSimpleName() + "'. Theres already a registered command with this name!");
				continue;
			} else validCommands.add(cmd);
		}
		commands.put(obj, validCommands);
	}
	
	public void unregister(Object obj) {
		if(commands.containsKey(obj)) commands.remove(obj);
	}
	
	public void unregister(WrappedCommand cmd) {
		if(!commands.containsKey(cmd.getReference())) return;
		ArrayList<WrappedCommand> registeredCommands = commands.get(cmd.getReference());
		if(registeredCommands.contains(cmd)) registeredCommands.remove(cmd);
	}
	
	public WrappedCommand getCommand(String cmd) {
		for(Entry<Object, ArrayList<WrappedCommand>> e: commands.entrySet()) {
			for(WrappedCommand command: e.getValue()) {
				if(command.getCmdInfo().cmd().equalsIgnoreCase(cmd)) return command;
			}
		}
		return null;
	}
	
	public static CommandRegistry getInstance() {
		if(instance == null) instance = new CommandRegistry();
		return instance;
	}
	
}
