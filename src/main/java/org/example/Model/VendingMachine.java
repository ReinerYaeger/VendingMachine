package org.example.Model;
import java.io.*;

public class VendingMachine implements Serializable {

    private int forkCount;
    private int napkinCount;
    private int spoonCount;
    private int knifeCount;
    private int balance;

    public VendingMachine(){
        forkCount = 20;
        napkinCount = 20;
        spoonCount = 20;
        knifeCount = 20;
        balance = 99999;
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
