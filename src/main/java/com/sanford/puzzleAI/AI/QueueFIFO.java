package com.sanford.puzzleAI.AI;

import com.sanford.puzzleAI.AI.problem.Problem8Puzzle;
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
        for (int i = 0; i < nodes.size(); i++) {
            if (i % 10 == 0) {
                states += "\n";
            }
            states += nodes.get(i).getState().toString() + ", ";
        }
        return states;
    }
    //Test Method
    public static void main(String[] args){
        QueueFIFO queue = new QueueFIFO();
        System.out.println("Is empty:" + queue.isEmpty());
        queue.insert(new Node(new Problem8Puzzle(new State("012345678"), new State("123456780"))));
        queue.insert(new Node(new ProblemMAndC(new State("331000"), new State("000133"))));
        System.out.println("Inserted 2 items");
        for(Node node:  queue.getList()){
            System.out.println(node.toString());
        }
        System.out.println("Popping item : "+ queue.pop().toString());
        System.out.println("Is Empty: " + queue.isEmpty());
    }
}
