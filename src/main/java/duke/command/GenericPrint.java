package duke.command;

import duke.task.Task;

public class GenericPrint {

    // acts as a standard for printing messages

    public static final String BYE_GREETING = "bye";
    private static final String HELLO_GREETING = "hello";
    private static final String BORDER_LINES = "______________________________________________________________________";

    public static void printLines() {
        System.out.println(BORDER_LINES);
    }

    public static void printGreetings(String greeting) {
        if (greeting.equals(HELLO_GREETING)) {
            // hello greeting
            printLines();
            System.out.println("Hello! I'm Duke" + System.lineSeparator() + "What can I do for you?");
            printLines();
        }   else if (greeting.equals(BYE_GREETING)){
            // bye greeting
            System.out.println("Bye. Hope to see you again soon!");
        }
    }

    public static void printTaskMessages(Task[] tasks, int taskNumber) {
        System.out.println("Got it. I've added this task:" + System.lineSeparator()
                +  tasks[taskNumber] + System.lineSeparator() + "Now you have "
                + (taskNumber + 1) + " tasks in the list.");
    }

    public static void exceptionMessagePrint() {
        // to be done over time, move exception message printing here
    }
}
