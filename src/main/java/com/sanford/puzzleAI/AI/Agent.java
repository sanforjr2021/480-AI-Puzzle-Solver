package com.sanford.puzzleAI.AI;

import com.sanford.puzzleAI.AI.problem.Problem;
import com.sanford.puzzleAI.AI.problem.Problem8Puzzle;
import com.sanford.puzzleAI.AI.problem.ProblemMAndC;

import java.util.ArrayList;

import static com.sanford.puzzleAI.Util.divideIntoSegments;

public class Agent {
    private Problem problem;
    private ArrayList<Node> frontier, explored;
    private String[] dataSegments;
    public Agent(String originalData){
        dataSegments = divideIntoSegments(originalData);
        frontier = new ArrayList<Node>();
        explored = new ArrayList<Node>();
        System.out.println("Problem Type:" + dataSegments[0]);
        State initial = new State(dataSegments[1]);
        State goal = new State(dataSegments[2]);
        System.out.println("Initial State: " + initial.toString());
        System.out.println("Goal State: " + goal.toString());
        problem = formulateProblem(initial, goal);
    }

    public String getProblemName(){
        return dataSegments[0];
    }

    public Sequence getSeq(){
        return breadthFirstSearch(problem);
    }
    public Action simpleProblemSolvingAgent(Percept percept){
        //TODO: complete the method
        return null;
    }
    private Problem formulateProblem(State state, State goal) {
        Problem problem;
        switch (dataSegments[0]) {
            case "m&c":
                problem = new ProblemMAndC(state, goal);
                break;
            case "8puzzle":
                problem = new Problem8Puzzle(state, goal);
                break;
            default:
                System.out.println("No valid problem type entered.");
                problem = null;
        }
        return problem;
    }
    private Node childNode(Problem problem, Node parent, Action action){
        return new Node(problem, parent, action);
    }

    private Sequence breadthFirstSearch(Problem problem){
        Integer count = 0;
        QueueFIFO frontier = new QueueFIFO();
        QueueFIFO explored = new QueueFIFO();
        Node parentNode = new Node(problem);
        frontier.insert(new Node(problem));
        Node currentNode;

        while(!frontier.isEmpty()){ //if the frontier is NOT empty(Still has unexplored nodes), keep trying to solve.
            currentNode = frontier.pop();
            ArrayList<Action> actions = problem.actions(currentNode.getState());
            explored.insert(currentNode);

            for(int i = 0; i < actions.size(); i++)
            {
                count++;
                System.out.println("(Node #" + count + ")Creating Child Node:");
                Node childNode = new Node(problem, currentNode, actions.get(i));
                if(problem.hasLost(childNode.getState())){
                    //checks to see if it has failed already
                }
                else if(frontier.isStateInQueue(childNode)){ //check to see if its in the frontier
                    System.out.println("\tNode is already in frontier.");
                }
                else if(explored.isStateInQueue(childNode)){ //check to see if its in the explored
                    System.out.println("\tNode is already in explored");
                }
                else {
                    if(problem.goalTest(childNode.getState())){
                        System.out.println("*************************************\n\t\tFound Solution\n***************************************");
                        return childNode.getSolution();
                    }//end of if problem.goalTest
                    frontier.insert(childNode);
                }//end of if for inStateInQueue
            }//end of for actions
            System.out.println("\nFrontier Size:" + frontier.getSize() + "\tExplored Size: " + explored.getSize()) ;
        }//end of while
        System.out.println("*************************************\n\t\tNo Valid Solution\n***************************************");
        return new Sequence(new ArrayList<Action>());
    }//end of breadthFirstSearch

    public static void main(String[] args){
        Agent agent = new Agent("M&C#331000#000133");
        Sequence seq = agent.breadthFirstSearch(agent.problem);
        System.out.println(seq.toString());

        agent = new Agent("M&C#331000#000155");
        seq = agent.breadthFirstSearch(agent.problem);
        System.out.println(seq.toString());

        agent = new Agent("8puzzle#012345678#102345678");
        seq = agent.breadthFirstSearch(agent.problem);
        System.out.println(seq.toString());

        agent = new Agent("8puzzle#012345678#125348607");
        seq = agent.breadthFirstSearch(agent.problem);
        System.out.println(seq.toString());
    }
}
