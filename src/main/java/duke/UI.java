package duke;

import duke.taskmanager.TaskList;
import duke.taskmanager.tasks.Task;

public class UI {
    public final String DASH_SEPARATOR = "------------------------------------------------------------\n";
    public static int oneBasedIndex = 1;
    public void formatOutput(String stringToOutput) {
        System.out.println(DASH_SEPARATOR + stringToOutput + System.lineSeparator() + DASH_SEPARATOR);
    }

    public void printGreetingMessage() {
        String LOGO = "     ____.  _____ ______________   ____.___  _________\n"
                + "    |    | /  _  \\\\______   \\   \\ /   /|   |/   _____/\n"
                + "    |    |/  /_\\  \\|       _/\\   Y   / |   |\\_____  \\\n"
                + "/\\__|    /    |    \\    |   \\ \\     /  |   |/        \\\n"
                + "\\________\\____|__  /____|_  /  \\___/   |___/_______  /\n"
                + "                 \\/       \\/                       \\/\n";
        String greet = "Hello! I'm\n" + LOGO + "What can I do for you?\n"
                + "Enter \"bye\" to exit.";
        formatOutput(greet);
    }

    public void printMark(Task task, boolean done) {
        formatOutput(task.markDone(done));
    }

    public void printTask(Task task) {
        formatOutput("Got it. I've added this task:" + System.lineSeparator()
                + task + System.lineSeparator() + "Now you have " + oneBasedIndex
                + " tasks in the list.");
        oneBasedIndex++;
    }

    public void printTaskAfterDelete(Task task) {
        oneBasedIndex--;
        formatOutput("Noted. I've removed this task:" + System.lineSeparator()
                + task + System.lineSeparator() + "Now you have " + (oneBasedIndex - 1)
                + " tasks in the list.");
    }

    public void printList(TaskList tasks) {
        StringBuilder listMessage = new StringBuilder();
        listMessage.append("Here are the tasks in your list:").append(System.lineSeparator());
        for (int i = 1; i < oneBasedIndex; i++) {
            listMessage.append(i).append(".").append(tasks.get(i)).append(System.lineSeparator());
        }
        formatOutput(listMessage.toString());
    }

    public void printEmptyException(String command) {
        formatOutput("☹ OOPS!!! The description of a " + command + " cannot be empty.");
    }
    public void printWrongCommandException() {
        formatOutput("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
    public void printTaskOutOfBoundsException() {
        formatOutput("☹ OOPS!!! The task number you specified does not exist.");
    }
    public void printNoBackslashException() {
        formatOutput("☹ OOPS!!! You did not specify a deadline OR event datetime.");
    }
    public void printFileNotFoundException() {
        formatOutput("File not found sorry.");
    }
    public void printNumberFormatException() {
        formatOutput("☹ OOPS!!! You did not enter a number.");
    }
    public void showLoadingError() {
        formatOutput("☹ OOPS!!! There was no previously saved tasks.");
    }
    public void printExitMessage() {
        formatOutput("Bye. Hope to see you again soon!");
    }


}
