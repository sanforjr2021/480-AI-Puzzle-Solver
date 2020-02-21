package com.sanford.puzzleAI.AI;

import static com.sanford.puzzleAI.Util.divideIntoSegments;

/**
 *How To Add a new checker:
 * 1. Register the name of the puzzle in the switch. 
 * 2. Create a method to check the problem and solution and place it in the switch to register it.
 * 3. If you have an additional puzzleData than problem and solution, create a method for each of them.
 * @author Sanford
 */
public class ValidityChecker {
    private String input;
    private String[] puzzleData; //Type, Problem, Solution
    private Boolean isValid;
    private Agent agent;
    
    public ValidityChecker(String input) {
        this.input = input;
        isValid = true;
        puzzleData = divideIntoSegments(input);
        validateByType();
        if(isValid){
            agent = new Agent(input);
            agent.getSeq();
        }
    }
    private void validateByType(){
        switch(puzzleData[0]){
            case "8puzzle":
                System.out.println("Puzzle Type: 8Puzzle");
                validate8Puzzle(puzzleData[1], "Problem");
                validate8Puzzle(puzzleData[2], "Solution");
                break;
            case "m&c":
                System.out.println("Puzzle Type: M&C");
                validateMC(puzzleData[1], "Problem");
                validateMC(puzzleData[2], "Solution");
                break;
            case "maze":
                System.out.println("Puzzle Type: Maze");
                validateMazeCords(puzzleData[0], "Problem");
                validateMazeCords(puzzleData[1], "Solution");
            default:
                isValid = false;
                System.out.println(puzzleData[0] + " is not a valid type of game");
                break;
        }
    }
    //removes excess space, and divides by hashtags
////////////////////////////////////
// Validator for problem + solution
////////////////////////////////////
    private void validate8Puzzle(String validatingInput, String type){
        if(validatingInput.length() != 9){
            isValid = false;
            System.err.println("The " + type + " does not contain 8 characters");
        }
        else{
            if(!validatingInput.contains("0")){isValid = false;}
            if(!validatingInput.contains("1")){isValid = false;}
            if(!validatingInput.contains("2")){isValid = false;}
            if(!validatingInput.contains("3")){isValid = false;}
            if(!validatingInput.contains("4")){isValid = false;}
            if(!validatingInput.contains("5")){isValid = false;}
            if(!validatingInput.contains("7")){isValid = false;}
            if(!validatingInput.contains("8")){isValid = false;}
            if(isValid == false){
                System.err.println("The 8Puzzle's " + type + " does not contain numbers 0-8");
            }
        }
    }
    private void validateMC(String validatingInput, String type){
        Integer mcNum;
        try{
            mcNum = Integer.parseInt(validatingInput);
        }
        catch(Exception e){
            System.out.println("The " + type +" does not contain only digits");
            isValid = false;
            return;
        }
        if(validatingInput.length() != 6){
            isValid = false;
            System.out.println("The " + type + " is not 6 charaters long");
        }
    }

    ////////////////////////////
    //TODO: FINISH THESE
    /////////////////////////////////////////
    private void validateMazeCords(String validatingInput, String type) {
        //todo: validate #5 5 and #0 1
    }
    private void validateMazeDimensions(String validatingInput){
        //todo: validate #5 5
    }
    private void validateMazeMap(String validate){}
//////////////////////////////////////
//      Getters
/////////////////////////////////////
    public String getInput() {
        return input;
    }
    public String getType(){
        if( isValid){
            return puzzleData[0];
        }
        else{
            return null;
        }
    }
    public String getProblem(){
        if( isValid){
            return puzzleData[1];
        }
        else{
            return null;
        }
    }
    public String getSolution(){
        if( isValid){
            return puzzleData[2];
        }
        else{
            return null;
        }//end of else
    } // end of getSolution
}//end of Validator
