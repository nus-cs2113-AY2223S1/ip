package duke.ui;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

public class UI {

    public final String LINE_DIVIDER = "-------------------------------------\n";
    public final String WELCOME_TEXT = "Hello I am Duke. Here are a list of command:\n"
            + "todo {task} : Creates a todo\n"
            + "deadline {deadline /by time} : Creates a deadline by time\n"
            + "event {event /at time} : Creates an event at time\n"
            + "mark/unmark {index} : Marks a task\n"
            + "delete {index} : Deletes a task\n"
            + "list : List all current tasks\n"
            + "bye : Exits the program";

    public final String BYE_MESSAGE = "Goodbye, hope to see you again.";

    public void welcomeUser() {
        formatMessage(WELCOME_TEXT);
    }

    private void formatMessage(String message) {
        System.out.print(LINE_DIVIDER + message + "\n" + LINE_DIVIDER);
    }

    public void byeMessage() {
        formatMessage(BYE_MESSAGE);
    }

    public void addMessage(Task task, TaskList tasks) {
        formatMessage("Got it. I have added this task:\n" + task
                + "\nYou now have " + TaskList.getTaskCount() + " tasks.");
    }

    public void printList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            formatMessage("You have no tasks currently.");
        } else {
            int size = tasks.size();
            String message = "Here is the list of your tasks: \n";
            for (int i = 0; i < size; i++) {
                if (i == size - 1) {
                    message += (i + 1) + ". " + tasks.get(i);
                } else {
                    message += (i + 1) + ". " + tasks.get(i) + "\n";
                }
            }
            formatMessage(message);
        }
    }

    public void markMessage(TaskList tasks, int index) {
        Task task = tasks.getTask(index - 1);
        if (!task.getDone()) {
            formatMessage("Unmarked the following task:\n "
                    + task);
        } else {
            formatMessage("Marked the following task:\n"
                    + task);
        }
    }

    public void invalidCommand() {
        formatMessage("You have entered an invalid command.");
    }

    public void deleteMessage(TaskList tasks, int index) {
        Task deletedTask = tasks.getTask(index - 1);
        formatMessage("The following task has been deleted: \n"
                + deletedTask + "\nYou now have " + (TaskList.getTaskCount() - 1)
                + " tasks.");
    }

    public void printFound(ArrayList<Task> found) {
        String message = "Here are the matching tasks in your list:\n";
        int size = found.size();
        if (size == 0) {
            message = "Found no matching tasks in your list.";
        } else {
            for (int i = 0; i < size; i++) {
                if (i == size - 1) {
                    message += (i + 1) + ". " + found.get(i);
                } else {
                    message += (i + 1) + ". " + found.get(i) + "\n";
                }
            }
        }
        formatMessage(message);
    }

    public void printErrorMessage(DukeException e) {
        formatMessage(e.getMessage());
    }

    public String getInput() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        return input.trim();
    }
}
