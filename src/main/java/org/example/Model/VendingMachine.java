package org.example.Model;
import java.io.*;

public final class VendingMachine implements Serializable {

    private int forkCount;
    private int napkinCount;
    private int spoonCount;
    private int knifeCount;
    private int balance;

    /*//TODO
    * The vending machine class should read from the file to determine the amount of items and money there area in the registers
    * */

    public boolean isVMDBFileExists() {
        File file = new File("vmdb.txt");
        return file.exists();
    }
    public VendingMachine(){

        if(!isVMDBFileExists()) {
            forkCount = 20;
            napkinCount = 20;
            spoonCount = 20;
            knifeCount = 20;
            balance = 1000;
        }else{
            forkCount = new FileHandler().readFromFile().getForkCount();
            napkinCount = new FileHandler().readFromFile().getNapkinCount();
            spoonCount = new FileHandler().readFromFile().getSpoonCount();
            knifeCount = new FileHandler().readFromFile().getKnifeCount();
            balance = new FileHandler().calculateTotalPurchase();
        }
    }

    public VendingMachine(int forkCount, int napkinCount, int spoonCount, int knifeCount, int balance) {
        this.forkCount = forkCount;
        this.napkinCount = napkinCount;
        this.spoonCount = spoonCount;
        this.knifeCount = knifeCount;
        this.balance = balance;
    }

    public int getForkCount() {
        return forkCount;
    }

    public void setForkCount(int forkCount) {
        this.forkCount = forkCount;
    }

    public int getNapkinCount() {
        return napkinCount;
    }

    public void setNapkinCount(int napkinCount) {
        this.napkinCount = napkinCount;
    }

    public int getSpoonCount() {
        return spoonCount;
    }

    public void setSpoonCount(int spoonCount) {
        this.spoonCount = spoonCount;
    }

    public int getKnifeCount() {
        return knifeCount;
    }

    public void setKnifeCount(int knifeCount) {
        this.knifeCount = knifeCount;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "VendingMachine{" +
                "forkCount=" + forkCount +
                ", napkinCount=" + napkinCount +
                ", spoonCount=" + spoonCount +
                ", knifeCount=" + knifeCount +
                ", balance=" + balance +
                '}';
    }
}
