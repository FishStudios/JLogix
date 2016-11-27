package com.kneecapdav.JLogix.API.element;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ElementInfo {

	String moduleID();
	String elementID();
	String description() default "";
	
}
