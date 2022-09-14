import exception.InvalidArgumentsException;
import exception.InvalidCommandException;
import exception.NotEnoughArgumentsException;
import exception.TaskDoesNotExistException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.util.ArrayList;

public class ConsoleCommands {
    public static final String COMMAND_MARKED = "mark";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_DEADLINE_BY = "/by";
    public static final String COMMAND_EVENT_AT = "/at";
    private int numberOfTasks = 0;
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
    public static void markStatus(String input, ArrayList<Task> Array) throws TaskDoesNotExistException {
        printLine();
        String[] instructions = input.split(" ");
        int index = Integer.parseInt(instructions[1]);
        if (index >= Array.size()) {
            throw new TaskDoesNotExistException();
        }
        if (instructions[0].equals(COMMAND_MARKED)) {
            Array.get(index - 1).isDone = true;
            System.out.println("Nice! I've marked this task as done:");
        } else {
            Array.get(index-1).isDone = false;
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println("  " + Array.get(index-1));
        printLine();
    }
    public static void printList(ArrayList<Task> Array) {
        printLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < Array.size(); i++) {
            System.out.print((i + 1) + ".");
            System.out.println(Array.get(i).toString());
        }
        printLine();
    }
    public static void start() {
        printLine();
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        printLine();
    }
    public static void end() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    public static void deleteTask(String input, ArrayList<Task> Array) throws TaskDoesNotExistException {
        printLine();
        String[] instructions = input.split(" ");
        int index = Integer.parseInt(instructions[1]) - 1;
        if (index > Array.size()) {
            throw new TaskDoesNotExistException();
        }
        System.out.println("Noted. I've removed this task:");
        System.out.println(Array.get(index).toString());
        Array.remove(index);
        System.out.println("Now you have " + Array.size() + " tasks in the list.");
        printLine();
    }
    public static void addTask(String input, ArrayList<Task> Array) throws InvalidCommandException, InvalidArgumentsException, NotEnoughArgumentsException {
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
            Array.add(new Deadline(deadlineInstructions[0],deadlineInstructions[1]));
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
            Array.add(new Event(eventInstructions[0],eventInstructions[1]));
        }
        else if (instructions[0].equals(COMMAND_TODO)) {
            if (input.equals(COMMAND_TODO)) {
                throw new NotEnoughArgumentsException();
            }
            String todoTask = input.replace("todo ", "");
            Array.add(new Todo(todoTask));
        }
        else {
            throw new InvalidCommandException();
        }
        System.out.println("Now you have " + Array.size() + " tasks in the list.");
        printLine();
    }
}
