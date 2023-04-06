package org.example.Model;

import java.util.*;

import static org.example.Model.ConsoleColors.*;
import static org.example.Model.Tape.BLANK;


public class TuringMachine {
    private String input;

    private String startState;
    private String currentState;
    private StringBuilder sb = new StringBuilder();

    private int totalCost;
    private int totalInput;
    private final StringBuilder moneyGiven = new StringBuilder();
    private final  StringBuilder itemsRequested = new StringBuilder();
    public static VendingMachine VENDINGMACHINE = new VendingMachine();
    public static Tape TAPE = new Tape();
    public static final ArrayList <Transition> STATERULES = new ArrayList<>();
    public static Register INPUTMONEYREGISTER = new Register();
    public static Map<String,Register> ITEMREGISTERMAP = new HashMap<String,Register>();
/*    private static final Register FITEMREGISTER = new Register();
    private static final Register SITEMREGISTER = new Register();
    private static final Register KITEMREGISTER = new Register();
    private static final Register NITEMREGISTER = new Register();*/

    static class  InsufficientFundsException extends Exception{

        public InsufficientFundsException (String str){
            super(str);
        }
    }

    static  class LowStockException extends Exception{
        public LowStockException (String str){
            super(str);
        }
    }


    public TuringMachine(){
        TAPE = new Tape();
        INPUTMONEYREGISTER = new Register();
        ITEMREGISTERMAP = new HashMap<String,Register>();

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
            TAPE.input(input);
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
        startState = "q0";
        STATERULES.add(new Transition("q0","q1",'a','a',"R"));
        STATERULES.add(new Transition("q0","q1",'b','b',"R"));
        STATERULES.add(new Transition("q0","q1",'c','c',"R"));


        STATERULES.add(new Transition("q1","q1",'a','a',"R"));
        STATERULES.add(new Transition("q1","q1",'b','b',"R"));
        STATERULES.add(new Transition("q1","q1",'c','C',"R"));
        STATERULES.add(new Transition("q1","q2",'N','N',"R"));
        STATERULES.add(new Transition("q1","q2",'F','F',"R"));
        STATERULES.add(new Transition("q1","q2",'S','S',"R"));
        STATERULES.add(new Transition("q1","q2",'K','K',"R"));


        STATERULES.add(new Transition("q2","q2",'N','N',"R"));
        STATERULES.add(new Transition("q2","q2",'S','S',"R"));
        STATERULES.add(new Transition("q2","q2",'K','K',"R"));
        STATERULES.add(new Transition("q2","q2",'F','F',"R"));
        STATERULES.add(new Transition("q2","qa",BLANK,BLANK,"R"));

        //Ignore Just testing something
        STATERULES.add(new Transition("qa","qa"));


        //Restock String
        STATERULES.add(new Transition("q0","q5",'N','N',"R"));
        STATERULES.add(new Transition("q5","q6",'K','K',"R"));
        STATERULES.add(new Transition("q6","q7",'S','S',"R"));
        STATERULES.add(new Transition("q7","q8",'F','F',"R"));
        STATERULES.add(new Transition("q8","q9",'F','F',"R"));
        STATERULES.add(new Transition("q9","q10",'S','S',"R"));
        STATERULES.add(new Transition("q10","q11",'K','K',"R"));
        STATERULES.add(new Transition("q11","q12",'S','S',"R"));

        //Accept restock input
        STATERULES.add(new Transition("q12","qa2",BLANK,BLANK,"R"));
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

        for(Transition rule : STATERULES){

            if(Objects.equals(rule.getCurrentState(), currentState)){
                if (rule.getReadCharacter() == TAPE.getHead() ){
                    transition = rule;
                    sb.append(transition.getWriteCharacter());

                    System.out.println(CYAN_BOLD+ "[Σ] " + transition.toString()+"\n" + RESET);
                    TAPE.setHead(transition.getWriteCharacter());

                    /*
                    * Here we specify the movement of the Tape head left or right depending on the state
                    * */
                    if(Objects.equals(transition.getDirection(), "R")){
                        TAPE.moveHeadRight();
                    } else if (Objects.equals(transition.getDirection(), "L")) {
                        TAPE.moveHeadLeft();
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
                            currentState = transition.getNextState();
                            break;
                        case 'b':

                            INPUTMONEYREGISTER.add();
                            totalInput += new Money().getValue("b");
                            moneyGiven.append("b");
                            currentState = transition.getNextState();
                            break;
                        case 'c':

                            INPUTMONEYREGISTER.add();
                            totalInput += new Money().getValue("c");
                            moneyGiven.append("c");
                            currentState = transition.getNextState();
                            break;
                        case 'F' :
                            ITEMREGISTERMAP.get("F").add();
                            totalCost += new Money().getPrice("F");
                            itemsRequested.append("F");
                            currentState = transition.getNextState();
                            break;

                        case 'K' :
                            ITEMREGISTERMAP.get("K").add();
                            totalCost += new Money().getPrice("K");
                            itemsRequested.append("K");
                            currentState = transition.getNextState();
                            break;

                        case 'N' :
                            ITEMREGISTERMAP.get("N").add();
                            totalCost += new Money().getPrice("N");
                            itemsRequested.append("N");
                            currentState = transition.getNextState();
                            break;

                        case 'S' :
                            ITEMREGISTERMAP.get("S").add();
                            totalCost += new Money().getPrice("S");
                            itemsRequested.append("S");
                            break;
                    }

                    // If input is accepted
                    if(Objects.equals(transition.getNextState(), "qa2") & TAPE.getHead() == BLANK){
                        System.out.println( GREEN_BOLD+ "[qa2] Restock: Accept State" + RESET);

                        restock();
                        //TODO add code to restock register
                    }

                    else if(Objects.equals(transition.getNextState(), "qa")& TAPE.getHead() == BLANK){
                        System.out.println(GREEN_BOLD + "[qa] Purchase: Accept State" +  RESET);

                        System.out.println(sb.toString());

                        try {
                            purchaseItem();
                        }catch (InsufficientFundsException | LowStockException e){
                            System.err.println(e);
                        }
                        //totalItemCost()

                        //TODO add code to dispense item to use
                    }

/*                    if(Objects.equals(transition.getNextState(), "qa2") & TAPE.getHead() !=   BLANK){
                        throw new Tape.InvalidInputException("There was an error in the code");
                    } else if (Objects.equals(transition.getNextState(), "qa") & TAPE.getHead() != BLANK) {
                        throw new Tape.InvalidInputException("There was an error in the code");
                    }*/
                }
            }
        }

        return transition;
    }

    public void purchaseItem() throws InsufficientFundsException, LowStockException {

        if(totalInput<totalCost){
            throw new InsufficientFundsException("Insufficient Funds Total Input = "  + totalInput + " Total Cost = " + totalCost);
        }

        new FileHandler().logPurchase(new Purchase(totalInput,itemsRequested.toString()));

        System.out.println(new FileHandler().calculateTotalPurchase());

        /*
        *  Subtracting the items from the stock
        * */


        /*
        * Save the Result to the File
        * */

        System.out.println( CYAN_BOLD+GREEN_UNDERLINED+"[፹] Here are your items" + RESET);
        for (Map.Entry<String,Register> item : ITEMREGISTERMAP.entrySet()){
            switch (item.getKey()) {
                case "F" -> {
                        int items = item.getValue().getStoredValue();
                        VENDINGMACHINE.setForkCount(VENDINGMACHINE.getForkCount() - item.getValue().getStoredValue());
                        while(item.getValue().getStoredValue()>0) {
                            item.getValue().subtract();
                        }
                        System.out.println(CYAN_BOLD + GREEN_UNDERLINED + "[፹] [ " + items + " ] Fork(s)" + RESET);
                    if (VENDINGMACHINE.getSpoonCount() == 0){
                        throw new LowStockException("Please Contact your local Technician for a restock");
                    }
                }
                case "N" -> {
                        int items = item.getValue().getStoredValue();
                        VENDINGMACHINE.setNapkinCount(VENDINGMACHINE.getNapkinCount() - item.getValue().getStoredValue());
                        while(item.getValue().getStoredValue()>0) {
                            item.getValue().subtract();
                        }
                        System.out.println(CYAN_BOLD + GREEN_UNDERLINED + "[፹] [ " + items + " ] Napkin(s)" + RESET);
                    if (VENDINGMACHINE.getSpoonCount() == 0){
                        throw new LowStockException("Please Contact your local Technician for a restock");
                    }
                }
                case "S" -> {

                        int items = item.getValue().getStoredValue();
                        VENDINGMACHINE.setSpoonCount(VENDINGMACHINE.getSpoonCount() - item.getValue().getStoredValue());
                        while(item.getValue().getStoredValue()>0) {
                            item.getValue().subtract();
                        }
                        System.out.println(CYAN_BOLD + GREEN_UNDERLINED + "[፹] [ " + items + " ] Spoon(s)" + RESET);
                     if (VENDINGMACHINE.getSpoonCount() == 0){
                        throw new LowStockException("Please Contact your local Technician for a restock");
                    }
                }
                case "K" -> {
                        int items = item.getValue().getStoredValue();
                        VENDINGMACHINE.setKnifeCount(VENDINGMACHINE.getKnifeCount() - item.getValue().getStoredValue());
                        while(item.getValue().getStoredValue()>0) {
                            item.getValue().subtract();
                        }
                        System.out.println(CYAN_BOLD + GREEN_UNDERLINED + "[፹] [ " + items + " ] Knife(ves)" + RESET);
                    if (VENDINGMACHINE.getSpoonCount() == 0){
                        throw new LowStockException("Please Contact your local Technician for a restock");
                    }
                }
            }
        }

        if (totalInput > totalCost){
            //dispense Change
            VENDINGMACHINE.setBalance(VENDINGMACHINE.getBalance() + totalInput);
            VENDINGMACHINE.setBalance(VENDINGMACHINE.getBalance() - (totalInput-totalCost));
            System.out.println( CYAN_BOLD+GREEN_UNDERLINED+"[፹] Here is your change: " + (totalInput-totalCost) + RESET);
        }else{
            VENDINGMACHINE.setBalance(VENDINGMACHINE.getBalance() + totalInput);
        }
        System.out.println( CYAN_BOLD+GREEN_UNDERLINED+"[፹] Thank you for your purchase " + RESET);

        System.out.println(YELLOW_BOLD + "Total Input = " + totalInput+ RESET);
        System.out.println(YELLOW_BOLD + "Total Cost = " + totalCost + RESET);
        System.out.println(YELLOW_BOLD + VENDINGMACHINE.toString() + RESET);

        new FileHandler().saveToFile(VENDINGMACHINE);
    }

    public void restock(){

        /*While working this doesn't use the registers, based on the requirements we have the creative freedom in this matter,
        * Register is implemented above which shows our ability to do so*/
        System.out.println( CYAN_BOLD+GREEN_UNDERLINED+"[፹] Restocking" + RESET);

        VENDINGMACHINE.setNapkinCount(VENDINGMACHINE.getNapkinCount() + 1);
        VENDINGMACHINE.setKnifeCount(VENDINGMACHINE.getKnifeCount() + 2);
        VENDINGMACHINE.setForkCount(VENDINGMACHINE.getForkCount() + 2);
        VENDINGMACHINE.setSpoonCount(VENDINGMACHINE.getSpoonCount() + 3);

        System.out.println( CYAN_BOLD+GREEN_UNDERLINED+"[፹] Item Stock " + VENDINGMACHINE.toString() + RESET);

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
        STATERULES.add(transition);
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public ArrayList<Transition> getStateRules() {
        return STATERULES;
    }


    @Override
    public String toString() {
        return "TuringMachine{" +
                "input='" + input + '\'' +
                ", STATERULES=" + STATERULES +
                ", startState=" + startState +
                ", currentState=" + currentState +
                '}';
    }
}
