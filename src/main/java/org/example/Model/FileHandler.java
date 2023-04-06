package org.example.Model;

import java.io.*;
import java.util.Scanner;

import static org.example.Model.ConsoleColors.*;

public class FileHandler {

    FileHandler(){

    }
    public void saveToFile(VendingMachine vm){

        System.out.println(PURPLE +" [✎] Writing to vmdb ... " +RESET);
        try (PrintWriter writer = new PrintWriter(new FileWriter("vmdb.txt"))) {
            writer.println(vm.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public VendingMachine readFromFile(){
        System.out.println(PURPLE +" [✎] Reading form vmdb ... " +RESET);
        VendingMachine vm = null;
        try (Scanner scanner = new Scanner(new File("vmdb.txt"))) {
            String vmString = scanner.nextLine();
            String[] fields = vmString.split(",");
            int forkCount = Integer.parseInt(fields[0].split("=")[1]);
            int napkinCount = Integer.parseInt(fields[1].split("=")[1]);
            int spoonCount = Integer.parseInt(fields[2].split("=")[1]);
            int knifeCount = Integer.parseInt(fields[3].split("=")[1]);
            int balance = Integer.parseInt(fields[4].split("=")[1].replace("}", ""));
            vm = new VendingMachine(forkCount, napkinCount, spoonCount, knifeCount, balance);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + "vmdb.txt");
        }
        return vm;
    }

    public void logPurchase(Purchase purchase){
        System.out.println(PURPLE +" [✎] Writing to Purchase Log ... ");

        try (PrintWriter writer = new PrintWriter(new FileWriter("log.txt", true))) {
            writer.println(purchase.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public int calculateTotalPurchase(){

        System.out.println(PURPLE +" [✎] Reading from log ... " +RESET);

        int totalMoney = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("log.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                int money = Integer.parseInt(tokens[0].split("=")[1].trim());
                totalMoney += money;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return totalMoney;
    }
}
