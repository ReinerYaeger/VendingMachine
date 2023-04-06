package org.example.Model;

public class Register {
    private int storedValue;

    public Register(int value){
        storedValue = value;
    }

    public Register(){
        storedValue = 0;
    }

    public void add(){
        storedValue ++;
    }



    public boolean subtract(){

        try {
            checkStoredValue();
        } catch (TuringMachine.LowStockException e) {
            throw new RuntimeException(e);
        }
        if(storedValue <= 0){
            return false;
        }
        storedValue --;
        return true;
    }

    public boolean subtractMany(int values) throws TuringMachine.LowStockException {
        for (int i = 0 ; i <= values; i++)
            if (!subtract()){
                throw new TuringMachine.LowStockException("Please Contact your local Technician for a restock");
            }
        return true;
    }

    public int getStoredValue() {
        return storedValue;
    }

    public void setStoredValue(int storedValue) {
        try {
            checkStoredValue();
        } catch (TuringMachine.LowStockException e) {

        }
        this.storedValue = storedValue;
    }

    public void checkStoredValue()  throws TuringMachine.LowStockException {
        if (storedValue == 0){
            throw new TuringMachine.LowStockException("");
        }
    }
    @Override
    public String toString() {
        return "Register{" +
                "storedValue=" + storedValue +
                '}';
    }
}
