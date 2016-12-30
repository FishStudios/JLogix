package com.kneecapdev.JLogix.API.module;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ModuleInfo {

	/**
	 * Module name
	 */
	String moduleID();
	/**
	 * Module version
	 */
	String version() default "";
	/**
	 * Module author
	 */
	String author() default "";
	/**
	 * Module description
	 */
	String description() default "";
	
}
