package org.example.Model;

import java.io.*;

import static org.example.Model.ConsoleColors.*;

public class FileHandler {

    FileHandler(){

    }
    public void saveToFile(VendingMachine vm){
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

        try {
            FileInputStream fi = new FileInputStream(new File("myObjects.txt"));
            ObjectInputStream oi = new ObjectInputStream(fi);

            // Read objects
            VendingMachine vm = (VendingMachine) oi.readObject();

            System.out.println(BLACK_BOLD + WHITE_BACKGROUND + "Reading From File" + vm.toString() + RESET);

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

        try(Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("log.txt",true), "UTF-8")))
        {
            writer.write(purchase.toString() +"\n");
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }

    }
}
