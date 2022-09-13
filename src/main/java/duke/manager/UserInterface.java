package duke.manager;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {

    private static final String MESSAGE_INDENTATION = "  ";
    private static final String BORDER_LINES = "______________________________________________________________________";

    private static final String DUKE_LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Prints 70 horizontal lines.
     */
    public static void printBorderLines() {
        System.out.println(BORDER_LINES);
    }

    /**
     * Prints Duke logo and greets the user
     */
    public static void printHello() {
        System.out.println("Hello from \n" + DUKE_LOGO);
        printBorderLines();
        System.out.println("Hello! I'm Duke" + System.lineSeparator()
                + "I am a basic task manager" + System.lineSeparator()
                + "What can I do for you?");
        printBorderLines();
    }

    /**
     * Prints goodbye message
     */
    public static void printGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Reads and then returns the next line of the user input
     *
     * @param in the scanner that takes in user input
     * @return the next line of the input for parsing
     */
    public static String readInput(Scanner in) {
        return in.nextLine();
    }

    public static void printAddTaskMessage(ArrayList taskList, Task newTask) {
        System.out.println("Got it. I've added this task:" + System.lineSeparator()
                + MESSAGE_INDENTATION + newTask + System.lineSeparator()
                + "Now you have " + taskList.size() + " tasks in the list.");
    }

    public static void printDeleteTaskMessage(ArrayList taskList, Task newTask) {
        System.out.println("Here are the tasks in your list:" + System.lineSeparator()
                + MESSAGE_INDENTATION + newTask + System.lineSeparator()
                + "Now you have " + taskList.size() + " tasks in the list.");
    }

}
