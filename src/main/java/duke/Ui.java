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
        printOutput("Error loading txt file", LINE_DIVIDER);
    }
    public void showWelcomeMessage() {
        printOutput(LINE_DIVIDER, WELCOME_MESSAGE, QUESTION_MESSAGE);
    }


    private void printOutput(String... message) {
        for (String line : message) {
            System.out.println(line);
        }
    }

    /**
     * Reads the input in next line.
     *
     * @return Input from the next line.
     */
    public String readCommand() {
        String input = scanner.nextLine();
        return input;
    }

    /**
     * Prints the feedback.
     *
     * @param result Feedback to be printed in string.
     */


    public void showFeedbackToUser(String result) {
        printOutput(result, LINE_DIVIDER);
    }

    /**
     * Prints the feedback.
     *
     * @param result Feedback to be printed in ArrayList.
     */
    public void showFeedbackToUser(ArrayList<String> result) {
        for (String string : result) {
            System.out.println(string);
        }
    }

    /**
     * Prints the task list to the user.
     *
     * @param tasks Tasks in the TaskList.
     */
    public void showTaskList(ArrayList<Task> tasks) {
        ArrayList<String> feedbackTaskList = getTaskListFeedback(tasks);
        showFeedbackToUser(feedbackTaskList);
    }

    /**
     * Returns an ArrayList of formatted tasks in string with opening message.
     *
     * @param tasks Tasks to be formatted
     * @return Feedback to the user.
     */
    private static ArrayList<String> getTaskListFeedback(ArrayList<Task> tasks) {
        ArrayList<String> taskListFeedback = new ArrayList<>();
        taskListFeedback.add(PRINT_LIST_MESSAGE);
        for (int i = 0; i < tasks.size(); i++) {
            taskListFeedback.add(String.format("%d.", i + 1) + tasks.get(i).getTaskInfo());
        }
        return taskListFeedback;
    }

}
