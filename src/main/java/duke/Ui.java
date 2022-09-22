package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    public static final String WELCOME_MESSAGE = "Hello! I'm Duke";
    public static final String QUESTION_MESSAGE = "What can I do for you?";

    public static final String PRINT_LIST_MESSAGE = "Here are the tasks in your list:";

    public static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";

    public static final String LINE_DIVIDER = "____________________________________________________________";
    private final Scanner scanner;
    public Ui() {
        scanner =new Scanner(System.in);
    }

    public void showLoadingError() {
    }


    public void showWelcomeMessage() {
        printOutput(LINE_DIVIDER, WELCOME_MESSAGE, QUESTION_MESSAGE);
    }


    private void printOutput(String... message) {
        for (String line : message) {
            System.out.println(line);
        }
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showFeedbackToUser(String result) {
        if (result.length() == 0) {
            printOutput(LINE_DIVIDER);
        } else {
            printOutput(result, LINE_DIVIDER);
        }
    }

    public void showFeedbackToUser(ArrayList<String> result) {
        for (String string : result) {
            System.out.println(string);
        }
    }

    public void showTaskList(ArrayList<Task> tasks) {
        ArrayList<String> taskListFeedback = new ArrayList<>();
        taskListFeedback.add(PRINT_LIST_MESSAGE);
        getTasksFeedback(tasks, taskListFeedback);
        showFeedbackToUser(taskListFeedback);
    }

    public void showMatchingTasks(ArrayList<Task> filterTasks) {
        ArrayList<String> matchingTasksFeedback  = new ArrayList<>();
        matchingTasksFeedback.add("Here are the matching tasks in your list:");
        getTasksFeedback(filterTasks, matchingTasksFeedback);
        showFeedbackToUser(matchingTasksFeedback);
    }

    private static void getTasksFeedback(ArrayList<Task> tasks, ArrayList<String> tasksFeedback) {
        for (int i = 0; i < tasks.size(); i++) {
            tasksFeedback.add(String.format("%d.", i + 1) + tasks.get(i).getTaskInfo());
        }
    }

    public void showExitMessage() {
        showFeedbackToUser(EXIT_MESSAGE);
    }
}
