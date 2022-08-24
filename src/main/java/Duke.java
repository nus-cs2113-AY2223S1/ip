package main.java;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {

    private static String[][] list;
    private static int listNum = 0;

    public static void main(String[] args) {

        //Initialize necessary variables
        init();

        //Print introduction to Duke
        introduction();

        //Re-iterate what the user types

        list = new String[100][2];
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
        String inputType = input.toLowerCase();
        boolean exit = false;
        switch (inputType) {
            case "bye":
                exit = true;
                break;
            case "list":
                System.out.print(hLine);
                printList();
                System.out.println(hLine + "\n");
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
                    System.out.print(hLine + indent + type + "ing...");
                    int markIndex = matchesMark ? 4 : 6;
                    String number = input.substring(markIndex).replaceAll(" ", "");
                    int index = Integer.valueOf(number) - 1;
                    if (index >= listNum) {
                        System.out.print(indent + "Trying to " + type + " an item outside of list length? Failed.");
                    } else if (index < 0) {
                        System.out.print(indent + "Trying to " + type + " an item that is too small? Failed.");
                    } else {
                        switch (type) {
                        case "Mark":
                            list[index][1] = "X";
                            break;
                        default:
                             list[index][1] = " ";
                        }
                        System.out.print(indent + "Success! Printing your updated list:");
                        printList();
                    }

                    System.out.println(hLine + "\n");

                } else {
                    System.out.println(hLine + indent + "added: " + input + hLine + "\n");
                    String[] newItem = {input, " "};
                    list[listNum] = newItem;
                    listNum++;
                }


        }
        return exit;
    }

    private static void printList() {
        if (listNum == 0) {
            System.out.print(indent + "Nothing to see here! Type to add to your list.");
            return;
        }
        for (int i = 0; i < listNum; i++) {
            String item = list[i][0];
            String checked = list[i][1];
            System.out.print(indent + (i + 1) + ".[" + checked + "] " + item);
        }
    }

    private static void goodbye() {
        String goodbyeText = "\n    Bye. Hope to see you again soon!";
        String goodbye = hLine + goodbyeText + hLine;
        System.out.println(goodbye);
    }

    private static Scanner scanner;
    private static String indent = "\n    ";
    private static String hLine = indent + "-------------------------------------------";
}
