package com.kneecapdav.JLogix.API.module;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ModuleInfo {

	String moduleID();
	String version() default "";
	String author() default "";
	String description() default "";
	
}
