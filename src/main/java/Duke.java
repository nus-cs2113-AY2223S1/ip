import java.util.Scanner;


public class Duke {
    private static TaskList list = new TaskList();
    public static void main(String[] args) {
        final String DUKE_LOGO =  " ____        _\n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        final String CONV_START = DUKE_LOGO + "Hello! I'm Duke";
        final String CONV_END = "Bye. Hope to see you again soon!";

        printOutput(CONV_START);
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.println("What can I do for you?");
            String lineInput = in.nextLine();

//            System.out.print("\033[H\033[2J");
//            System.out.flush();

            if (lineInput.equals("bye")) {
                break;
            } else if (lineInput.equals("list")) {
                printOutput(list.getCompleteList());
            } else if (lineInput.startsWith("mark ")) {
                doMarkAction(lineInput);
            } else if (lineInput.startsWith("unmark ")) {
                doUnmarkAction(lineInput);
            } else if (lineInput.startsWith("todo ")) {
                doTodoAction(lineInput);
            } else if (lineInput.startsWith("deadline ")) {
                doDeadlineAction(lineInput);
            } else if (lineInput.startsWith("event ")) {
                doEventAction(lineInput);
            } else {
                doTaskAction(lineInput);
            }
        }
        printOutput(CONV_END);
    }

    private static void printOutput(String message) {
        final String SEPARATOR = "____________________________________________________________";
        System.out.println(SEPARATOR);
        System.out.println(message);
        System.out.println(SEPARATOR);
        System.out.println("");
    }

    private static void doMarkAction(String lineInput) {
        int itemNumber = Integer.parseInt(lineInput.split(" ")[1]);
        list.markCompleted(itemNumber, true);
        String message = "Nice! I've marked this task as done:\n"
                + list.getItemFromList(itemNumber);
        printOutput(message);
    }

    private static void doUnmarkAction(String lineInput) {
        int itemNumber = Integer.parseInt(lineInput.split(" ")[1]);
        list.markCompleted(itemNumber, false);
        String message = "OK, I've marked this task as not done yet:\n"
                + list.getItemFromList(itemNumber);
        printOutput(message);
    }

    private static void doTodoAction(String lineInput) {
        String item_description = lineInput.substring("todo ".length());
        int index = list.addTaskToList(item_description, TaskType.TODO);
        String output = "I got you, added a todo:\n"
                + list.getItemFromList(index + 1)
                + "\n Now you have " + (index + 1) + " tasks in the list.";
        printOutput(output);
    }

    private static void doDeadlineAction(String lineInput) {
        String item_description = lineInput.substring("deadline ".length());
        int index = list.addTaskToList(item_description, TaskType.DEADLINE);
        String output = "I got you, added a deadline:\n"
                + list.getItemFromList(index + 1)
                + "\n Now you have " + (index + 1) + " tasks in the list.";
        printOutput(output);
    }

    private static void doEventAction(String lineInput) {
        String item_description = lineInput.substring("event ".length());
        int index = list.addTaskToList(item_description, TaskType.EVENT);
        String output = "I got you, added a event:\n"
                + list.getItemFromList(index + 1)
                + "\n Now you have " + (index + 1) + " tasks in the list.";
        printOutput(output);
    }

    private static void doTaskAction(String lineInput) {
        int index = list.addTaskToList(lineInput, TaskType.OTHER);
        String output = "I got you, added a task:\n"
                + list.getItemFromList(index + 1)
                + "\n Now you have " + (index + 1) + " tasks in the list.";
        printOutput(output);
    }
}
