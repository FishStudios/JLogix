package com.kneecapdev.jlogix.api.events;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface EventHook {

	enum Priority {
		LOWEST(0), VERY_LOW(1), LOW(2), NORMAL(3), HIGH(4), VERY_HIGH(5), HIGHEST(5);
		
		public int weight;
		
		Priority(int weight) {
			this.weight = weight;
		}
	}
	
	Priority priority() default Priority.NORMAL;
	
}
