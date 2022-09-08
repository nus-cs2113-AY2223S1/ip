package task;

public class TaskList {
    public static final String COMMAND_MARKED = "mark";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    Task[] listOfTasks = new Task[100];
    private int numberOfTasks = 0;
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
    public void markStatus(String input) {
        String[] instructions = input.split(" ");
        int index = Integer.parseInt(instructions[1]);
        printLine();
        if (instructions[0].equals(COMMAND_MARKED)) {
            listOfTasks[index - 1].isDone = true;
            System.out.println("Nice! I've marked this task as done:");
        } else {
            listOfTasks[index-1].isDone = false;
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println("  " + listOfTasks[index-1]);
        printLine();
    }
    public void printList() {
        printLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < numberOfTasks; i++) {
            System.out.print((i + 1) + ".");
            System.out.println(listOfTasks[i].toString());
        }
        printLine();
    }
    public void start() {
        printLine();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        printLine();
    }
    public void end() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }
    public void addTask(String input) {
        printLine();
        String[] instructions = input.split(" ");
        if (instructions[0].equals(COMMAND_DEADLINE)) {
            String deadlineTask = input.replace("deadline ", "");
            String[] deadlineInstructions = deadlineTask.split(" /by ");
            listOfTasks[numberOfTasks] = new Deadline(deadlineInstructions[0],deadlineInstructions[1]);
            numberOfTasks++;
        }
        else if (instructions[0].equals(COMMAND_EVENT)) {
            String eventTask = input.replace("event ", "");
            String[] deadlineInstructions = eventTask.split(" /at ");
            listOfTasks[numberOfTasks] = new Event(deadlineInstructions[0],deadlineInstructions[1]);
            numberOfTasks++;
        }
        else if (instructions[0].equals(COMMAND_TODO)) {
            String todoTask = input.replace("todo ", "");
            listOfTasks[numberOfTasks] = new Todo(todoTask);
            numberOfTasks++;
        }
        else {
            System.out.println("Invalid input. Please try again.");
        }
        System.out.println("Now you have " + numberOfTasks + " tasks in the list.");
        printLine();
    }
}
