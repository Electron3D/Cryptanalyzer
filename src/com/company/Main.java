package com.company;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("GUI started");
        } else {
            Cli.start(args);
        }
    }
}