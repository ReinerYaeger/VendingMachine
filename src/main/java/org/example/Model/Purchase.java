package org.example.Model;

import java.text.SimpleDateFormat;
import java.util.Date;

class Purchase {
    private int totalMoney;
    private String itemsPurchased;
    private Date timeStamp;
    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    Purchase(int totalMoney, String itemsPurchased){
        this.totalMoney = totalMoney;
        this.itemsPurchased = itemsPurchased;
        timeStamp = new Date();
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
