package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    public static final String WELCOME_MESSAGE = "Hello! I'm Duke";
    public static final String ADD_TASK_MESSAGE = "Got it. I've added this task:";
    public static final String QUESTION_MESSAGE = "What can I do for you?";

    public static final String PRINT_LIST_MESSAGE = "Here are the tasks in your list:";

    public static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";

    public static final String MARK_TASK_UNDONE_MESSAGE = "Nice! I've marked this task as not done:";
    public static final String MARK_TASK_DONE_MESSAGE = "Nice! I've marked this task as done:";
    public static final String DELETE_TASK_MESSAGE = "Noted. I've removed this task:";

    public static final String LINE_DIVIDER = "____________________________________________________________";
    private final Scanner scanner;
    public Ui() {
        scanner =new Scanner(System.in);
    }


    public void showLoadingError() {
        printOutput("Error loading txt file", LINE_DIVIDER);
    }
    public void showWelcomeMessage() {
        printOutput(LINE_DIVIDER, WELCOME_MESSAGE, QUESTION_MESSAGE,LINE_DIVIDER);
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
        return scanner.nextLine();
    }

    /**
     * Prints the feedback.
     *
     * @param result Feedback to be printed in string.
     */


    public void showFeedbackToUser(String result) {
        if (result.length() == 0) {
            printOutput(LINE_DIVIDER);
        } else {
            printOutput(result, LINE_DIVIDER);
        }
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
    private ArrayList<String> getTaskListFeedback(ArrayList<Task> tasks) {
        ArrayList<String> taskListFeedback = new ArrayList<>();
        taskListFeedback.add(PRINT_LIST_MESSAGE);
        getTasksFeedback(tasks, taskListFeedback);
        return taskListFeedback;
    }

    /**
     * Display the tasks matching the keyword
     *
     * @param filterTasks tasks that match the keyword
     */
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

    /**
     * Returns the formatted tasks count output.
     *
     * @param taskList TaskList that the tasks is stored in.
     * @return Feedback for tasks count.
     */
    public String getTasksCountFeedback(TaskList taskList) {
        int taskCount = taskList.getTaskCount();
        String taskName = taskCount < 2 ? "task" : "tasks";
        return System.lineSeparator() + String.format("Now you have %d %s in the list.", taskCount, taskName);
    }

    public void showExitMessage() {
        showFeedbackToUser(EXIT_MESSAGE);
    }

    public String getMarkMessage() {
        return MARK_TASK_DONE_MESSAGE;
    }

    public String getUnmarkMessage() {
        return MARK_TASK_UNDONE_MESSAGE;
    }

    /**
     * Returns the final feedback after updating the status of the task.
     *
     * @param feedback Initial feedback.
     * @param task Task which the status is being updated.
     * @return Feedback
     */
    public static String appendFeedbackForUpdatingStatus(String feedback, Task task) {
        feedback = feedback + System.lineSeparator() + "  " + task.getTaskInfo();
        return feedback;
    }


    /**
     * Returns the feedback for create and add a task.
     * @param task Task created.
     * @param taskList TaskList which the task is the added to.
     * @return Feedback
     */
    public String getAddTaskFeedback(Task task, TaskList taskList) {
        return ADD_TASK_MESSAGE + System.lineSeparator() + task.getTaskInfo() + System.lineSeparator() + getTasksCountFeedback(taskList);
    }

    /**
     * Returns the final feedback after deleting the task.
     *
     * @param feedback Feedback obtained before removing the task.
     * @param taskList TaskList which contains the tasks.
     * @return Final feedback
     */
    public String getFinalDeleteFeedback(String feedback, TaskList taskList) {
        return feedback + System.lineSeparator() + getTasksCountFeedback(taskList);
    }

    /**
     * Returns the initial feedback before deleting the task.
     *
     * @param taskIndex Index of the task to be deleted.
     * @param taskList TaskList which the tasks are in.
     * @return Feedback before deleting.
     */

    public String getInitialDeleteFeedback(int taskIndex, TaskList taskList) {
        return DELETE_TASK_MESSAGE + System.lineSeparator() + "  " + taskList.getTasks().get(taskIndex).getTaskInfo();
    }

}
