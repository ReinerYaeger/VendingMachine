package org.example.Model;

import java.io.*;

import static org.example.Model.ConsoleColors.*;

public class FileHandler {

    FileHandler(){

    }
    public void saveToFile(VendingMachine vm){

        System.out.println(PURPLE +" [✎] Writing to vmdb ... " +RESET);
        try {
            FileOutputStream f = new FileOutputStream(new File("vmdb.txt"));
            ObjectOutputStream o = new ObjectOutputStream(f);

            // Write objects to file
            o.writeObject(vm);

            o.close();
            f.close();
        }catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        }
    }

    public void readFromFile(){
        System.out.println(PURPLE +" [✎] Reading form vmdb ... " +RESET);

        try {
            FileInputStream fi = new FileInputStream(new File("vmdb.txt"));
            final ObjectInputStream oi = new ObjectInputStream(fi);

            // Read objects
            VendingMachine vm = (VendingMachine) oi.readObject();

            oi.close();
            fi.close();
        }catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void logPurchase(Purchase purchase){
        System.out.println(PURPLE +" [✎] Writing to Purchase Log ... ");

        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("log.txt",true))) {
            objectOutputStream.writeObject(purchase);
            objectOutputStream.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public int calculateTotalPurchase(){

        System.out.println(PURPLE +" [✎] Reading from log ... " +RESET);

        /*int totalMoney = 0;

        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("log.txt"))) {
            while (true) {
               // Purchase purchase = (Purchase) objectInputStream.readObject();
               // totalMoney += purchase.getTotalMoney();
            }
        } catch (EOFException e) {
            // End of file reached
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return totalMoney;*/
        return 0;
    }
}
