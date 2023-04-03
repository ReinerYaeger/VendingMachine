package org.example.Model;

import java.util.Scanner;

public class VendingMachine {
    //Variables to hold the stock of each item, the total money earned and the price of each item
    private static int napkinStock = 20;
    private static int knifeStock = 20;
    private static int forkStock = 20;
    private static int spoonStock = 20;
    private static int totalMoney = 0;
    private static final int NAPKIN_PRICE = 10;
    private static final int KNIFE_PRICE = 25;
    private static final int FORK_PRICE = 15;
    private static final int SPOON_PRICE = 20;
    private static final int NAPKIN_CODE = 1;
    private static final int KNIFE_CODE = 2;
    private static final int FORK_CODE = 3;
    private static final int SPOON_CODE = 4;

    public static void main(String[] args) {

        //create a scanner object for user input
        Scanner scanner = new Scanner(System.in);
        while (true) {
            //ask the user to enter the amount of money they are inserting
            System.out.println("Enter amount of money (α = $5, β = $10, γ = $20): ");
            String moneyInput = scanner.nextLine();

            //check if the user entered "quit" to exit the program
            if (moneyInput.equalsIgnoreCase("quit")) {
                break;
            }

            //convert the user input to the total amount of money inserted
            int money = 0;
            for (int i = 0; i < moneyInput.length(); i++) {
                char c = moneyInput.charAt(i);
                if (c == 'α') {
                    money += 5;
                } else if (c == 'β') {
                    money += 10;
                } else if (c == 'γ') {
                    money += 20;
                } else {
                    System.out.println("Invalid coin symbol. Please try again.");
                    break;
                }
            }
            System.out.println("Enter item code (1 for napkin, 2 for knife, 3 for fork, 4 for spoon): ");
            int itemCode = scanner.nextInt();
            scanner.nextLine();
            if (itemCode < NAPKIN_CODE || itemCode > SPOON_CODE) {
                System.out.println("Invalid item code. Please try again.");
                continue;
            }

            //get the price and stock of the selected item based on the item code
            int itemPrice = 0;
            int itemStock = 0;
            switch (itemCode) {
                case NAPKIN_CODE:
                    itemPrice = NAPKIN_PRICE;
                    itemStock = napkinStock;
                    break;
                case KNIFE_CODE:
                    itemPrice = KNIFE_PRICE;
                    itemStock = knifeStock;
                    break;
                case FORK_CODE:
                    itemPrice = FORK_PRICE;
                    itemStock = forkStock;
                    break;
                case SPOON_CODE:
                    itemPrice = SPOON_PRICE;
                    itemStock = spoonStock;
                    break;
            }

            //check if the user inserted enough money and if the selected item is in stock
            if (money < itemPrice) {
                System.out.println("Insufficient funds. Please insert more money.");
            } else if (itemStock == 0) {
                System.out.println("Item out of stock. Please choose another item.");
            } else {
                // dispense item and calculate change
                int change = money - itemPrice;
                switch (itemCode) {
                    case NAPKIN_CODE:
                        napkinStock--;
                        break;
                    case KNIFE_CODE:
                        knifeStock--;
                        break;
                    case FORK_CODE:
                        forkStock--;
                        break;
                    case SPOON_CODE:
                        spoonStock--;
                        break;
                }
                totalMoney += itemPrice;
                System.out.println("Dispensing item. Your change is: " + change);
            }
        }
        System.out.println("Total money earned: " + totalMoney);
        scanner.close();
    }
}
