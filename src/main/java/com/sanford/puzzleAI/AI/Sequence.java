package com.sanford.puzzleAI.AI;

import java.util.ArrayList;

public class Sequence {

    private ArrayList<Action> actionArrayList;

    public Sequence(ArrayList<Action> theSequence){
        actionArrayList = theSequence;
    }
    public ArrayList<Action> getTheSequence(){
        return actionArrayList;
    }
    public Action getFirst(){
        return actionArrayList.get(0);
    }
    public void pop(){
        actionArrayList.remove(0);
    }
    public Sequence getRest(){
        Sequence restOfSequence = new Sequence(actionArrayList);
        restOfSequence.pop();
        return restOfSequence;
    }
    public String toString(){
        String answer = "";
        for(int i = 0; i < actionArrayList.size(); i++){
            answer += actionArrayList.get(i).toString()+"\n";
        }
        return answer;
    }
    public static void main(String[] args){
        Action action1 = new Action("Row#11");
        Action action2 = new Action("Row#01");
        Action action3 = new Action("Row#20");
        Action action4 = new Action("Row#02");

        ArrayList<Action> actions = new ArrayList<Action>();
        actions.add(action1);
        actions.add(action2);
        actions.add(action3);
        actions.add(action4);

        Sequence seq = new Sequence(actions);
        System.out.println("First: " + seq.getFirst().toString());
        System.out.println("Popping first");
        seq.pop();
        System.out.println("New First: " + seq.getFirst());
        System.out.println("Rest of the sequence:");
        System.out.println(seq.getRest().toString());
        System.out.println("ToString: " + seq.toString());
    }




}
