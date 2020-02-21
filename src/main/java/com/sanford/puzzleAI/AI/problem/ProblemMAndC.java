package com.sanford.puzzleAI.AI.problem;

import com.sanford.puzzleAI.AI.Action;
import com.sanford.puzzleAI.AI.Node;
import com.sanford.puzzleAI.AI.State;
import com.sanford.puzzleAI.Util;

import java.util.ArrayList;

import static com.sanford.puzzleAI.Util.convertStringToIntegers;

public class ProblemMAndC extends Problem {

    public ProblemMAndC(State initialState, State goalState) {
        super(initialState, goalState);
    }

    @Override
    public ArrayList<Action> actions(State s) {
        ArrayList<Action> list = new ArrayList<Action>();
        Integer[] stateInts = convertStringToIntegers(s.toString());
        int leftMissionary = stateInts[0]; int leftCan = stateInts[1]; int leftBoat = stateInts[2];
        int rightBoat = stateInts[3]; int rightMissionary = stateInts[4]; int rightCan = stateInts[5];
        if(leftBoat == 1 && rightBoat == 0){ //boat left
            if(leftMissionary >= 1) {
                list.add(new Action("Row#10"));
                if(leftMissionary >= 2){
                    list.add(new Action("Row#20"));
                }
                if(leftCan >= 1){
                    list.add(new Action("Row#11"));
                }
            }
            if(leftCan >= 1){
                list.add(new Action("Row#01"));
                if(leftCan >= 2){
                    list.add(new Action("Row#02"));
                }
            }
        }
        else if(leftBoat == 0 && rightBoat == 1){ //boat right
            if(rightMissionary >= 1) {
                list.add(new Action("Row#10"));
                if(rightMissionary >= 2){
                    list.add(new Action("Row#20"));
                }
                if(rightCan >= 1){
                    list.add(new Action("Row#11"));
                }
            }
            if(rightCan >= 1){
                list.add(new Action("Row#01"));
                if(rightCan >= 2){
                    list.add(new Action("Row#02"));
                }
            }
        }
        return list;
    }
    @Override
    public Boolean hasLost(State s){
        return s == null;
    }
    @Override
    public State result(State s, Action a) {
        //split state and action into integers
        Integer[] stateInts = convertStringToIntegers(s.toString());
        String[] action = a.toString().split("#");
        Integer[] actionInts = convertStringToIntegers(action[1]);
        //convert values to readable values
        int leftMissionary = stateInts[0]; int leftCan = stateInts[1]; int leftBoat = stateInts[2];
        int rightBoat = stateInts[3]; int rightMissionary = stateInts[4]; int rightCan = stateInts[5];
        int actionMissionary = actionInts[0]; int actionCan = actionInts[1];
        //calculate new posistions
        if(leftBoat == 1 && rightBoat == 0){//boat is left
            //move boat
            leftBoat = 0;
            rightBoat = 1;
            //switch sides
            leftMissionary -= actionMissionary;
            rightMissionary += actionMissionary;
            leftCan -= actionCan;
            rightCan += actionCan;
        }
        else if(rightBoat == 1 && leftBoat == 0){//boat is right
            //move boat
            leftBoat = 1;
            rightBoat = 0;
            //switch sides
            leftMissionary += actionMissionary;
            rightMissionary -= actionMissionary;
            leftCan += actionCan;
            rightCan -= actionCan;
        }

        //set update stateInts with new values.
        stateInts[0] = leftMissionary ;  stateInts[1] = leftCan;  stateInts[2] = leftBoat;
        stateInts[3] = rightBoat; stateInts[4] = rightMissionary ; stateInts[5] = rightCan;
        //checks for errors before making into a new state.
        System.out.print("\tStarting State:" + s.toString() + "\tAction = " + a.toString() + "\tExpected State=");
        for(int i = 0; i < stateInts.length; i++){
            System.out.print(stateInts[i]);
        }
        System.out.println();
        if(leftMissionary + rightMissionary < 3 || leftCan + rightCan < 3){ //make sure there is no more than the original value of cannibles
            System.out.println("\t[ERROR]:Cannibals and missionaries are greater than 3");
            return null; //invalid
        }

        if((leftMissionary > 0 && leftCan > leftMissionary )|| (rightMissionary > 0 && rightCan > rightMissionary )){ //make sure cannibals never outnumber missionaries
            System.out.println("\t[ERROR]:Cannibals outnumber missionaries");
            return null; // invalid
        }
        for(int i = 0; i < stateInts.length-1; i++){
            if(stateInts[i] > 3 || stateInts[i] < 0){ //if value is beyond 3 or less than 0
                System.out.println("\t[ERROR]:An items is beyond the bounds 3 to 1.");
                return null;
            }
        }
        return new State(Util.convertIntegersToString(stateInts));
    }

    public String toString(){
        return "ProblemM&C: Initial=" + getInitialState() + "\tGoal=" + getGoalState();
    }
    @Override
    public int pathCost(Node node) {
        return 0;
    }

    public static void main(String[] args){
        State initial = new State("331000");
        State goal = new State("000133");
        ProblemMAndC problem = new ProblemMAndC(initial, goal);
        System.out.println("Initial State: " + problem.getInitialState());
        System.out.println("Goal State:" + problem.getGoalState());
        System.out.println(problem.toString());
        System.out.println("\nGoal Test:" + problem.goalTest(problem.getInitialState()));
        System.out.println("Goal Test with final state:" + problem.goalTest(problem.getGoalState()));
        ArrayList<Action> list = problem.actions(initial);
        System.out.println("\nPossible outcomes from initial state with a size of " + list.size());
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).equals(null)){
                System.out.println(i + " : Invalid Node.");
            }
            else{
                System.out.println(i +" : " + list.get(i).toString());
            }
        }
        State secondState = problem.result(initial, list.get(4));
        System.out.println("Results of combining Initial state and action 2:");
        System.out.println("\t\t" + secondState.toString());
    }
}
