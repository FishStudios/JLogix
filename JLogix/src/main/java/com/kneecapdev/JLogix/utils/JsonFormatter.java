package com.kneecapdev.JLogix.utils;

import java.util.Iterator;
import java.util.Map.Entry;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JsonFormatter{

    public static String format(final JSONObject object) {
        final JsonVisitor visitor = new JsonVisitor(4, ' ');
        visitor.visit(object, 0);
        return visitor.toString();
    }
    
    public static String format(final JSONArray array) {
        final JsonVisitor visitor = new JsonVisitor(4, ' ');
        visitor.visit(array, 0);
        return visitor.toString();
    }

    private static class JsonVisitor{

        private final StringBuilder builder = new StringBuilder();
        private final int indentationSize;
        private final char indentationChar;

        public JsonVisitor(final int indentationSize, final char indentationChar){
            this.indentationSize = indentationSize;
            this.indentationChar = indentationChar;
        }

        private void visit(final JSONArray array, final int indent){
            final int length = array.size();
            if(length == 0){
                write("[]", indent);
            } else{
                write("[", indent);
                for(int i = 0; i < length; i++){
                    visit(array.get(i), indent + 1, false);
                }
                write("]", indent);
            }

        }

        private void visit(final JSONObject obj, final int indent) {
            final int length = obj.size();
            if(length == 0){
                write("{}", indent);
            } else{
                write("{", indent);
                @SuppressWarnings("unchecked")
				Iterator<Entry<?, ?>> iter = obj.entrySet().iterator();
                
                while(iter.hasNext()) {
                	Entry<?, ?> e = iter.next();
                	
                	final String key = (String) e.getKey();
                    write(key + " :", indent + 1);
                    visit(e.getValue(), indent + 1, !iter.hasNext());
                    /*if(iter.hasNext()){
                        write(",", indent + 1);
                    }*/
                }
                write("}", indent);
            }

        }

        private void visit(final Object object, final int indent, boolean last) {
            if(object instanceof JSONArray){
                visit((JSONArray) object, indent);
            } else if(object instanceof JSONObject){
                visit((JSONObject) object, indent);
            } else{
                if(object instanceof String){
                    write("\"" + (String) object + "\"" + (last ? "" : ","), indent);
                } else{
                    write(String.valueOf(object) + (last ? "" : ","), indent);
                }
            }

        }

        private void write(final String data, final int indent){
            for(int i = 0; i < (indent * indentationSize); i++){
                builder.append(indentationChar);
            }
            builder.append(data).append('\n');
        }

        @Override
        public String toString(){
            return builder.toString();
        }

    }

}