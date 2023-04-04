package org.example.Model;

public class RegisterTest {

    private static final int ACOST = 5;
    private static final int BCOST = 10;
    private static final int CCOST = 20;



    static class EmptyStockException extends Exception{
        public EmptyStockException(String str){
            super(str);
        }
    }
    private double storedValue;

    public RegisterTest() {
    }

    public RegisterTest(double storedValue) {
        this.storedValue = storedValue;
    }

    /*
    * adds a value to the stored value
    * */
    public void add(double increaseBy){
        this.storedValue += storedValue;
    }

    public void subtract(double decreaseBy){
        this.storedValue -= storedValue;
    }

    /*
    *
    * */
    public boolean test(){
        return storedValue == 0 ? true:false;
    }

    public double getStoredValue() {
        return storedValue;
    }

    public void setStoredValue(double storedValue) {
        this.storedValue = storedValue;
    }



    @Override
    public String toString() {
        return "Register{" +
                "storedValue=" + storedValue +
                '}';
    }


    public static class ItemRegisterTest extends RegisterTest {

        private static int napkinStock;
        private static int knifeStock ;
        private static int forkStock;
        private static int spoonStock ;
        private static final int NAPKIN_PRICE = 10;
        private static final int KNIFE_PRICE = 25;
        private static final int FORK_PRICE = 15;
        private static final int SPOON_PRICE = 20;
        private static final int NAPKIN_CODE = 1;
        private static final int KNIFE_CODE = 2;
        private static final int FORK_CODE = 3;
        private static final int SPOON_CODE = 4;

        public ItemRegisterTest() {
            super();
            napkinStock = 20;
            knifeStock = 20;
            forkStock = 20;
            spoonStock = 20;

        }


        public static void checkStockLevels() {
            if (napkinStock < 5 || knifeStock < 5 || forkStock < 5 || spoonStock < 5) {
                System.out.println("WARNING: One or more item stocks are running low!");
                if (napkinStock < 5) {
                    System.out.println("Napkin stock: " + napkinStock);
                }
                if (knifeStock < 5) {
                    System.out.println("Knife stock: " + knifeStock);
                }
                if (forkStock < 5) {
                    System.out.println("Fork stock: " + forkStock);
                }
                if (spoonStock < 5) {
                    System.out.println("Spoon stock: " + spoonStock);
                }
            }
        }

        public double getTotalInput(String input){
            double moneyGiven = 0;

            for(char c : input.toCharArray()) {
                if (c == 'a') {
                    moneyGiven += ACOST;
                } else if (c == 'b') {
                    moneyGiven += BCOST;
                } else if (c == 'c') {
                    moneyGiven += CCOST;
                }
            }
            return  moneyGiven;
        }

        public double getCostForItems(String input){

            double itemPrice = 0;
            for(char c : input.toCharArray()){
                if (c == 'N'){
                    itemPrice += NAPKIN_PRICE;
                } else if (c == 'K') {
                    itemPrice += KNIFE_PRICE;
                } else if (c == 'F') {
                    itemPrice += FORK_PRICE;
                } else if (c == 'S') {
                    itemPrice += SPOON_PRICE;
                }
            }

            return itemPrice;
        }


        public ItemRegisterTest(double storedValue) {
            super(storedValue);
        }
    // to add item simply int itemCount = setKnife(getKnife()+incrementValue);
        public static int getNapkinStock() {
            return napkinStock;
        }

        public static void setNapkinStock(int napkinStock) {
            checkStockLevels();
            ItemRegisterTest.napkinStock = napkinStock;
        }

        public static int getKnifeStock() {
            return knifeStock;
        }

        public static void setKnifeStock(int knifeStock) {
            checkStockLevels();
            ItemRegisterTest.knifeStock = knifeStock;
        }

        public static int getForkStock() {
            return forkStock;
        }

        public static void setForkStock(int forkStock) {
            checkStockLevels();
            ItemRegisterTest.forkStock = forkStock;
        }

        public static int getSpoonStock() {
            return spoonStock;
        }

        public static void setSpoonStock(int spoonStock) {
            checkStockLevels();
            ItemRegisterTest.spoonStock = spoonStock;
        }
    }

}

