import java.util.Scanner;

public class Duke {
    /**
     * Prints the welcome message
     */
    public static void welcomeUser() {
        String welcomeText = "-------------------------------------\n"
                + "Hello! I am not Duke.\n"
                + "Here are a list of commands: \n"
                + "\tadd <string> : adds a task to the list\n"
                + "\tlist : shows list of tasks\n"
                + "\tmark <task id> : marks a task \n"
                + "\tunmark <task id> : unmarks a task \n"
                + "\tbye : exits the program \n"
                + "I WILL CRASH IF YOU DO NOT COMPLY!!!\n"
                + "-------------------------------------";
        System.out.println(welcomeText);
    }

    /**
     * Sets input to lowercase and removes any
     * leading and trailing whitespaces.
     *
     * @param input Input entered by user.
     * @return Lowercase and trimmed input.
     */
    public static String cleanInput(String input) {
        return input.toLowerCase().trim();
    }

    /**
     * Prints the current list of tasks.
     * If list is empty, print error message.
     *
     * @param list List of tasks.
     */
    public static void listTasks(Task[] list) {
        if (Task.getTaskCount() != 0) {
            for (int i = 0; i < Task.getTaskCount(); i++) {
                System.out.println(list[i].getStatusIcon() + (i + 1) + ": " + list[i].getDescription());
            }
        } else {
            System.out.println("The list is empty, please add something!");
        }
    }

    /**
     * Adds a task to the list of tasks.
     *
     * @param taskList        The list of tasks.
     * @param taskDescription The description of the task to be added.
     */
    public static void addTask(Task[] taskList, String taskDescription) {
        taskList[Task.getTaskCount()] = new Task(taskDescription);
        System.out.println("Added \"" + taskDescription + "\"");
    }

    /**
     * Marks or unmarks the task that user chooses.
     *
     * @param isMark   User's choice to mark or unmark.
     * @param taskList List of tasks.
     * @param input    User's input.
     */
    public static void markTask(Boolean isMark, Task[] taskList, String input) {
        if (isMark) {
            int toMark = Integer.parseInt(input.substring(5)) - 1;
            System.out.println(taskList[toMark].getDescription() + " marked");
            taskList[toMark].setDone(true);
        } else {
            int toMark = Integer.parseInt(input.substring(7)) - 1;
            System.out.println(taskList[toMark].getDescription() + " unmarked");
            taskList[toMark].setDone(false);
        }
    }

    /**
     * Method that takes in the user's input to allow them
     * to interact with Duke.
     */
    public static void trackInput() {
        Scanner scanner = new Scanner(System.in);
        Task[] taskList = new Task[100];
        final String LINE_DIVIDER = "-------------------------------------";
        while (true) {
            String input = cleanInput(scanner.nextLine());
            System.out.println(LINE_DIVIDER);
            if (input.equals("bye")) {
                System.out.println("Bye bye!!! \n" + LINE_DIVIDER + "\n");
                break;
            } else if (input.equals("list")) {
                listTasks(taskList);
            } else if (input.startsWith("add ")) {
                addTask(taskList, input.substring(4));
            } else if (input.startsWith("mark ")) {
                markTask(true, taskList, input);
            } else if (input.startsWith("unmark ")) {
                markTask(false, taskList, input);
            } else {
                System.out.println("Invalid command.");
            }
            System.out.println(LINE_DIVIDER);
        }
    }

    public static void main(String[] args) {
        welcomeUser();
        trackInput();
    }
}
