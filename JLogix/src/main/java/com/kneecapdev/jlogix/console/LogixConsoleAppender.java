package com.kneecapdev.jlogix.console;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

public class LogixConsoleAppender extends AppenderSkeleton {

	@Override
	public void close() {
	}

	@Override
	public boolean requiresLayout() {
		return true;
	}

	@Override
	protected void append(LoggingEvent e) {
		LogixConsole.getInstance().append(this.getLayout().format(e));
	}

}
