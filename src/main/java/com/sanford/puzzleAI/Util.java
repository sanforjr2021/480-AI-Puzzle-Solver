package com.sanford.puzzleAI;

import com.sanford.puzzleAI.AI.Action;

public class Util {
    public static Boolean debug = false;
    /**
     * Converts a String to a list of integers.
     * @param stringOfInts
     * @return  Integer[]
     */
    public static Integer[] convertStringToIntegers(String stringOfInts){
        Integer[] list = new Integer[stringOfInts.length()];
        for(int i = 0; i < stringOfInts.length(); i++){
            try{
                list[i] =  Integer.parseInt(String.valueOf(stringOfInts.charAt(i)));
            } catch (NumberFormatException e) {
                System.err.println("Value " + list[i] + " is not a number(Util.java:16)");
            }
        }
        return  list;
    }

    /**
     * Converts an Array of Integers to a String
     * @param ints
     * @return String
     */
    public static String convertIntegersToString(Integer[] ints){
        String compiledString = "";
        for(int i= 0; i < ints.length; i++){
            compiledString += ints[i];
        }
        return compiledString;
    }
    public static String[] divideIntoSegments(String data){
        return data.toLowerCase().split("#");
    }

    public static void toggleDebug(){
        debug = !debug;
    }

    public static void main(String[] args){
        Action a = new Action("Row#02");
        String[] segments = divideIntoSegments(a.toString());
        System.out.println("Segment[1]=" + segments[1]);
        Integer[] integers = convertStringToIntegers(segments[1]);
        System.out.println(integers[0]);
        System.out.println(integers[1]);
    }
}