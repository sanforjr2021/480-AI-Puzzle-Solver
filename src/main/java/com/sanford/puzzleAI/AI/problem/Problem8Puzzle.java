package com.sanford.puzzleAI.AI.problem;

import com.sanford.puzzleAI.AI.Action;
import com.sanford.puzzleAI.AI.Node;
import com.sanford.puzzleAI.AI.State;

import java.util.ArrayList;

public class Problem8Puzzle extends Problem {


    public Problem8Puzzle(State initialState, State goalState) {
        super(initialState, goalState);
    }

    @Override
    public ArrayList<Action> actions(State s) {
        ArrayList<Action> actions = new ArrayList<Action>();
        /*Possible 4 Actions:
        Move 0 to the left: left
        Move 0 to the right: right
        Move 0 to the top: up
        Move 0 to the bottom: down
         */
        int location = 0; //TODO: get location of 0 from state s.
        //Left
        if(location == 1 || location == 2 || location == 4 || location == 5 || location == 7 || location == 7){
            actions.add(new Action("left"));
        }//right
        if(location == 0 || location == 1 || location == 4 || location == 6 || location == 7 || location == 3){
            actions.add(new Action("right"));
        }
        //up
        if(location == 3 || location == 4 || location == 5 || location == 6 || location == 7 || location == 8){
            actions.add(new Action("up"));
        }
        //down
        if(location == 0 || location == 1 || location == 2 || location == 3 || location == 4 || location == 5){
            actions.add(new Action("down"));
        }
        return actions;
    }

    @Override
    public State result(State s, Action a) {
        //TODO: calculate result based off the 4 possible actions.
        return null;
    }

    @Override
    public int pathCost(Node node) {
        //path cost will not be considered in this problem
        return 0;
    }

    @Override
    public Boolean hasLost(State s) {
        //TODO: find checks on how to lose.
        return false;
    }
}
