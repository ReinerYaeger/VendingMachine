package org.example.Model;

public class Transition {
    private String currentState;
    private String nextState;
    private char readCharacter;
    private char writeCharacter;
    private String direction;

    public Transition(String currentState, String nextState, char readCharacter, char writeCharacter, String direction) {
        this.currentState = currentState;
        this.nextState = nextState;
        this.readCharacter = readCharacter;
        this.writeCharacter = writeCharacter;
        this.direction = direction;
    }

    public Transition (String currentState, String nextState){
        this.currentState = currentState;
        this.nextState = nextState;
    }

    public String getCurrentState() {
        return currentState;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }

    public String getNextState() {
        return nextState;
    }

    public void setNextState(String nextState) {
        this.nextState = nextState;
    }

    public char getReadCharacter() {
        return readCharacter;
    }

    public void setReadCharacter(char readCharacter) {
        this.readCharacter = readCharacter;
    }

    public char getWriteCharacter() {
        return writeCharacter;
    }

    public void setWriteCharacter(char writeCharacter) {
        this.writeCharacter = writeCharacter;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }


    @Override
    public String toString() {
        return "Transition{" +
                "currentState='" + currentState + '\'' +
                ", nextState='" + nextState + '\'' +
                ", readCharacter=" + readCharacter +
                ", writeCharacter=" + writeCharacter +
                ", direction='" + direction + '\'' +
                '}';
    }
}
