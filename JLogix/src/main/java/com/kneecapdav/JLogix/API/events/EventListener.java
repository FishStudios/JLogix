package com.kneecapdav.JLogix.API.events;

/**
 * This class only exist to determine if the ModuleLoader should register the class in the Guava EventBus or not.
 * 
 * When your class implements this class it would get automatically loaded in the EventBus.
 * 
 * @author Dominik
 *
 */
public interface EventListener {}
