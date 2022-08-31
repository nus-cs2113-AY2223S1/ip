package main.java;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Duke {

    /* Variables for Duke's use */
    private static Task[] taskList;
    private static int listIndex;
    private static Scanner scanner;

    /* Constants */
    private static String INDENT = "\n    ";
    private static String H_LINE = INDENT +
            "------------------------------------------------";


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

    /* Initialize variables for Duke's use */
    private static void init() {
        scanner = new Scanner(System.in);
        taskList = new Task[100];
        listIndex = 0;
    }

    /* Print introduction to Duke */
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

    /* Determine whether the user wants to create a task, exit, list, or mark */
    private static boolean respondToUser() {
        String input = scanner.nextLine();
        String inputType = input.toLowerCase();
        boolean shouldExit = false;

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
                markOrUnmark(matchesMark, input);

                //Otherwise, create a new task
            } else {
                Task task = createTask(input);
                if (task == null) {
                    System.out.println(H_LINE + INDENT
                            + "Looks like there was an issue with adding this task: "
                            + input + INDENT + "Please try again!" + H_LINE + "\n");
                }
                System.out.println(H_LINE + INDENT
                        + "Success!" + INDENT + "Added: " + (listIndex + 1) + ". " + task + H_LINE + "\n");
                taskList[listIndex] = task;
                listIndex++;
            }
        }
        return shouldExit;
    }

    private static Task createTask(String input) {
        Task task;
        if (input.startsWith("deadline")) {
            task = createDeadline(input);
            if (task == null) {
                System.out.println(INDENT
                        + "Was unable to create a Deadline! Please specify a date after writing /by.");
            }
        } else if (input.startsWith("event")) {
            task = createEvent(input);
            if (task == null) {
                System.out.println(INDENT
                        + "Was unable to create an Event! Please specify a date after writing /at.");
            }
        } else {
            task = new Task(input);
        }
        return task;
    }

    /* Create a new Deadline with the correct due date */
    private static Task createDeadline(String input) {
        int dateIndex = input.indexOf("/by");
        if (dateIndex == -1) {
            return null;
        }
        int startIndex = findNextLetter("deadline", input);
        int endIndex = dateIndex + findNextLetter("/by", input.substring(dateIndex));
        return new Deadline(input.substring(startIndex, dateIndex), input.substring(endIndex));
    }

    /* Create a new Event with the correct date */
    private static Task createEvent(String input) {
        int dateIndex = input.indexOf("/at");
        if (dateIndex == -1) {
            return null;
        }
        int startIndex = findNextLetter("event", input);
        int endIndex = dateIndex + findNextLetter("/at", input.substring(dateIndex));
        return new Event(input.substring(startIndex, dateIndex), input.substring(endIndex));
    }

    private static int findNextLetter(String word, String input) {
        int index = word.length();
        while (input.charAt(index) == ' ') {
            index++;
        }
        return index;
    }

    /* Either mark or unmark a task */
    private static void markOrUnmark(boolean toMark, String input) {
        String type = toMark ? "Mark" : "Unmark";
        System.out.print(H_LINE + INDENT + type + "ing...");
        int markIndex = toMark ? 4 : 6;
        String number = input.substring(markIndex).replaceAll(" ", "");
        int index = Integer.valueOf(number) - 1;
        if (index >= listIndex) {
            System.out.print(INDENT + "Trying to " + type + " an item outside of list length? Failed.");
        } else if (index < 0) {
            System.out.print(INDENT + "Trying to " + type + " an item that is too small? Failed.");
        } else {
            if (toMark) {
                taskList[index].mark();
            } else {
                taskList[index].unmark();
            }
            System.out.print(INDENT + "Success!");
            printList();
        }
        System.out.println(H_LINE + "\n");
    }

    /* Print a list of the current tasks */
    private static void printList() {
        if (listIndex == 0) {
            System.out.print(INDENT + "Nothing to see here! Type to add to your list.");
            return;
        }
        System.out.print(INDENT + "Here's your current task list:");
        for (int i = 0; i < listIndex; i++) {
            Task task = taskList[i];
            System.out.print(INDENT + (i + 1) + "." + task);
        }
    }

    /* Print a goodbye message from the Duke */
    private static void goodbye() {
        String goodbyeText = "\n    Bye. Hope to see you again soon!";
        String goodbye = H_LINE + goodbyeText + H_LINE;
        System.out.println(goodbye);
    }
}
