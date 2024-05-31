package com.altaresources;

import jdk.swing.interop.SwingInterOpUtils;

public class Main {

    public static void main(String[] args) {
        /*
            byte - holds a range from -128 to 127 will probably never use
            short - rarely used  if at  all
            int - most common
            long - used from time to time
            float - rarely used  if at  all
            double - most common
            char - used from time to time
            boolean - most common
            The string is a datatype in Java, which is not a primitive datatype. It's actually a Class, but it enjoys a bit of favoritism in Java to make it easier to use than a regular class.
            A string is a sequence of characters. In the case of the char which can only contain a singular regular or unicode character, while a string contains a large number of characters. Technically it's limited by memory (or heap space in your computer) or the MAX_VALUE of an int which is 2.14 Billion.
        */
        String myString = "This is a string";
        System.out.println("myString is equal to " + myString);
        myString = myString + ", and this more.";
        System.out.println("myString is equal to " + myString);
        myString = myString + "\u00A9 2022";
        System.out.println("myString is equal to " + myString);
        String numberString = "250.55";
        numberString = numberString + "49.95";
        System.out.println(numberString);
        String lastString = "10";
        int myInt = 50;
        lastString = lastString + myInt;
        System.out.println("LastString is equal to " + lastString);
        double doubleNumber = 120.47d;
        lastString = lastString + doubleNumber;
        System.out.println("LastString is equal to " + lastString);
    }
}
