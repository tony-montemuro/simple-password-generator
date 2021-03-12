package com.mycompany.password_generator;

import java.util.Scanner;

enum Type {
    LOWER, UPPER, NUMBER
}

public class Main {
    //Constants used in random character generation
    private static final int NUM_NUMS = 10;
    private static final int NUM_CHARS = 26;
    private static final int NUM_SPEC = 94;
    private static final int FIRST_INDEX_SPEC = 33;
    private static final int FIRST_INDEX_LOWER = 97;
    private static final int FIRST_INDEX_UPPER = 65;
    private static final int FIRST_INDEX_NUMBER = 48;
    
    //Main
    public static void main(String[] args) {
        //Introduction
        
        System.out.println("Welcome to the Password Generator!");
        
        //Declare variables
        char decision;
        
        //User can create as many passwords as they would like
        do {
            //Initialize user input -- determines characteristics of password
            //Platform
            Scanner in = new Scanner(System.in);
            System.out.println("Enter platform: ");
            String platform = in.nextLine();
            
            //Number of Characters
            System.out.println("Enter number of characters: ");
            int numChars = in.nextInt();
            
            //Has special characters
            System.out.println("Special characters? (Y/N): ");
            char specialDecision = readChar(in.next());
            boolean hasSpecial = true;
            if (Character.toUpperCase(specialDecision) != 'Y') {
                hasSpecial = false;
            }
            
            //Generate and log password to console
            System.out.println(platform + ": " + generatePassword(numChars, hasSpecial));
            
            //Ask user if they would like to generate a new password
            System.out.println("Would you like to generate another password?");
            decision = readChar(in.next());            
        } while (Character.toUpperCase(decision) == 'Y');
    }
    
    //Methods
    /**
     * Returns the first character from a string - simulating the effect of
     * reading a character from the input stream.
     * 
     * @param str Some string; str.length() > 0
     * @return first character of the string
     */
    public static char readChar(String str) {
        //Return first character of str
        char chArr[] = str.toCharArray();
        return chArr[0];
    }
    
    /**
     * This function generates a random password.
     * 
     * @param length is a positive integer; determines the length of password
     * @param hasSpecial is a boolean type, determines whether or not to include
     * special characters in the password or not
     * @return newPassword, which is a randomized password
     */
    public static String generatePassword(int length, boolean hasSpecial) {
        //Declare new variable
        String newPassword = "";
        
        //Generate new password using generateRandomChar method for each char
        for (int i = 0; i < length; i++) {
            newPassword += generateRandomChar(hasSpecial);
        }
        
        //Return new password
        return newPassword;
    }
    
    /**
     * This function hashes an int value.
     * 
     * @param n is a random number between 1 and 3.
     * @return A value of type Type.
     */
    public static Type hash(int n) {
        //Converts int value into a Type value
        switch (n) {
            case 1:
                return Type.LOWER;
            case 2:
                return Type.UPPER;
            case 3:
                return Type.NUMBER;
            default:
                return Type.LOWER;
        }
    }
    
    /**
     * This function generates and returns a random character based on the
     * hasSpecial parameter.
     * 
     * @param hasSpecial determines whether or not the character can be a
     * special character.
     * @return a randomized character.
     */
    public static char generateRandomChar(boolean hasSpecial) {
        //If hasSpeicla is true, simply return a char that is a number,
        //letter, or special char
        char c;
        if (hasSpecial) {
            c = (char) (NUM_SPEC * Math.random() + FIRST_INDEX_SPEC);
        }
        //Otherwise, only return a char that is a number or a letter
        else {
            //This system will return lowercase letter 33.33% of time,
            //uppercase letter 33.33% of the time, and number 33.33% of time
            int n = (int) (3 * Math.random() + 1);
            switch (hash(n)) {
                case LOWER:
                    c = (char) (NUM_CHARS * Math.random() + FIRST_INDEX_LOWER);
                    break;
                case UPPER:
                    c = (char) (NUM_CHARS * Math.random() + FIRST_INDEX_UPPER);
                    break;
                case NUMBER:
                    c = (char) (NUM_NUMS * Math.random() + FIRST_INDEX_NUMBER);
                    break;
                default:
                    c = '0';
            }
        }
        return c;
    }
}