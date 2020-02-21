package com.sanford.puzzleAI.AI.problem;

import com.sanford.puzzleAI.AI.Action;
import com.sanford.puzzleAI.AI.Node;
import com.sanford.puzzleAI.AI.State;

import java.util.ArrayList;

public abstract class Problem {

    private State initialState, goalState;

    public Problem(State initialState, State goalState){
        this.initialState = initialState;
        this.goalState = goalState;
    }

    //Abstract classes
    public abstract ArrayList<Action> actions(State s);

    public abstract State result(State s, Action a);

    public abstract int pathCost(Node node);
    public abstract Boolean hasLost(State s);
    //////////////////////////////////////////////
    //Classes that will not need an @Override
    //////////////////////////////////////////////

    public State getInitialState(){
        return initialState;
    }

    public State getGoalState(){
        return goalState;
    }

    public boolean goalTest(State s){
        return s.toString().equals(goalState.toString());
    }

}
