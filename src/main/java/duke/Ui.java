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

    public void showWelcomeMessage() {
        System.out.println("Hey! How can I help you?");
    }

    public static void farewellMessage() {
        //Goodbye
        System.out.println("Bye bye!");
    }

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

    public static void showNumberOfTask() {
        System.out.println("You now have " + TaskList.tasks.size() + " task(s)");
    }

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

    public static void printDeletedTask(int deleteNumber) {
        System.out.println("Hehe... I've deleted the task below: ");
        System.out.println("  [T][ ] " + TaskList.tasks.get(deleteNumber).getTask());
    }

    public static void printTask(int index) {
        String status = TaskList.tasks.get(index).getStatusIcon();
        String task = TaskList.tasks.get(index).getTask();
        String taskType = getClass(index);

        System.out.println((index + 1) + ". " + taskType + "[" + status + "] " + task);

    }

    public static String readCommand() {
        Scanner in = new Scanner(System.in);

        return in.nextLine();
    }

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

    public static void printMatchingTask(int taskIndex, int listIndex) {
        String status = TaskList.tasks.get(taskIndex).getStatusIcon();
        String task = TaskList.tasks.get(taskIndex).getTask();
        String taskType = getClass(taskIndex);

        System.out.println((listIndex) + ". " + taskType + "[" + status + "] " + task);
    }


}
