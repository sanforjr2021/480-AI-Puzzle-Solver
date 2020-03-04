package com.sanford.puzzleAI;

import com.sanford.puzzleAI.AI.Action;

public class Util {
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
                System.err.println("Value " + list[i] + " is not a number(Util.java:17)");
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

    /**
     * Divides a string based off of where # are and clean them up to reduce spaces and be lowercase.
     * @param data
     * @return
     */
    public static String[] divideIntoSegments(String data){
        return data.toLowerCase().trim().split("#");
    }

    public static String switchTwoCharacters(String s, int startIndex, int endIndex){
        char startIndexChar = s.charAt(startIndex);
        char endIndexChar = s.charAt(endIndex);
        char list[] = s.toCharArray();
        //switch end char and start char
        list[startIndex] = endIndexChar;
        list[endIndex] = startIndexChar;
        //make it back to a string.
        return String.valueOf(list);
    }
}