package org.example.Model;

public class Money {
    private static final int NAPKIN_PRICE = 10;
    private static final int KNIFE_PRICE = 25;
    private static final int FORK_PRICE = 15;
    private static final int SPOON_PRICE = 20;

    public Money() {
    }

    public int getPrice(String item){

        if(item.equals("K")){
            return KNIFE_PRICE;
        } else if (item.equals("F")) {
            return FORK_PRICE;
        } else if (item.equals("S")) {
            return SPOON_PRICE;
        } else if (item.equals("N")) {
            return NAPKIN_PRICE;
        }
        return 0;
    }


}
