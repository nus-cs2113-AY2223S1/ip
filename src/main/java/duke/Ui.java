package duke;


import duke.task.Task;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {
    private final Scanner in;
    private final PrintStream out;


    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Prints a welcome message at the start of the program.
     */
    public void showWelcomeMessage() {
        System.out.println("Hey! How can I help you?");
    }

    /**
     * Prints a farewell message before stopping the operation.
     * @param commands The command that is entered by user separated by command type and description.
     */
    public static void farewellMessage(String[] commands) {
        String commandEntered = commands[0];

        System.out.println("You have entered: " + commandEntered + ".");
        System.out.println("Bye bye!! :)");
    }

    /**
     * Prints a completed message after add operation is executed.
     *
     * @param type The type of task that has been added.
     */
    public static void addCompleteMessage(String type) {
        boolean isTodo = type.equals("todo");
        boolean isEvent = type.equals("event");
        boolean isDeadline = type.equals("deadline");

        System.out.println("Got it. I added to the list.");
        int lastIndex = TaskList.tasks.size() - 1;

        if (isTodo) {
            System.out.println("  [T][ ] " + TaskList.tasks.get(lastIndex).getTask());
        } else if (isEvent) {
            System.out.println("  [E][ ] " + TaskList.tasks.get(lastIndex).getTask());
        } else if (isDeadline){
            System.out.println("  [D][ ] " + TaskList.tasks.get(lastIndex).getTask());
        }

        showNumberOfTask();

    }

    /**
     * Prints the number of task in list of task.
     */
    public static void showNumberOfTask() {
        System.out.println("You now have " + TaskList.tasks.size() + " task(s)");
    }

    /**
     * Prints all the task that is stored in list of task.
     */
    public static void printAllTasks() {
        boolean isEmpty = (TaskList.tasks.size() == 0);
        if (isEmpty) {
            System.out.println("Great work! There's nothing in the to do list!");
        } else {
            for (int i = 0; i < TaskList.tasks.size(); i += 1) {
                Ui.printTask(i);
            }
        }
    }

    /**
     * Prints the task that is being deleted from list of task.
     *
     * @param deleteNumber The task number that is being deleted from the list of task.
     */
    public static void printDeletedTask(int deleteNumber) {
        System.out.println("Hehe... I've deleted the task below: ");
        System.out.println("  [T][ ] " + TaskList.tasks.get(deleteNumber).getTask());
    }

    /**
     * Prints the specific task that is indicated by index.
     *
     * @param index The index which the task will be printed from the list of task..
     */
    public static void printTask(int index) {
        String status = TaskList.tasks.get(index).getStatusIcon();
        String task = TaskList.tasks.get(index).getTask();
        String taskType = getClass(index);

        System.out.println((index + 1) + ". " + taskType + "[" + status + "] " + task);

    }

    /**
     * Waits for user to input command and read it
     *
     * @return The string command inputted by the user.
     */
    public static String readCommand() {
        Scanner in = new Scanner(System.in);

        return in.nextLine();
    }

    /**
     * Returns the specified class name in string format.
     *
     * @param index The index of which the class name will be returned.
     * @return the name of the class in string format.
     */
    public static String getClass(int index) {
        String className = TaskList.tasks.get(index).getClass().getSimpleName();
        boolean isTodo = className.equals("Todo");
        boolean isDeadline = className.equals("Deadline");
        boolean isEvent = className.equals("Event");

        if (isTodo) {
            return "[T]";
        } else if (isDeadline) {
            return "[D]";
        } else if (isEvent) {
            return "[E]";
        }
        return "";
    }

    /**
     * Finds the tasks that match with the string inputted.
     *
     * @param matchingString The string that will be used to check across the list of task.
     */
    public static void findMatchingTasks(String matchingString) {
        boolean isContain;
        boolean isTitlePrinted = false;
        int listNumber = 1;

        for (int i = 0; i < TaskList.tasks.size(); i += 1) {
            String currentTask = TaskList.tasks.get(i).getTask();
            isContain = currentTask.contains(matchingString);
            if (isContain) {
                if (!isTitlePrinted) {
                    System.out.println("Here are the matching tasks in your list: :)");
                    isTitlePrinted = true;
                }
                printMatchingTask(i, listNumber);
                listNumber += 1;
            }
        }

        if (listNumber == 1 ) {
            System.out.println("Sorry we don't have any task that match with your input. :/");
        }
    }

    /**
     * Prints the tasks that is in the index.
     *
     * @param taskIndex The index of which the task is printed.
     * @param listIndex The line number of which task is printed.
     */
    public static void printMatchingTask(int taskIndex, int listIndex) {
        String status = TaskList.tasks.get(taskIndex).getStatusIcon();
        String task = TaskList.tasks.get(taskIndex).getTask();
        String taskType = getClass(taskIndex);

        System.out.println((listIndex) + ". " + taskType + "[" + status + "] " + task);
    }


}
