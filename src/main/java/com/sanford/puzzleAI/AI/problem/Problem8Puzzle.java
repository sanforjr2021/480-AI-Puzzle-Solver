package com.sanford.puzzleAI.AI.problem;

import com.sanford.puzzleAI.AI.Action;
import com.sanford.puzzleAI.AI.Node;
import com.sanford.puzzleAI.AI.State;

import java.util.ArrayList;

import static com.sanford.puzzleAI.Util.convertIntegersToString;
import static com.sanford.puzzleAI.Util.convertStringToIntegers;

public class Problem8Puzzle extends Problem {


    public Problem8Puzzle(State initialState, State goalState) {
        super(initialState, goalState);
    }

    @Override
    public ArrayList<Action> actions(State s) {
        ArrayList<Action> actions = new ArrayList<Action>();
        //Get location of "0"
        int location = s.toString().indexOf("0");
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
        //useful data
        String state = s.toString();
        String action = a.toString();
        System.out.print("\tInitial State =" +state + "" +"\tAction=" + action);
        Integer indexOf0 = state.indexOf("0");
        Integer[] linearPuzzle = convertStringToIntegers(state);
        Integer[][] puzzleLayout = new Integer[3][3];
        int y0 = 0; int x0 = 0; //values of index of 0
        //Puzzle Layout in matrix//
        // x1, y1  x2, y1  x3, y1//
        // x1, y2  x2, y2  x3, y2//
        // x1, y3  x2, y3  x3, y3//
        ///////////////////////////
        //format into a matrix as puzzleLayout
        for(Integer i = 0; i < linearPuzzle.length; i++) {
            //
            int x = 0;
            int y = 0;
            //X Cord
            if (i % 3 == 0) {x = 0;}
            else if (i % 3 == 1) {x = 1;}
            else if (i % 3 == 2) {x = 2;}
            //Y Cord
            if (i / 3 == 0) {y = 0; }
            else if (i / 3 == 1) { y = 1; }
            else if (i / 3 == 2) { y = 2; }
            //mark the cords of 0
            if (i == indexOf0) {
                y0 = y;
                x0 = x;
            }
            puzzleLayout[x][y] = linearPuzzle[i];
        }
        //apply action
        if(action.equals("left")){
            puzzleLayout[x0][y0] = puzzleLayout[x0-1][y0]; //replace the 0
            puzzleLayout[x0-1][y0] = 0; //set moved value to 0
        }
        else if(action.equals("right")){
            puzzleLayout[x0][y0] = puzzleLayout[x0+1][y0]; //replace the 0
            puzzleLayout[x0+1][y0] = 0; //set moved value to 0
        }
        else if(action.equals("up")){
            puzzleLayout[x0][y0] = puzzleLayout[x0][y0-1]; //replace the 0
            puzzleLayout[x0][y0-1] = 0; //set moved value to 0
        }
        else if(action.equals("down")){
            puzzleLayout[x0][y0] = puzzleLayout[x0][y0+1]; //replace the 0
            puzzleLayout[x0][y0+1] = 0; //set moved value to 0
        }
        //update linearPuzzle with the right values
        for(Integer i = 0; i < linearPuzzle.length; i++){
            linearPuzzle[i] = puzzleLayout[i%3][i/3];
        }
        s = new State(convertIntegersToString(linearPuzzle));
        System.out.print("\tNew State = " + s.toString() + "\n");
        //convert it back into a string & State
        return s;
    }

    @Override
    public int pathCost(Node node) {
        //path cost will not be considered in this problem
        return 0;
    }

    @Override
    public Boolean hasLost(State s) {
        //No invalid lost state unless state == null
        return s.toString().equals(null);
    }
    public static void main(String args[]){
        State initial =new State("012345678");
        State solution = new State("123456780");
        Problem8Puzzle problem = new Problem8Puzzle(initial, solution);
        System.out.println("Initial State: " + problem.getInitialState());
        System.out.println("Goal State:" + problem.getGoalState());
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
        State secondState = problem.result(initial, list.get(1));
        System.out.println("Results of combining Initial state and action 1:");
        System.out.println("\t\t" + secondState.toString());
    }
}

