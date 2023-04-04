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
        if(storedValue <= 0){
            return false;
        }
        storedValue --;
        return true;
    }

    public int getStoredValue() {
        return storedValue;
    }

    public void setStoredValue(int storedValue) {
        this.storedValue = storedValue;
    }

    @Override
    public String toString() {
        return "Register{" +
                "storedValue=" + storedValue +
                '}';
    }
}
