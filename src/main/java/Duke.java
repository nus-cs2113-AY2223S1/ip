package main.java;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static ArrayList<String> list;

    public static void main(String[] args) {

        //Initialize necessary variables
        init();

        //Print introduction to Duke
        introduction();

        //Re-iterate what the user types

        list = new ArrayList<String>();
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
        String input = scanner.nextLine();
        boolean exit = input.toLowerCase().equals("bye");
        boolean returnList = input.toLowerCase().equals("list");
        if (!exit) {
            if (returnList) {
                System.out.print(hLine);
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(indent + (i + 1) + ". " + list.get(i));
                }
                System.out.println(hLine + "\n");
            } else {
                System.out.println(hLine + indent + "added: " + input + hLine + "\n");
                list.add(input);
            }
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
