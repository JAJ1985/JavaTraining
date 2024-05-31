package com.altaresources;

enum Level {
    LOW,
    MEDIUM,
    HIGH;
}

public class Main {

    public static void main(String[] args) {
        Level l = Level.LOW;

        switch(l) {
            case LOW:
                System.out.println("Low Level");
                break;
            case MEDIUM:
                System.out.println("Medium Level");
                break;
            case HIGH:
                System.out.println("High Level");
                break;
        }
    }
}
