package com.kneecapdev.jlogix.api.log;

import java.io.PrintStream;

import com.kneecapdev.jlogix.console.LogixConsole;

public class SystemOutHook {

	public static void init() {
		
		System.setOut(new PrintStream(System.out) {
			
	        @Override
	        public void println(String s) {
	        	LogixConsole.getInstance().append(s);
	            super.println(s);
	        }
	        
	    });
		
	}
	
}
