package com.sanford.puzzleAI.AI;

import com.sanford.puzzleAI.AI.problem.Problem;
import com.sanford.puzzleAI.AI.problem.ProblemMAndC;

import java.util.ArrayList;


public class Node {
    private State state;
    private Node parent;
    private Action action;
    private Integer pathCost;
    private Integer depth;

    //child node
    public Node(Problem problem, Node parent, Action action){
        this.state = problem.result(parent.state, action);
        this.parent = parent;
        this.action = action;
        this.pathCost = 1;
        //this.pathCost = parent.PathCost + problem.stepCost(Paent.state, action);
    }
    //orphan node
    public Node(Problem problem){
        this.state = problem.getInitialState();
        this.parent = null;
        this.action = null;
        this.pathCost = 1;
    }
    public Sequence getSolution(){
        Node tempParent = parent;
        ArrayList<Action> actions = new ArrayList<Action>();
        actions.add(action);
        while(tempParent != null){
            if(tempParent.action != null){
                actions.add(tempParent.action);
            }
            tempParent = tempParent.getParent();
        }
        return new Sequence(actions);
    }

    //getter & Setters
    public State getState() {
        return state;
    }

    public Node getParent() {
        return parent;
    }

    public Action getAction() {
        return action;
    }

    public Integer getPathCost() {
        return pathCost;
    }

    public static void main(String[] args){
        Node myNode = new Node(new ProblemMAndC(new State("331000"), new State("000133")));
        System.out.println(myNode.toString());
    }
}
  