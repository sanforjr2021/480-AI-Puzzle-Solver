package com.sanford.puzzleAI.AI;

import com.sanford.puzzleAI.AI.problem.ProblemMAndC;

import java.util.ArrayList;

public class QueueFIFO {
///////////////////////////////
//Variables
///////////////////////////////

    private ArrayList<Node> nodes;

///////////////////////////////////////
//constructor
//////////////////////////////////////////

    public QueueFIFO() {
        nodes = new ArrayList<Node>();
    }

//////////////////////////////////////////////
//methods
//////////////////////////////////////////////

    public Boolean isEmpty(){
        return nodes.isEmpty();
    }
    public Node pop(){
        Node node = nodes.get(0);
        nodes.remove(0);
        return node;
    }
    public Boolean isStateInQueue(Node node){
        for(int i = 0; i < nodes.size(); i++){
            if(node.getState().toString().equals( nodes.get(i).getState().toString())){
                return true;
            }
        }
        return false;
    }
    public void insert(Node node){
        nodes.add(node);
    }
    public ArrayList<Node> getList(){
        return nodes;
    }
    public Integer getSize(){
        return nodes.size();
    }
    public String toString(){
        String states = "";
        for(int i = 0; i < nodes.size(); i++){
            if(i%10 == 0){
                states+="\n";
            }
            states += nodes.get(i).getState().toString() + ", ";
        }
        return states;
    }
    //Test Method
    public static void main(String[] args){
        QueueFIFO queueFIFO = new QueueFIFO();
        System.out.println("Is empty at start:" + queueFIFO.isEmpty());
        queueFIFO.insert(new Node(
                new ProblemMAndC(new State("331000"), new State("000133"))
        ));
        queueFIFO.insert(new Node(
                new ProblemMAndC(new State("010132"), new State("000133"))
        ));
        System.out.println("Added 2 Nodes. Size is now: " + queueFIFO.getSize() +  ".\n Is Empty: " + queueFIFO.isEmpty());

        System.out.println("Is state in queue(Should succeed):" +
                queueFIFO.isStateInQueue(new Node(
                        new ProblemMAndC(new State("010132"), new State("000133"))
        )));
        System.out.println("Is state in queue(Should fail):" + queueFIFO.isStateInQueue(new Node(
                new ProblemMAndC(new State("010133"), new State("000133"))
        )));
        queueFIFO.pop();
        System.out.println("Popped object." + queueFIFO.getSize());
    }
}
