package consoleCommands;

import exception.TaskDoesNotExistException;
import exception.InvalidCommandException;
import exception.InvalidFileDataException;
import exception.InvalidArgumentsException;
import exception.NotEnoughArgumentsException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class ConsoleCommands {
    public static final String COMMAND_MARKED = "mark";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_DEADLINE_BY = "/by";
    public static final String COMMAND_EVENT_AT = "/at";
    public static final String ADDED_MESSAGE = "Got it. I've added this task:";
    public static final String DELETED_MESSAGE = "Noted. I've removed this task:";
    public static final String MARKED_MESSAGE = "Nice! I've marked this task as done:";
    public static final String UNMARKED_MESSAGE = "OK, I've marked this task as not done yet:";
    public static final String HELLO_MESSAGE = "Hello! I'm Duke\n" + "What can I do for you?";
    public static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
    public static void markStatus(String input, ArrayList<Task> taskList)
            throws TaskDoesNotExistException {
        printLine();
        String[] instructions = input.split(" ");
        int index = Integer.parseInt(instructions[1]);
        if (index > taskList.size()) {
            throw new TaskDoesNotExistException();
        }
        if (instructions[0].equals(COMMAND_MARKED)) {
            taskList.get(index-1).isDone = true;
            System.out.println(MARKED_MESSAGE);
        } else {
            taskList.get(index-1).isDone = false;
            System.out.println(UNMARKED_MESSAGE);
        }
        System.out.println("  " + taskList.get(index-1));
        printLine();
    }
    public static void printList(ArrayList<Task> taskList) {
        printLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.print((i + 1) + ".");
            System.out.println(taskList.get(i).toString());
        }
        printLine();
    }
    public static void start(String filePath, ArrayList<Task> taskList)
            throws FileNotFoundException, InvalidFileDataException {
        printLine();
        FileCommands.readFromFile(filePath, taskList);
        System.out.println(HELLO_MESSAGE);
        printLine();
    }
    public static void end(String filePath, String tempFilePath, ArrayList<Task> taskList)
            throws IOException {
        printLine();
        FileCommands.writeToFile(filePath, tempFilePath, taskList);
        System.out.println(BYE_MESSAGE);
        printLine();
    }

    public static void deleteTask(String input, ArrayList<Task> taskList)
            throws TaskDoesNotExistException {
        printLine();
        String[] instructions = input.split(" ");
        int index = Integer.parseInt(instructions[1]) - 1;
        if (index > taskList.size()) {
            throw new TaskDoesNotExistException();
        }
        System.out.println(DELETED_MESSAGE);
        System.out.println("  " + taskList.get(index).toString());
        taskList.remove(index);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        printLine();
    }
    public static void addTask(String input, ArrayList<Task> taskList)
            throws InvalidCommandException, InvalidArgumentsException, NotEnoughArgumentsException {
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
            taskList.add(new Deadline(deadlineInstructions[0],deadlineInstructions[1]));
        } else if (instructions[0].equals(COMMAND_EVENT)) {
            String eventTask = input.replace("event ", "");
            if (!eventTask.contains(COMMAND_EVENT_AT)) {
                throw new InvalidArgumentsException();
            }
            String[] eventInstructions = eventTask.split(" /at ");
            if (eventInstructions.length != 2) {
                throw new NotEnoughArgumentsException();
            }
            taskList.add(new Event(eventInstructions[0],eventInstructions[1]));
        } else if (instructions[0].equals(COMMAND_TODO)) {
            if (input.equals(COMMAND_TODO)) {
                throw new NotEnoughArgumentsException();
            }
            String todoTask = input.replace("todo ", "");
            taskList.add(new Todo(todoTask));
        } else {
            throw new InvalidCommandException();
        }
        System.out.println(ADDED_MESSAGE);
        System.out.println("  " + taskList.get(taskList.size() - 1).toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        printLine();
    }
}
