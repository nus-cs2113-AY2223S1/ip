package main.java;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Duke {

    private static String[][] list;
    private static int listIndex;
    private static Scanner scanner;
    private static String INDENT = "\n    ";
    private static String H_LINE = INDENT + "-------------------------------------------";


    public static void main(String[] args) {

        //Initialize necessary variables
        init();

        //Print introduction to Duke
        introduction();

        //Re-iterate what the user types, store in list, and unmark / mark

        while (!respondToUser()) {
        }

        //If the user exits, salute them goodbye
        goodbye();

    }

    private static void init() {
        scanner = new Scanner(System.in);
        list = new String[100][2];
        listIndex = 0;
    }

    private static void introduction() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String introText = "\n    Hello! I'm Duke\n    What can I do for you?";
        String introduction = logo + H_LINE + introText + H_LINE + "\n";
        System.out.println(introduction);
    }


    private static boolean respondToUser() {
        String input = scanner.nextLine();
        String inputType = input.toLowerCase();
        boolean shouldExit = false;

        //Based on what the user types, either exit, list, mark, unmark, or add to list
        switch (inputType) {
            case "bye":
                shouldExit = true;
                break;
            case "list":
                System.out.print(H_LINE);
                printList();
                System.out.println(H_LINE + "\n");
                break;
            default:

                //create patterns for checking both mark and unmark
                Pattern markPattern = Pattern.compile("^mark[ ]*[0-9]+[ ]*", Pattern.CASE_INSENSITIVE);
                boolean matchesMark = markPattern.matcher(input).find();
                Pattern unmarkPattern = Pattern.compile("^unmark[ ]*[0-9]+[ ]*", Pattern.CASE_INSENSITIVE);
                boolean matchesUnmark = unmarkPattern.matcher(input).find();

                //if the item is to be marked or unmarked, follow the correct steps to extract the index
                if (matchesMark || matchesUnmark) {
                    String type = matchesMark ? "Mark" : "Unmark";
                    System.out.print(H_LINE + INDENT + type + "ing...");
                    int markIndex = matchesMark ? 4 : 6;
                    String number = input.substring(markIndex).replaceAll(" ", "");
                    int index = Integer.valueOf(number) - 1;
                    if (index >= listIndex) {
                        System.out.print(INDENT + "Trying to " + type + " an item outside of list length? Failed.");
                    } else if (index < 0) {
                        System.out.print(INDENT + "Trying to " + type + " an item that is too small? Failed.");
                    } else {
                        switch (type) {
                            case "Mark":
                                list[index][1] = "X";
                                break;
                            default:
                                list[index][1] = " ";
                        }
                        System.out.print(INDENT + "Success! Printing your updated list:");
                        printList();
                    }

                    System.out.println(H_LINE + "\n");

                } else {

                    //Add a new item to the list
                    System.out.println(H_LINE + INDENT + "added: " + input + H_LINE + "\n");
                    String[] newItem = {input, " "};
                    list[listIndex] = newItem;
                    listIndex++;
                }


        }
        return shouldExit;
    }

    private static void printList() {
        if (listIndex == 0) {
            System.out.print(INDENT + "Nothing to see here! Type to add to your list.");
            return;
        }
        for (int i = 0; i < listIndex; i++) {
            String item = list[i][0];
            String checked = list[i][1];
            System.out.print(INDENT + (i + 1) + ".[" + checked + "] " + item);
        }
    }

    private static void goodbye() {
        String goodbyeText = "\n    Bye. Hope to see you again soon!";
        String goodbye = H_LINE + goodbyeText + H_LINE;
        System.out.println(goodbye);
    }


}
