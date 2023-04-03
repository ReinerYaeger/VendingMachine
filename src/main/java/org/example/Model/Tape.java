package org.example.Model;

import javax.swing.text.TabExpander;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Tape {
    public static final char BLANK = '$';
    public static final Character[] TAPEALPHABET = {'a','b','c','N','K','F','S',BLANK} ;
    private Stack <Character> leftTape;
    private Stack <Character> rightTape;
    private char head;

    static class InvalidInputException extends Exception{

        public InvalidInputException(String str){
            super(str);
        }
    }

    public Tape(Character [] tapeAlphabet, Stack<Character> leftTape, Stack<Character> rightTape, char head) {

        this.leftTape = leftTape;
        this.rightTape = rightTape;
        this.head = head;
    }

    public Tape(){

    }

    public void input(String input) {

        StringBuilder stringBuilder = new StringBuilder(input);
        input = stringBuilder.append(BLANK).toString();
        leftTape = new Stack<>();
        rightTape = new Stack<>();

        // Adding an End for the input String
        rightTape.push(BLANK);

        // Pushing the Input to the Stack
        for(int i= input.length() -1; i>=0; i-- ){
            rightTape.push(input.charAt(i));
            System.out.print(input.charAt(i));
        }
        System.out.println(
        );
        // Getting the character at the top of the string,
        // the head is over head
        head = rightTape.pop();
    }

    // Head is pointing at the top of rightTape, to move in any
    //direction we push the value in head in the opposite direction
    // and pop the store the popped value from the desired direction to
    // the head.
    //If the direction is empty, the blank or null clause is pushed
    // This ensures the head is always pointing at a valid location
    public void moveHeadLeft(){

        System.out.println("<> Tape head moving left");
        rightTape.push(head);
        if(leftTape.isEmpty()){
            leftTape.push(BLANK);
        }

        head = leftTape.pop();
        System.out.println("<> Tape head location \t[" + getHead() + "]");
    }

    public void moveHeadRight(){

        System.out.println("<> Tape head moving right");

        leftTape.push(head);
        if(leftTape.isEmpty()){
            rightTape.push(BLANK);
        }
        if (!rightTape.isEmpty()){
             head = rightTape.pop();
        }


        System.out.println("<> Tape head location \t[" + getHead() + "]");

    }

    public char getBLANK() {
        return BLANK;
    }

    public Stack<Character> getLeftTape() {
        return leftTape;
    }

    public void setLeftTape(Stack<Character> leftTape) {
        this.leftTape = leftTape;
    }

    public Stack<Character> getRightTape() {
        return rightTape;
    }

    public void setRightTape(Stack<Character> rightTape) {
        this.rightTape = rightTape;
    }

    public char getHead() {
        return head;
    }

    public void setHead(char head) {
        this.head = head;
    }
}
