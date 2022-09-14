package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.io.IOException;


public class Command {
    private final TaskList tasks;
    private final String command;
    public static final String DIVIDER = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    public static final String SPACER = "  ";

    private final String filePath;
    public Command(String command, TaskList tasks, String filePath) {
        this.command = command;
        this.tasks = tasks;
        this.filePath = filePath;
    }

    private static void markCommand(String input, TaskList tasks) throws DukeException {
        String[] words = input.split(" ");
        int num = Integer.parseInt(words[1]);
        if (num <= (tasks.getSize())) {
            tasks.findTask(num - 1).markDone();
            System.out.println("that was fast\n" + SPACER + tasks.findTask(num - 1).toString() + System.lineSeparator()
                    + DIVIDER);
        } else {
            throw new DukeException();
        }
    }

    private static void unmarkCommand(String input, TaskList tasks) throws DukeException {
        String[] words = input.split(" ");
        int num = Integer.parseInt(words[1]);
        if (num <= (tasks.getSize())) {
            tasks.findTask(num - 1).markUndone();
            System.out.println("can you make up your mind\n" + SPACER + tasks.findTask(num - 1).toString() + System.lineSeparator()
                    + DIVIDER);
        } else {
            throw new DukeException();
        }
    }

    private static Deadline deadlineCommand(String input) throws DukeException {
        if (input.length() < 10 || !input.contains("/")) {
            throw new DukeException();
        }
        int endOfDescription = input.indexOf("/") - 1;
        String description = input.substring(9, endOfDescription);
        String by = input.substring(input.indexOf("/") + 1);
        return new Deadline(description, by);
    }

    private static Todo todoCommand(String input) throws DukeException {
        if (input.length() < 6) {
            throw new DukeException();
        }
        String description = input.substring(5);
        return new Todo(description);
    }

    private static Event eventCommand(String input) throws DukeException {
        if (input.length() < 7 || !input.contains("/")) {
            throw new DukeException();
        }
        int endOfDescription = input.indexOf("/") - 1;
        String description = input.substring(6, endOfDescription);
        String at = input.substring(input.indexOf("/") + 1);
        return new Event(description, at);
    }

    private static void deleteCommand(TaskList tasks, String command) throws DukeException {
        String[] words = command.split(" ");
        int num = Integer.parseInt(words[1]);
        if (num <= tasks.getSize()) {
            System.out.println("task deleted" + System.lineSeparator() + SPACER + tasks.findTask(num - 1).toString()
                    + System.lineSeparator() + "you still have " + (tasks.getSize() - 1) + " tasks left" + System.lineSeparator()
                    + DIVIDER);
            tasks.deleteTask(num - 1);
        } else {
            throw new DukeException();
        }
    }

    private static void printStatement(TaskList tasks) {
        System.out.println("task added" + System.lineSeparator() + SPACER + tasks.findTask(tasks.getSize() - 1).toString()
                + System.lineSeparator() + "you still have " + (tasks.getSize()) + " tasks left" + System.lineSeparator()
                + DIVIDER);
    }

    public void handleCommand() {
        int numOfTasks = tasks.getSize();
        String[] input = command.split(" ", 2);
        String mainCommand = input[0];
        Storage storage = new Storage(filePath);
        switch (mainCommand) {
        case "list":
            tasks.listTask();
            break;
        case "mark":
            try {
                markCommand(command, tasks);
                storage.writeFile(tasks);
            } catch (DukeException e) {
                System.out.println("you only have " + (numOfTasks) + " tasks");
            } catch (IOException e) {
                System.out.println("cannot update file");
            }
            break;
        case "unmark":
            try {
                unmarkCommand(command, tasks);
                storage.writeFile(tasks);
            } catch (DukeException e) {
                System.out.println("you only have " + (numOfTasks) + " tasks");
            } catch (IOException e) {
                System.out.println("cannot update file");
            }
            break;
        case "deadline":
            try {
                tasks.addTask(deadlineCommand(command));
                storage.appendTask(deadlineCommand(command).taskToString());
                printStatement(tasks);
            } catch (DukeException e) {
                System.out.println("Please key in a valid deadline input (missing '/' or missing description)");
            } catch (IOException e) {
                System.out.println("cannot append task");
            }
            break;
        case "todo":
            try {
                tasks.addTask(todoCommand(command));
                storage.appendTask(todoCommand(command).taskToString());
                printStatement(tasks);
            } catch (DukeException e) {
                System.out.println("todo description cannot be empty.");
            } catch (IOException e) {
                System.out.println("cannot append task");
            }
            break;
        case "event":
            try {
                tasks.addTask(eventCommand(command));
                storage.appendTask(eventCommand(command).taskToString());
                printStatement(tasks);
            } catch (DukeException e) {
                System.out.println("Please key in a valid deadline input (missing '/' or missing description)");
            } catch (IOException e) {
                System.out.println("cannot append task");
            }
            break;
        case "delete":
            try {
                deleteCommand(tasks, command);
                storage.writeFile(tasks);
            } catch (DukeException e) {
                System.out.println("you only have " + numOfTasks + " tasks");
            } catch (IOException e) {
                System.out.println("cannot update file");
            }
            break;
        default:
            System.out.println("What are you saying?\n" + DIVIDER);
            break;
        }
    }
}
