package org.example.Model;

import java.util.*;

import static org.example.Model.Tape.BLANK;


public class TuringMachine {

    private String input;
    private Tape tape = new Tape();
    private ArrayList <Transition> stateRules;
    private String startState;
    private String currentState;
    private Register  totalMoney;
    private Register.ItemRegister items = new Register.ItemRegister();
    private StringBuilder sb = new StringBuilder();


    private double totalItemCost;

    static class  InsufficientFundsException extends Exception{

        public InsufficientFundsException (String str){
            super(str);
        }
    }



    // This constructor show be used in a loop to keep the programing running after an exception is thrown
    public TuringMachine(String input) {

        boolean success = false;
        try {
            validateInput(input);
            success = true;
        }catch (Tape.InvalidInputException e){
            System.err.println(e);
            currentState = "qr";
            System.err.println("Reject State: Current State [ " + currentState+  " ] ");

        }

        if(success){
            tape.input(input);
            //welcomeText();
            setStateRules();
            start();
        }
    }

    public void setStateRules(){

        //acceptable String Transitions reject everything else
        stateRules = new ArrayList<>();
        startState = "q0";
        stateRules.add(new Transition("q0","q1",'a','a',"R"));
        stateRules.add(new Transition("q0","q1",'b','b',"R"));
        stateRules.add(new Transition("q0","q1",'c','c',"R"));


        stateRules.add(new Transition("q1","q1",'a','a',"R"));
        stateRules.add(new Transition("q1","q1",'b','b',"R"));
        stateRules.add(new Transition("q1","q1",'c','C',"R"));
        stateRules.add(new Transition("q1","q2",'N','N',"R"));
        stateRules.add(new Transition("q1","q2",'F','F',"R"));
        stateRules.add(new Transition("q1","q2",'S','S',"R"));
        stateRules.add(new Transition("q1","q2",'K','K',"R"));


        stateRules.add(new Transition("q2","q2",'N','N',"R"));
        stateRules.add(new Transition("q2","q2",'S','S',"R"));
        stateRules.add(new Transition("q2","q2",'K','K',"R"));
        stateRules.add(new Transition("q2","q2",'F','F',"R"));
        stateRules.add(new Transition("q2","qa",BLANK,BLANK,"R"));
        stateRules.add(new Transition("qa","qa"));


        //Restock String
        stateRules.add(new Transition("q0","q5",'N','N',"R"));
        stateRules.add(new Transition("q5","q6",'K','K',"R"));
        stateRules.add(new Transition("q6","q7",'S','S',"R"));
        stateRules.add(new Transition("q7","q8",'F','F',"R"));
        stateRules.add(new Transition("q8","q9",'F','F',"R"));
        stateRules.add(new Transition("q9","q10",'S','S',"R"));
        stateRules.add(new Transition("q10","q11",'K','K',"R"));
        stateRules.add(new Transition("q11","q12",'S','S',"R"));

        //Accept restock input
        stateRules.add(new Transition("q12","qa2",BLANK,BLANK,"R"));
    }

    public void start(){

        currentState = startState;
        Transition currentTransition = null;
        do {
            if (currentTransition != null){
                currentState = currentTransition.getNextState();
            }
            currentTransition = changeState(currentState);
        }while(currentTransition != null);
    }

    public Transition changeState(String state){

        Transition transition = null;



        for(Transition rule : stateRules){

            if(Objects.equals(rule.getCurrentState(), currentState)){
                if (rule.getReadCharacter() == tape.getHead() ){
                    transition = rule;

                    sb.append(transition.getWriteCharacter());

                    System.out.println("[0] " + transition.toString()+"\n");
                    tape.setHead(transition.getWriteCharacter());

                    // move tape
                    if(Objects.equals(transition.getDirection(), "R")){
                        tape.moveHeadRight();
                    } else if (Objects.equals(transition.getDirection(), "L")) {
                        tape.moveHeadLeft();
                    }

                    // If input is accepted
                    if(Objects.equals(transition.getNextState(), "qa2")){

                        System.out.println("Accept State");



                        //TODO add code to restock register
                    }

                    if(Objects.equals(transition.getNextState(), "qa")){
                        System.out.println("Accept State");

                        System.out.println(sb.toString());

                        try {
                            purchaseItem();
                        }catch (InsufficientFundsException e){
                            System.err.println(e);
                        }
                        //totalItemCost()

                        //TODO add code to dispense item to use
                    }

                    //If input is rejected
                    if(Objects.equals(currentState, "qr")){
                        System.err.println("There was an error, Here is your money you filthy animal  :)");
                        //TODO add code to input id wrong or when stock is too low

                    }

                }
            }
        }

        return transition;
    }

    public void purchaseItem() throws InsufficientFundsException{

        double costForItems = items.getCostForItems(sb.toString());
        double moneyGiven = items.getTotalInput(sb.toString());

        if(costForItems>moneyGiven){
            throw  new InsufficientFundsException("Please Enter more money and tray again");
        }

        //TODO figure out a way to purchase this item and to dispense the individual items, and another thing we need to give the user their change
    }

    public void dispenseItems(){

    }

    public void validateInput(String input) throws Tape.InvalidInputException {

        //Reading through the String, checking if it is a valid input
        for(int i = input.length() -1 ; i>=0 ; i--){
            char c = input.charAt(i);
            if(!Arrays.asList(Tape.TAPEALPHABET).contains(input.charAt(i))) {
                throw new  Tape.InvalidInputException("[ " + c + " ] is not Present is the Tape Alphabet");
            }
        }
    }

    public void addStateRule(Transition transition){
        stateRules.add(transition);
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public ArrayList<Transition> getStateRules() {
        return stateRules;
    }

    public void setStateRules(ArrayList<Transition> stateRules) {
        this.stateRules = stateRules;
    }

    @Override
    public String toString() {
        return "TuringMachine{" +
                "input='" + input + '\'' +
                ", stateRules=" + stateRules +
                ", startState=" + startState +
                ", currentState=" + currentState +
                '}';
    }
}
