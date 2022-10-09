import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void printLine() {
        System.out.println("    ____________________________________________________________");
    }
    public static void printWelcome() {
        printLine();
        System.out.println("    Hello! I'm Duke" + "\n" + "    What can I do for you?");
        printLine();
    }
    public static void printGoodbye() {
        printLine();
        System.out.println("    Bye. Hope to see you again soon!");
        printLine();
    }

    public static void printError() {
        printLine();
        System.out.println("    ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        printLine();
    }

    public static void printTodoError() {
        printLine();
        System.out.println("    ☹ OOPS!!! The description of a todo cannot be empty.");
        printLine();
    }
    public static void marking(ArrayList<Task>tasks, String command, boolean isMarked) {
        printLine();
        if (isMarked) {
            System.out.println("    Nice! I've marked this task as done:");
            command = command.replace("mark ", "");
        } else {
            System.out.println("    OK, I've marked this task as not done yet:");
            command = command.replace("unmark ", "");
        }
        //@@author {brian-vb}-reused
        //{Inspiration taken from chydarren, reused with minor modifications}
        int index = Integer.parseInt(command) - 1;
        tasks.get(index).setAsDone(isMarked);
        System.out.println("    " + tasks.get(index).getTaskDetails());
        //@@author
        printLine();
    }

    public static void printList(ArrayList<Task> tasks) {
        int numOfTasks = tasks.size();
        printLine();
        System.out.println("    Here are the tasks in your list:");
        //@@author {brian-vb}-reused
        //{Reused from chydarren with minor modifications}
        for (int counter = 0; counter < numOfTasks; counter++) {
            System.out.println("    " + (counter + 1) + "." + tasks.get(counter).getTaskDetails());
        }
        //@@author
        printLine();
    }

    public static void addTodo(ArrayList<Task> tasks, String command) {
        String instruction = command.substring(5);
        if (instruction.equals("todo")) {
            printTodoError();
        } else {
            Todo newTask = new Todo(instruction);
            tasks.add(newTask);
            System.out.println("    Got it. I've added this task:");
            System.out.println("    " + newTask.getTaskDetails());
            System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
            printLine();
        }
    }

    public static Command parseCommand(String input) {
        Command mainCommand;
        if (input.equals("list")) {
            mainCommand = Command.LIST;
        } else if (input.equals("bye")) {
            mainCommand = Command.BYE;
        } else if (input.startsWith("mark "))  {
            mainCommand = Command.MARK;
        } else if (input.startsWith("unmark ")) {
            mainCommand = Command.UNMARK;
        } else if (input.startsWith("todo ")) {
            mainCommand = Command.TODO;
        } else if (input.startsWith("deadline ")) {
            mainCommand = Command.DEADLINE;
        } else if (input.startsWith("event ")) {
            mainCommand = Command.EVENT;
        } else {
            mainCommand = Command.EMPTY;
        }
        return mainCommand;
    }

    public static void addDeadline(ArrayList<Task> tasks, String command) {
        printLine();
        String instruction;
        instruction = command.substring(9);
        String description;
        String deadline;
        description = instruction.split(" /by ")[0];
        deadline = instruction.split(" /by ")[1];
        Deadline newDeadline = new Deadline(description, deadline);
        tasks.add(newDeadline);
        System.out.println("    Got it. I've added this task:");
        System.out.println("    " + newDeadline.getTaskDetails());
        System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
        printLine();

    }
    public static void addEvent(ArrayList<Task> tasks, String command) {
        printLine();
        String instruction;
        instruction = command.substring(6);
        String description;
        String timeline;
        description = instruction.split(" /at ")[0];
        timeline = instruction.split(" /at ")[1];
        String startTime;
        String endTime;
        startTime = timeline.split("-")[0];
        endTime = timeline.split("-")[1];
        Event newEvent = new Event(description, startTime, endTime);
        tasks.add(newEvent);
        System.out.println("    Got it. I've added this task:");
        System.out.println("    " + newEvent.getTaskDetails());
        System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
        printLine();

    }
    public static void main(String[] args) {
        printWelcome();
        Scanner input = new Scanner(System.in);
        String command;
        ArrayList<Task> tasks = new ArrayList<>();
        boolean isRunning = true;
        while (isRunning) {
            command = input.nextLine();
            Command order = parseCommand(command);
            switch (order) {
                case LIST:
                    printList(tasks);
                    break;
                case BYE:
                    printGoodbye();
                    isRunning = false;
                    break;
                case MARK:
                    marking(tasks, command, true);
                    break;
                case UNMARK:
                    marking(tasks, command, false);
                    break;
                case TODO:
                    addTodo(tasks, command);
                    break;
                case DEADLINE:
                    addDeadline(tasks, command);
                    break;
                case EVENT:
                    addEvent(tasks, command);
                    break;
                default:
                    printError();
            }
        }
    }
}
