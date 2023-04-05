package org.example.Model;

import java.util.Scanner;

import static org.example.Model.ConsoleColors.*;

public class Main {
    public static void main(String[] args) {
        TuringMachine tm;
        tm= new TuringMachine();
        Scanner scanner = new Scanner(System.in);

        System.out.println(GREEN_UNDERLINED+"[፹] Acceptable Currency"+ RESET);
        System.out.println(GREEN_UNDERLINED+"[፹] [a] $5"+ RESET);
        System.out.println(GREEN_UNDERLINED+"[፹] [b] $10"+ RESET);
        System.out.println(GREEN_UNDERLINED+"[፹] [c] $20"+ RESET);

        System.out.println();

        System.out.println(GREEN_UNDERLINED+"[፹] We Items Available"+ RESET);
        System.out.println(GREEN_UNDERLINED+"[፹] [K] Knives: "+ TuringMachine.VENDINGMACHINE.getKnifeCount() + RESET);
        System.out.println(GREEN_UNDERLINED+"[፹] [F] Forks:  "+TuringMachine.VENDINGMACHINE.getForkCount() + RESET);
        System.out.println(GREEN_UNDERLINED+"[፹] [S] Spoons: "+TuringMachine.VENDINGMACHINE.getSpoonCount() + RESET);
        System.out.println(GREEN_UNDERLINED+"[፹] [N] Napkin: "+TuringMachine.VENDINGMACHINE.getNapkinCount() + RESET);
        System.out.println(GREEN_UNDERLINED+"[፹] Please Select what you would like eg, ccccNNFFK"+ RESET);

        System.out.println();

        String userInput = scanner.nextLine();

        tm = new TuringMachine(userInput);
        System.out.println("Press Enter:");
        scanner.nextLine();

        tm = new TuringMachine("aabcFNF");
        tm = new TuringMachine("cbaSK");
        tm = new TuringMachine("dNKF");
        tm = new TuringMachine("cN");

        tm = new TuringMachine("ccccccNNKFNNF");

        tm = new TuringMachine("NKSFFSKS");

    }
}