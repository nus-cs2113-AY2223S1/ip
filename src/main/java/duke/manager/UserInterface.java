package duke.manager;

import duke.task.Task;
import duke.task.TaskList;

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
                + "What can I do for you?");
        printBorderLines();
    }

    /**
     * Prints goodbye message
     */
    public static void printGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void printAddTaskMessage(Task newTask) {
        System.out.println("Got it. I've added this task:" + System.lineSeparator()
                + MESSAGE_INDENTATION + newTask + System.lineSeparator()
                + "Now you have " + TaskList.getSize() + " tasks in the list.");
    }

    public static void printDeleteTaskMessage(Task newTask) {
        System.out.println("Noted. I've removed this task:" + System.lineSeparator()
                + MESSAGE_INDENTATION + newTask + System.lineSeparator()
                + "Now you have " + TaskList.getSize() + " tasks in the list.");
    }
}
