import java.util.Scanner;

public class Duke {
    static final int MAX_TASK_SIZE = 100;

    public static String readTaskDescription(Scanner in) throws EmptyTaskDescriptionException {
        String taskDescription = in.nextLine().trim();
        if (taskDescription.isEmpty()) {
            throw new EmptyTaskDescriptionException();
        }
        return taskDescription;
    }

    public static String extractTaskName(String taskDescription, String dateTimeReference) {
        int dateTimeIndex = taskDescription.indexOf(dateTimeReference);
        return taskDescription.substring(0, dateTimeIndex - 1).trim();
    }

    public static String extractTaskDateTime(String taskDescription, String dateTimeReference) {
        int dateTimeIndex = taskDescription.indexOf(dateTimeReference);
        return taskDescription.substring(dateTimeIndex + 3).trim();
    }

    public static void addToDoTask(Task[] tasks, int taskIndex, String todoTaskName) {
        tasks[taskIndex] = new Todo(todoTaskName);
    }

    public static void addDeadlineTask(Task[] tasks, int taskIndex, String deadlineTaskName, String deadlineTaskBy) {
        tasks[taskIndex] = new Deadline(deadlineTaskName, deadlineTaskBy);
    }

    public static void addEventTask(Task[] tasks, int taskIndex, String eventTaskName, String eventTaskAt) {
        tasks[taskIndex] = new Event(eventTaskName, eventTaskAt);
    }

    public static void printTaskAddedMessage(Task[] tasks, int taskIndex) {
        boolean isSingleTask = (taskIndex == 0);
        String task = isSingleTask ? " task" : " tasks";
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks[taskIndex]);
        System.out.println("Now you have " + (taskIndex + 1) + task + " in the list.");
    }

    public static void markAsDone(Task[] tasks, Scanner in) {
        try {
            int markIndex = Integer.parseInt(in.next()) - 1;
            tasks[markIndex].setDone(true);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + tasks[markIndex]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! I'm sorry, the taskID does not exist in the task list.");
        } catch (NullPointerException e) {
            System.out.println("☹ OOPS!!! I'm sorry, there is no such task found in the task list.");
        } catch (NumberFormatException e) {
            System.out.println("☹ OOPS!!! Please kindly provide a numerical taskID.");
        }
    }

    public static void markAsUndone(Task[] tasks, Scanner in) {
        try {
            int unmarkIndex = Integer.parseInt(in.next()) - 1;
            tasks[unmarkIndex].setDone(false);
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + tasks[unmarkIndex]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! I'm sorry, the taskID does not exist in the task list.");
        } catch (NullPointerException e) {
            System.out.println("☹ OOPS!!! I'm sorry, there is no such task found in the task list.");
        } catch (NumberFormatException e) {
            System.out.println("☹ OOPS!!! Please kindly provide a numerical taskID.");
        }
    }

    public static void printList(Task[] tasks, int taskIndex) {
        boolean hasNoTask = (taskIndex == 0);
        if (hasNoTask) {
            System.out.println("There is no task in your list.");
            return;
        }
        boolean isSingleTask = (taskIndex == 1);
        String task = isSingleTask ? "is the task" : "are the tasks";
        System.out.println("Here " + task + " in your list:");
        taskIndex = 0;
        while (tasks[taskIndex] != null) {
            System.out.println((taskIndex + 1) + "." + tasks[taskIndex]);
            taskIndex++;
        }
    }

    public static void main(String[] args) {
        //Greet
        showWelcomeMessage();

        //Read Input
        String command;                         //variable to store line (input)
        Scanner in = new Scanner(System.in);    //create object that reads input

        Task[] tasks = new Task[MAX_TASK_SIZE];
        int taskIndex = 0;
        do {
            command = in.next();                //Read first word (command)
            switch (command) {
            case "list":
                printList(tasks, taskIndex);
                break;
            case "unmark":
                markAsUndone(tasks, in);
                break;
            case "mark":
                markAsDone(tasks, in);
                break;
            //Add Task
            case "todo":
                try {
                    String todoTaskName = readTaskDescription(in);
                    addToDoTask(tasks, taskIndex, todoTaskName);
                    printTaskAddedMessage(tasks, taskIndex);
                    taskIndex++;
                } catch (EmptyTaskDescriptionException e) {
                    showEmptyToDoDescriptionExceptionMessage();
                }
                break;
            case "deadline":
                try {
                    String deadlineTask = readTaskDescription(in);
                    String deadlineTaskName = extractTaskName(deadlineTask ,"/by");
                    String deadlineTaskBy   = extractTaskDateTime(deadlineTask ,"/by");
                    addDeadlineTask(tasks, taskIndex, deadlineTaskName, deadlineTaskBy);
                    printTaskAddedMessage(tasks, taskIndex);
                    taskIndex++;
                } catch (EmptyTaskDescriptionException e) {
                    showEmptyDeadlineDescriptionExceptionMessage();
                }
                break;
            case "event":
                try {
                    String eventTask = readTaskDescription(in);
                    String eventTaskName = extractTaskName(eventTask ,"/at");
                    String eventTaskAt   = extractTaskDateTime(eventTask ,"/at");
                    addEventTask(tasks, taskIndex, eventTaskName, eventTaskAt);
                    printTaskAddedMessage(tasks, taskIndex);
                    taskIndex++;
                } catch (EmptyTaskDescriptionException e) {
                    showEmptyEventDescriptionExceptionMessage();
                }
                break;
            default:
                showUndefinedCommandMessage();
            }
        } while (!command.equals("bye"));

        //Exit
        showByeMessage();
    }

    private static void showEmptyToDoDescriptionExceptionMessage() {
        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
    }

    private static void showEmptyDeadlineDescriptionExceptionMessage() {
        System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
    }

    private static void showEmptyEventDescriptionExceptionMessage() {
        System.out.println("☹ OOPS!!! The description of a event cannot be empty.");
    }

    private static void showUndefinedCommandMessage() {
        try {
            throw new UndefinedCommandException();
        } catch (UndefinedCommandException e) {
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private static void showByeMessage() {
        System.out.println("{\\__/}");
        System.out.println("(´^ω^)ノ Bye. Hope to see you again soon!");
        System.out.println("/ v v/");
    }

    private static void showWelcomeMessage() {

        String logo = "          @@@@@@@@@@@@@@@@%\n"
                + "       @@@%%%%%%%%%%%%%%%%%&@@\n"
                + "       @@%%%%%%%%%%###%#####%@@@@@@\n"
                + "        @@%%%%%##%@@@&@###@##@##@##@@\n"
                + "         @@@@&@####@###%@##@#@##&&#@@\n"
                + "        @@%@%%%@@##@@@@@@@@@@#/**@@@\n"
                + "         @@%@@@@@#*****************/@@\n"
                + "          /@@************#@@@@@@@@@@@@@@\n"
                + "          @@********%@@(******         *%@@\n"
                + "         @@(*******@@###*****            **@@\n"
                + "   @@@@@@@@((******@#####*****          ****@\n"
                + ",@@******@%((******@@######**************###@%\n"
                + "@@******%@((((******@@#####################@@\n"
                + "@@**/((((@((((********@@%###############&@@\n"
                + "@@(((((((@(((((***********(@@@@@@@@@&/**&@@\n"
                + "@@(((((((@(((((**************************@@\n"
                + "@@(((((((@#((((**************************@@\n"
                + "@@(((((((@&(((((*************************@@\n"
                + "@@(((((((@@((((((**********************((@@\n"
                + "@@(((((((@@(((((((((***************/(((((@/\n"
                + "@@(((((((@@(((((((((((((((((((((((((((((&@\n"
                + " @@((((((@@(((((((((((((((((((((((((((((@@\n"
                + "   @@@@@@@@((((((((((((@@@@@@@@@@@&(((((@@\n"
                + "         @@((((((((((((@%   @@((((((((((@@\n"
                + "         @@((((((((((((@    *@((((((((((@/\n"
                + "         %@(((((((((((@@     @%((((((((@@\n"
                + "         .@@((((((((((@@     @@@@@@@@@@.\n"
                + "             #@@@@@@*";

        //Greet
        System.out.println("Hello I'm Duke\n" + logo);
        System.out.println("What can I do for you?");
    }
}
