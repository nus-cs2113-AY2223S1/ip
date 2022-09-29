package Ui;

import Tasks.Task;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class TextUi {
    private static final String LS = System.lineSeparator();
    private static final String DIVIDER = "        ____________________________________________";
    private static final String FULL_LINE = "____________________________________________";

    private final Scanner in;
    private final PrintStream out;

    public TextUi() {
        this(System.in, System.out);
    }

    public TextUi(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /** Generates and prints a welcome message to the user upon the start of the application. **/
    public void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo + LS + FULL_LINE + LS + "Hello! I'm Duke." + LS
                + "What can I do for you?" + LS + FULL_LINE);
    }

    /** Generates and prints a goodbye message to the user upon termination of the application. **/
    public void printGoodbyeMessage() {
        System.out.println(FULL_LINE + LS + "Bye. Hope to see you again soon!" + LS + FULL_LINE);
    }

    /**
     * Prints a given list of tasks, including the tasks' type, status, name and possible deadline.
     *
     * @param list the task list to be printed
     */
    public void printList(ArrayList<Task> list) {
        System.out.println(DIVIDER);

        for (int i = 1; i <= list.size(); i += 1) {
            String taskType = list.get(i - 1).getTaskType();
            String markDoneStatus = list.get(i - 1).isDone() ? "[X]" : "[ ]";

            switch (taskType) {
            case "ToDo":
                System.out.println("        [T]" + markDoneStatus + " " + i + ". " + list.get(i - 1).getTaskName());
                break;

            case "Deadline":
                String deadline = list.get(i - 1).getTaskDeadline();
                System.out.println("        [D]" + markDoneStatus + " " + i + ". " + list.get(i - 1).getTaskName() +
                        " (by: " + deadline + ")");
                break;

            case "Event":
                String eventTime = list.get(i - 1).getEventTime();
                System.out.println("        [E]" + markDoneStatus + " " + i + ". " + list.get(i - 1).getTaskName() +
                        " (at: " + eventTime + ")");
                break;

            default:
                break;
            }
        }

        System.out.println(DIVIDER);
    }
}
