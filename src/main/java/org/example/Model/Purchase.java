package org.example.Model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

class Purchase implements Serializable{
    private int totalMoney;
    private String itemsPurchased;
    private Date timeStamp;
    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    Purchase(int totalMoney, String itemsPurchased){
        this.totalMoney = totalMoney;
        this.itemsPurchased = itemsPurchased;
        timeStamp = new Date();
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getItemsPurchased() {
        return itemsPurchased;
    }

    public void setItemsPurchased(String itemsPurchased) {
        this.itemsPurchased = itemsPurchased;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "totalMoney= " + totalMoney +
                ", itemsPurchased=' " + itemsPurchased + '\'' +
                ", timeStamp= " + formatter.format(timeStamp) +
                '}';
    }
}
