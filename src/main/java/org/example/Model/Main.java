package org.example.Model;

import java.util.Objects;
import java.util.Scanner;

import static org.example.Model.ConsoleColors.*;
import static org.example.Model.TuringMachine.VENDINGMACHINE;

public class Main {
    public static void main(String[] args) {
        TuringMachine tm;
        while(true) {
            tm = new TuringMachine();
            Scanner scanner = new Scanner(System.in);

            System.out.println(GREEN_UNDERLINED + "[፹] Acceptable Currency" + RESET);
            System.out.println(GREEN_UNDERLINED + "[፹] [a] $5" + RESET);
            System.out.println(GREEN_UNDERLINED + "[፹] [b] $10" + RESET);
            System.out.println(GREEN_UNDERLINED + "[፹] [c] $20" + RESET);

            System.out.println();

            System.out.println(GREEN_UNDERLINED + "[፹] We Items Available" + RESET);
            System.out.println(GREEN_UNDERLINED + "[፹] [K] Knives \t = Cost: $" + new Money().getPrice("K") + " \tQuantity: " + VENDINGMACHINE.getKnifeCount() + RESET);
            System.out.println(GREEN_UNDERLINED + "[፹] [F] Forks  \t = Cost: $" + new Money().getPrice("F") + " \tQuantity: " + VENDINGMACHINE.getForkCount() + RESET);
            System.out.println(GREEN_UNDERLINED + "[፹] [S] Spoons \t = Cost: $" + new Money().getPrice("S") + " \tQuantity: " + VENDINGMACHINE.getSpoonCount() + RESET);
            System.out.println(GREEN_UNDERLINED + "[፹] [N] Napkin \t = Cost: $" +new Money().getPrice("N")+" \tQuantity: " + VENDINGMACHINE.getNapkinCount() + RESET);
            System.out.println(GREEN_UNDERLINED + "[፹] Please Select what you would like eg, ccccNNFFK" + RESET);

            System.out.println();
            String userInput = scanner.nextLine();
            tm = new TuringMachine(userInput);
            System.out.println("Press enter to continue or type test");
            userInput = scanner.nextLine();
            if(Objects.equals(userInput, "test"))
                test();

            System.out.flush();
        }
    }

    public static void test(){
        TuringMachine tm;
        tm = new TuringMachine("aabcFNF");
        tm = new TuringMachine("cbaSK");
        tm = new TuringMachine("dNKF");
        tm = new TuringMachine("cN");
        tm = new TuringMachine("ccccccNNKFNNF");
        tm = new TuringMachine("NKSFFSKS");
    }
}