package duke;

import duke.taskmanager.TaskList;
import duke.taskmanager.tasks.Task;

import java.util.regex.Pattern;

/**
 * Handles all the output that the user can see.
 */
public class UI {
    /**
     * Demarcation to indicate system output.
     */
    public final String DASH_SEPARATOR = "------------------------------------------------------------"
            + System.lineSeparator();

    /**
     * How the user will see the system output.
     *
     * @param stringToOutput message related to the command the user has input
     */
    public void formatOutput(String stringToOutput) {
        System.out.println(DASH_SEPARATOR + stringToOutput + System.lineSeparator() + DASH_SEPARATOR);
    }

    /**
     * The greeting message which will only be shown once at the start of the programme.
     */
    public void printGreetingMessage() {
        String LOGO = "     ____.  _____ ______________   ____.___  _________" + System.lineSeparator()
                + "    |    | /  _  \\\\______   \\   \\ /   /|   |/   _____/" + System.lineSeparator()
                + "    |    |/  /_\\  \\|       _/\\   Y   / |   |\\_____  \\" + System.lineSeparator()
                + "/\\__|    /    |    \\    |   \\ \\     /  |   |/        \\" + System.lineSeparator()
                + "\\________\\____|__  /____|_  /  \\___/   |___/_______  /" + System.lineSeparator()
                + "                 \\/       \\/                       \\/" + System.lineSeparator();
        String greet = "Hello! I'm" + System.lineSeparator() + LOGO + "What can I do for you?"
                + System.lineSeparator() + "Enter \"bye\" to exit.";
        formatOutput(greet);
    }

    /**
     * Output message to inform the user which <code>task</code> is successfully marked or unmarked.
     *
     * @param task the task to be marked or unmarked
     * @param done whether the task is done
     */
    public void printMark(Task task, boolean done) {
        formatOutput(task.markDone(done));
    }

    /**
     * Output message to inform the user which new <code>task</code> is successfully added to the
     * <code>TaskList</code> which is the last <code>task</code> in the <code>TaskList</code>.
     *
     * @param tasks stores all the user's current tasks
     */
    public void printTask(TaskList tasks) {
        Task task = tasks.get(tasks.size() - 1);
        formatOutput("Got it. I've added this task:" + System.lineSeparator()
                + task + System.lineSeparator() + "Now you have " + tasks.size()
                + " tasks in the list.");
    }

    /**
     * Output message to inform the user which <code>task</code> is successfully deleted from the
     * <code>TaskList</code>.
     *
     * @param tasks stores all the user's current tasks
     * @param pos   the position of the task to be deleted
     */
    public void printTaskAfterDelete(TaskList tasks, int pos) {
        Task task = tasks.get(pos);
        //account for deleted task
        int numTasks = tasks.size() - 1;
        formatOutput("Noted. I've removed this task:" + System.lineSeparator()
                + task + System.lineSeparator() + "Now you have " + numTasks
                + " tasks in the list.");
    }

    /**
     * Outputs all the current <code>task</code>s in the <code>TaskList</code>.
     *
     * @param tasks stores all the user's current tasks
     */
    public void printList(TaskList tasks) {
        StringBuilder listMessage = new StringBuilder();
        listMessage.append("Here are the tasks in your list:").append(System.lineSeparator());
        for (int i = 0; i < tasks.size(); i++) {
            listMessage.append(i+1).append(".").append(tasks.get(i)).append(System.lineSeparator());
        }
        formatOutput(listMessage.toString());
    }

    /**
     * Looks through the <code>TaskList</code> for <code>task</code>s that match the keyword(s) and print it.
     * The case of the keyword(s) does not matter.
     *
     * @param tasks       stores all the user's current tasks
     * @param keywords    user provided keyword
     */
    public void printMatchList(TaskList tasks, String keywords) {
        Pattern pattern = Pattern.compile(keywords, Pattern.CASE_INSENSITIVE);
        StringBuilder matchString = new StringBuilder();
        int count = 0;
        matchString.append("Here are the tasks matching \"").append(keywords);
        matchString.append("\" in your list:").append(System.lineSeparator());
        for (int i = 0; i < tasks.size(); i++) {
            if (pattern.matcher(tasks.get(i).getDescription()).find()) {
                matchString.append(i+1).append(".").append(tasks.get(i)).append(System.lineSeparator());
                count++;
            }
        }
        if (count > 0) {
            formatOutput(matchString.toString());
        } else {
            formatOutput("Sorry! There are no tasks matching your specified keyword(s)");
        }

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
