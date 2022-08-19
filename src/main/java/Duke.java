package main.java;

import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        //Initialize necessary variables
        init();

        //Print introduction to Duke
        introduction();

        //Re-iterate what the user types

        while (!respondToUser()) {
        }

        goodbye();

    }

    private static void init() {
        scanner = new Scanner(System.in);
    }

    private static void introduction() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String introText = "\n    Hello! I'm Duke\n    What can I do for you?";
        String introduction = logo + hLine + introText + hLine + "\n";
        System.out.println(introduction);
    }


    private static boolean respondToUser() {
        String input = scanner.next();
        boolean exit = input.toLowerCase().equals("bye");
        if (!exit) {
            System.out.println(hLine + indent + input + hLine + "\n");
        }
        return exit;
    }

    private static void goodbye() {
        String goodbyeText = "\n    Bye. Hope to see you again soon!";
        String goodbye = hLine + goodbyeText + hLine;
        System.out.println(goodbye);
    }

    private static Scanner scanner;
    private static String indent = "\n    ";
    private static String hLine = indent + "-----------------------------------";
}
