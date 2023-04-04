package org.example.Model;

import java.io.File;
import java.util.*;

import static org.example.Model.ConsoleColors.*;
import static org.example.Model.Tape.BLANK;


public class TuringMachine {
    private String input;
    private static final Tape tape = new Tape();
    private ArrayList <Transition> stateRules;
    private String startState;
    private String currentState;
    private StringBuilder sb = new StringBuilder();
    private static final Register INPUTMONEYREGISTER = new Register();
    private static final Map<String,Register> ITEMREGISTERMAP = new HashMap<String,Register>();
    private int totalCost;
    private int totalInput;

    private final StringBuilder moneyGiven = new StringBuilder();
    private final  StringBuilder itemsRequested = new StringBuilder();
    private static final VendingMachine vendingMachine = new VendingMachine();

/*    private static final Register FITEMREGISTER = new Register();
    private static final Register SITEMREGISTER = new Register();
    private static final Register KITEMREGISTER = new Register();
    private static final Register NITEMREGISTER = new Register();*/

    static class  InsufficientFundsException extends Exception{

        public InsufficientFundsException (String str){
            super(str);
        }
    }



    // This constructor show be used in a loop to keep the programing running after an exception is thrown
    public TuringMachine(String input) {
        ITEMREGISTERMAP.put("K",new Register());
        ITEMREGISTERMAP.put("F",new Register());
        ITEMREGISTERMAP.put("N",new Register());
        ITEMREGISTERMAP.put("S",new Register());



        boolean success = false;
        try {
            validateInput(input);
            success = true;
        }catch (Tape.InvalidInputException e){
            System.err.println(e.toString());
            currentState = "qr";
            System.err.println("Reject State: Current State [ " + currentState+  " ] ");

        }

        if(success){
            tape.input(input);
            //welcomeText();
            setStateRules();
            try {
                start();
            } catch (Tape.InvalidInputException e) {
               System.err.println(e);
            }
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

        //Ignore Just testing something
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

    public void start() throws Tape.InvalidInputException{

        currentState = startState;
        Transition currentTransition = null;
        do {
            if (currentTransition != null){
                currentState = currentTransition.getNextState();
            }
            currentTransition = changeState(currentState);
        }while(currentTransition != null);
    }

    public Transition changeState(String state) throws Tape.InvalidInputException{

        Transition transition = null;




        for(Transition rule : stateRules){

            if(Objects.equals(rule.getCurrentState(), currentState)){
                if (rule.getReadCharacter() == tape.getHead() ){
                    transition = rule;
                    sb.append(transition.getWriteCharacter());

                    System.out.println(CYAN_BOLD+ "[Σ] " + transition.toString()+"\n" + RESET);
                    tape.setHead(transition.getWriteCharacter());

                    /*
                    * Here we specify the movement of the Tape head left or right depending on the state
                    * */
                    if(Objects.equals(transition.getDirection(), "R")){
                        tape.moveHeadRight();
                    } else if (Objects.equals(transition.getDirection(), "L")) {
                        tape.moveHeadLeft();
                    }

                    /*
                    *
                    * Performing computations with the registers
                    *
                    * */

                    switch (transition.getReadCharacter()) {
                        case 'a':

                            INPUTMONEYREGISTER.add();
                            totalInput += new Money().getValue("a");
                            moneyGiven.append("a");
                            break;
                        case 'b':

                            INPUTMONEYREGISTER.add();
                            totalInput += new Money().getValue("b");
                            moneyGiven.append("b");
                            break;
                        case 'c':

                            INPUTMONEYREGISTER.add();
                            totalInput += new Money().getValue("c");
                            moneyGiven.append("c");
                            break;
                        case 'F' :
                            ITEMREGISTERMAP.get("F").add();
                            totalCost += new Money().getPrice("F");
                            itemsRequested.append("F");
                            break;

                        case 'K' :
                            ITEMREGISTERMAP.get("K").add();
                            totalCost += new Money().getPrice("K");
                            itemsRequested.append("K");
                            break;

                        case 'N' :
                            ITEMREGISTERMAP.get("N").add();
                            totalCost += new Money().getPrice("N");
                            itemsRequested.append("N");
                            break;

                        case 'S' :
                            ITEMREGISTERMAP.get("S").add();
                            totalCost += new Money().getPrice("S");
                            itemsRequested.append("S");
                            break;
                    }

                    // If input is accepted
                    if(Objects.equals(transition.getNextState(), "qa2") & tape.getHead() == BLANK){
                        System.out.println( GREEN_BOLD+ "[qa2] Restock: Accept State" + RESET);

                        restock();
                        //TODO add code to restock register
                    }

                    else if(Objects.equals(transition.getNextState(), "qa")& tape.getHead() == BLANK){
                        System.out.println(GREEN_BOLD + "[qa] Purchase: Accept State" +  RESET);

                        System.out.println(sb.toString());

                        try {
                            purchaseItem();
                        }catch (InsufficientFundsException e){
                            System.err.println(e);
                        }
                        //totalItemCost()

                        //TODO add code to dispense item to use
                    }

/*                    if(Objects.equals(transition.getNextState(), "qa2") & tape.getHead() !=   BLANK){
                        throw new Tape.InvalidInputException("There was an error in the code");
                    } else if (Objects.equals(transition.getNextState(), "qa") & tape.getHead() != BLANK) {
                        throw new Tape.InvalidInputException("There was an error in the code");
                    }*/
                }
            }
        }

        return transition;
    }

    public void purchaseItem() throws  InsufficientFundsException{

        if(totalInput<totalCost){
            throw new InsufficientFundsException("Insufficient Funds Total Input = "  + totalInput + " Total Cost = " + totalCost);
        }

        /*new FileHandler().logPurchase(new Purchase(totalInput,"NKS"));*/

        System.out.println(new FileHandler().calculateTotalPurchase());

        /*
        *  Subtracting the items from the stock
        * */


        /*
        * Save the Result to the File
        * */
        /*int n =0,s=0,k=0,f = 0;*/
        /*String inputString = itemsRequested.toString();

        for (char c :  inputString.toCharArray()) {
                switch (c) {
                    case 'F' -> vendingMachine.setForkCount(vendingMachine.getForkCount() - 1);
                    case 'N' -> vendingMachine.setNapkinCount(vendingMachine.getNapkinCount() - 1);
                    case 'S' -> vendingMachine.setSpoonCount(vendingMachine.getSpoonCount() - 1);
                    case 'K' -> vendingMachine.setKnifeCount(vendingMachine.getKnifeCount() - 1);
                }
        }*/

        System.out.println( CYAN_BOLD+GREEN_UNDERLINED+"[፹] Here are your items" + RESET);
        for (Map.Entry<String,Register> map : ITEMREGISTERMAP.entrySet()){
            switch (map.getKey()) {
                case "F" :
                    if(map.getValue().getStoredValue() !=0)
                        System.out.println( CYAN_BOLD+GREEN_UNDERLINED+"[፹] [ "+map.getValue().getStoredValue() +" ] Fork(s)"  + RESET);
                    vendingMachine.setForkCount(vendingMachine.getForkCount() - map.getValue().getStoredValue());
                    break;
                case "N" :
                    if(map.getValue().getStoredValue() !=0)
                        System.out.println( CYAN_BOLD+GREEN_UNDERLINED+"[፹] [ "+map.getValue().getStoredValue() +" ] Napkin(s)"  + RESET);
                    vendingMachine.setNapkinCount(vendingMachine.getNapkinCount() - map.getValue().getStoredValue());
                    break;
                case "S" :
                    if(map.getValue().getStoredValue() !=0)
                        System.out.println( CYAN_BOLD+GREEN_UNDERLINED+"[፹] [ "+map.getValue().getStoredValue() +" ] Spoon(s)"  + RESET);
                    vendingMachine.setSpoonCount(vendingMachine.getSpoonCount() - map.getValue().getStoredValue());
                    break;
                case "K" :
                    if(map.getValue().getStoredValue() !=0)
                        System.out.println( CYAN_BOLD+GREEN_UNDERLINED+"[፹] [ "+map.getValue().getStoredValue() +" ] Knife(ves)"  + RESET);
                    vendingMachine.setKnifeCount(vendingMachine.getKnifeCount() - map.getValue().getStoredValue());
                    break;
            }
        }

        if (totalInput > totalCost){
            //dispense Change
            vendingMachine.setBalance(vendingMachine.getBalance() + totalInput);
            vendingMachine.setBalance(vendingMachine.getBalance() - (totalInput-totalCost));
            System.out.println( CYAN_BOLD+GREEN_UNDERLINED+"[፹] Here is your change: " + (totalInput-totalCost) + RESET);
        }else{
            vendingMachine.setBalance(vendingMachine.getBalance() + totalInput);
        }
        System.out.println( CYAN_BOLD+GREEN_UNDERLINED+"[፹] Thank you for your purchase " + RESET);

        System.out.println(YELLOW_BOLD + "Total Input = " + totalInput+ RESET);
        System.out.println(YELLOW_BOLD + "Total Cost = " + totalCost + RESET);
        System.out.println(YELLOW_BOLD + vendingMachine.toString() + RESET);
    }

    public void restock(){

        /*While working this doesn't use the registers, based on the requirements it seems as if we
        * have the option to do so
        * Working with the register is implemented above which shows our ability to do so*/
        System.out.println( CYAN_BOLD+GREEN_UNDERLINED+"[፹] Restocking" + RESET);

        vendingMachine.setNapkinCount(vendingMachine.getNapkinCount() + 1);
        vendingMachine.setKnifeCount(vendingMachine.getKnifeCount() + 2);
        vendingMachine.setForkCount(vendingMachine.getForkCount() + 2);
        vendingMachine.setSpoonCount(vendingMachine.getSpoonCount() + 3);

        System.out.println( CYAN_BOLD+GREEN_UNDERLINED+"[፹] Item Stock " + vendingMachine.toString() + RESET);

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
