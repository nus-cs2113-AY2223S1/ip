package task;
import exception.InvalidArgumentsException;
import exception.InvalidCommandException;
import exception.NotEnoughArgumentsException;
import exception.TaskDoesNotExistException;

public class TaskList {
    public static final String COMMAND_MARKED = "mark";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_DEADLINE_BY = "/by";
    public static final String COMMAND_EVENT_AT = "/at";
    Task[] listOfTasks = new Task[100];
    private int numberOfTasks = 0;
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
    public void markStatus(String input) throws TaskDoesNotExistException {
        printLine();
        String[] instructions = input.split(" ");
        int index = Integer.parseInt(instructions[1]);
        if (index > numberOfTasks) {
            throw new TaskDoesNotExistException();
        }
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
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        printLine();
    }
    public void end() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }
    public void addTask(String input) throws InvalidCommandException, InvalidArgumentsException, NotEnoughArgumentsException {
        printLine();
        String[] instructions = input.split(" ");
        if (instructions[0].equals(COMMAND_DEADLINE)) {
            String deadlineTask = input.replace("deadline ", "");
            if (!deadlineTask.contains(COMMAND_DEADLINE_BY)) {
                throw new InvalidArgumentsException();
            }
            String[] deadlineInstructions = deadlineTask.split(" /by ");
            if (deadlineInstructions.length != 2) {
                throw new NotEnoughArgumentsException();
            }
            listOfTasks[numberOfTasks] = new Deadline(deadlineInstructions[0],deadlineInstructions[1]);
            numberOfTasks++;
        }
        else if (instructions[0].equals(COMMAND_EVENT)) {
            String eventTask = input.replace("event ", "");
            if (!eventTask.contains(COMMAND_EVENT_AT)) {
                throw new InvalidArgumentsException();
            }
            String[] eventInstructions = eventTask.split(" /at ");
            if (eventInstructions.length != 2) {
                throw new NotEnoughArgumentsException();
            }
            listOfTasks[numberOfTasks] = new Event(eventInstructions[0],eventInstructions[1]);
            numberOfTasks++;
        }
        else if (instructions[0].equals(COMMAND_TODO)) {
            if (input.equals(COMMAND_TODO)) {
                throw new NotEnoughArgumentsException();
            }
            String todoTask = input.replace("todo ", "");
            listOfTasks[numberOfTasks] = new Todo(todoTask);
            numberOfTasks++;
        }
        else {
            throw new InvalidCommandException();
        }
        System.out.println("Now you have " + numberOfTasks + " tasks in the list.");
        printLine();
    }
}
