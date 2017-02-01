package com.kneecapdev.jlogix.api.meta;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class MetaBindings {

	public static void bind(BooleanProperty property, MetaValue<Boolean> meta) {
		BidirectionalBinding.bindBidirectional(property, meta,
				((observable,  oldValue, newValue) -> meta.setValue(newValue)),
				((observable,  oldValue, newValue) -> property.set(newValue)));
	}
	
	public static void bind(IntegerProperty property, MetaValue<Integer> meta) {
		BidirectionalBinding.bindBidirectional(property, meta,
				((observable,  oldValue, newValue) -> meta.setValue(newValue.intValue())),
				((observable,  oldValue, newValue) -> property.set(newValue)));
	}
	
	public static void bind(LongProperty property, MetaValue<Long> meta) {
		BidirectionalBinding.bindBidirectional(property, meta,
				((observable,  oldValue, newValue) -> meta.setValue(newValue.longValue())),
				((observable,  oldValue, newValue) -> property.set(newValue)));
	}
	
	public static void bind(FloatProperty property, MetaValue<Float> meta) {
		BidirectionalBinding.bindBidirectional(property, meta,
				((observable,  oldValue, newValue) -> meta.setValue(newValue.floatValue())),
				((observable,  oldValue, newValue) -> property.set(newValue)));
	}
	
	public static void bind(DoubleProperty property, MetaValue<Double> meta) {
		BidirectionalBinding.bindBidirectional(property, meta,
				((observable,  oldValue, newValue) -> meta.setValue(newValue.doubleValue())),
				((observable,  oldValue, newValue) -> property.set(newValue)));
	}
	
	public static void bind(StringProperty property, MetaValue<String> meta) {
		BidirectionalBinding.bindBidirectional(property, meta,
				((observable,  oldValue, newValue) -> meta.setValue(newValue)),
				((observable,  oldValue, newValue) -> property.set(newValue)));
	}
	
	public static class BidirectionalBinding {

	    /** Executes updateB when propertyA is changed. Executes updateA when propertyB is changed.
	     * Makes sure that no update loops are caused by mutual updates.
	     */
	    public static <A,B> void bindBidirectional(ObservableValue<A> propertyA, ObservableValue<B> propertyB, ChangeListener<A> updateB, ChangeListener<B> updateA){
	        addFlaggedChangeListener(propertyA, updateB);
	        addFlaggedChangeListener(propertyB, updateA);
	    }

	    /**
	     * Adds a change listener to a property that will not react to changes caused (transitively) by itself (i.e. from an update call in the call tree that is a descendant of itself.)
	     * @param property the property to add a change listener to
	     * @param updateProperty the logic to execute when the property changes
	     * @param <T> the type of the observable value
	     */
	    private static <T> void addFlaggedChangeListener(ObservableValue<T> property, ChangeListener<T> updateProperty){
	        property.addListener(new ChangeListener<T>() {

	        private boolean alreadyCalled = false;
	        
	        @Override public void changed(ObservableValue<? extends T> observable, T oldValue, T newValue) {
	            if(alreadyCalled) return;
	            try {
	                alreadyCalled = true;
	                updateProperty.changed(observable,oldValue,newValue);
	            }
	            finally { alreadyCalled = false; }
	        }
	        });
	    }

	}
	
}
