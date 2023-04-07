package org.example.Model;

public class Money {
    private static final int NAPKIN_PRICE = 10;
    private static final int KNIFE_PRICE = 25;
    private static final int FORK_PRICE = 15;
    private static final int SPOON_PRICE = 20;
    private static final int APRICE = 5;
    private static final int BPRICE = 10;
    private static final int CPRICE = 20;



    public Money() {
    }

    public int getPrice(String item){

        return switch (item) {
            case "K" -> KNIFE_PRICE;
            case "F" -> FORK_PRICE;
            case "S" -> SPOON_PRICE;
            case "N" -> NAPKIN_PRICE;
            default -> 0;
        };
    }

    public int getValue(String money){
        return switch (money) {
            case "a" -> APRICE;
            case "b" -> BPRICE;
            case "c" -> CPRICE;
            default -> 0;
        };
    }






}
