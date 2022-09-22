package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    public static final String WELCOME_MESSAGE = "Hello! I'm Duke";
    public static final String QUESTION_MESSAGE = "What can I do for you?";

    public static final String PRINT_LIST_MESSAGE = "Here are the tasks in your list:";

    public static final String LINE_DIVIDER = "____________________________________________________________";
    private Scanner scanner;
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
        String input = scanner.nextLine();
        return input;
    }

    public void showFeedbackToUser(String result) {
        printOutput(result, LINE_DIVIDER);
    }

    public void showFeedbackToUser(ArrayList<String> result) {
        for (String string : result) {
            System.out.println(string);
        }
    }

    public void showTaskList(ArrayList<Task> tasks) {
        ArrayList<String> feedbackTaskList = getTaskListFeedback(tasks);
        showFeedbackToUser(feedbackTaskList);
    }

    private static ArrayList<String> getTaskListFeedback(ArrayList<Task> tasks) {
        ArrayList<String> taskListFeedback = new ArrayList<>();
        taskListFeedback.add(PRINT_LIST_MESSAGE);
        for (int i = 0; i < tasks.size(); i++) {
            taskListFeedback.add(String.format("%d.", i + 1) + tasks.get(i).getTaskInfo());
        }
        return taskListFeedback;
    }
}
