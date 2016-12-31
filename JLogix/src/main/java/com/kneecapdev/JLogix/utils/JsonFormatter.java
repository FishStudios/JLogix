package com.kneecapdev.JLogix.utils;

import java.util.Iterator;
import java.util.Map.Entry;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JsonFormatter{

    public static String format(final JSONArray array) {
        final JsonVisitor visitor = new JsonVisitor(1, "\t");
        visitor.visit(array, false);
        return visitor.toString();
    }

    private static class JsonVisitor{

        private final StringBuilder builder = new StringBuilder();
        private final int indentationSize;
        private final String indentationChar;
        private int spacing;

        public JsonVisitor(final int indentationSize, final String indentationChar){
            this.indentationSize = indentationSize;
            this.indentationChar = indentationChar;
        }

        public void visit(JSONArray array, boolean value) {
        	if(!value) builder.append(buildSpacer(spacing) + "[");
        	else append("[");
        	@SuppressWarnings("unchecked")
			Iterator<Object> iter = array.iterator();
        	boolean first = true;
        	while(iter.hasNext()) {
        		Object obj = iter.next();
        		if(obj instanceof JSONObject) visit((JSONObject) obj, first, !iter.hasNext());
        		else if(obj instanceof JSONArray) visit((JSONArray) obj, false);
        		
        		first = false;
        	}
        	builder.append("]");
        }
        
        public void visit(JSONObject object, boolean first, boolean last) {
        	@SuppressWarnings("unchecked")
			Iterator<Entry<?, ?>> iter = object.entrySet().iterator();
        	
        	if(first) append("{");
        	else append("\n" + buildSpacer(spacing) + "{");
        		
        	boolean firstIter = true;
        	
        	while(iter.hasNext()) {
        		Entry<?, ?> entry = iter.next();
        		
        		if(firstIter) {
        			spacing++;
        			firstIter = false;
        		}
        		
        		append("\n" + buildSpacer(spacing) + "\"" + (String) entry.getKey() + "\": ");
        		
        		if(entry.getValue() instanceof JSONArray) visit((JSONArray) entry.getValue(), true);
        		else if(entry.getValue() instanceof JSONObject) visit((JSONObject) entry.getValue(), true, false);
        		else if(entry.getValue() instanceof String) append("\"" + (String) entry.getValue() + "\"" + (iter.hasNext() ? "," : ""));
        		else append(entry.getValue() + (iter.hasNext() ? "," : ""));
        	}
        	
        	spacing--;
        	
        	if(last) append("\n" + buildSpacer(spacing) + "}");
        	else append("\n" + buildSpacer(spacing) + "},");
        }

        private String buildSpacer(int indent) {
        	StringBuilder sb = new StringBuilder();
        	for(int i = 0; i < (indent * indentationSize); i++){
        		sb.append(indentationChar);
            }
        	return sb.toString();
        }
        
        private void append(String data) {
        	builder.append(data);
        }
        
        @Override
        public String toString(){
            return builder.toString();
        }

    }

}