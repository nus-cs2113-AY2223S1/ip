package duke.parser;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.*;
import duke.ui.Ui;

import java.io.IOException;

public class Parser {

    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";
    private static final String DELETE = "delete";

    private static final String LIST = "list";
    private static final String BYE = "bye";
    private final TaskList tasks;
    private final String input;
    private final Ui ui;
    private final Storage storage;

    public Parser(TaskList tasks, String input, Ui ui, Storage storage) {
        this.tasks = tasks;
        this.input = input;
        this.ui = ui;
        this.storage = storage;
    }

    private static Todo prepareTodo(String input) throws DukeException {
        if (input.length() < 6) {
            throw new DukeException();
        }
        String description = input.substring(5);
        return new Todo(description);
    }

    private static Event prepareEvent(String input) throws DukeException {
        if (input.length() < 7 || !input.contains("/")) {
            throw new DukeException();
        }
        int endOfDescription = input.indexOf("/") - 1;
        String description = input.substring(6, endOfDescription);
        String at = input.substring(input.indexOf("/") + 1);
        return new Event(description, at);
    }

    private static Deadline prepareDeadline(String input) throws DukeException {
        if (input.length() < 10 || !input.contains("/")) {
            throw new DukeException();
        }
        int endOfDescription = input.indexOf("/") - 1;
        String description = input.substring(9, endOfDescription);
        String by = input.substring(input.indexOf("/") + 1);
        return new Deadline(description, by);
    }

    private static int prepareMark(String input) throws DukeException {
        String[] words = input.split(" ", 2);
        if (words.length != 2) {
            throw new DukeException();
        }
        return Integer.parseInt(words[1]) - 1;
    }

    private static int prepareUnmark(String input) throws DukeException {
        String[] words = input.split(" ", 2);
        if (words.length != 2) {
            throw new DukeException();
        }
        return Integer.parseInt(words[1]) - 1;
    }

    private static int prepareDelete(String input) throws DukeException {
        String[] words = input.split(" ", 2);
        if (words.length != 2) {
            throw new DukeException();
        }
        return Integer.parseInt(words[1]) - 1;
    }

    private void addCommand() {
        String[] command = input.split(" ", 2);
        Task task = null;
        switch (command[0]) {
        case TODO:
            try {
                task = prepareTodo(input);
                tasks.addTask(task);
                ui.printAddTask(tasks);
            } catch (DukeException e) {
                ui.printToUser("todo description cannot be empty.");
            }
            break;
        case EVENT:
            try {
                task = prepareEvent(input);
                tasks.addTask(task);
                ui.printAddTask(tasks);
            } catch (DukeException e) {
                ui.printToUser("Please key in a valid event input (missing '/' or missing description)");
            }
            break;
        case DEADLINE:
            try {
                task = prepareDeadline(input);
                tasks.addTask(task);
                ui.printAddTask(tasks);
            } catch (DukeException e) {
                ui.printToUser("Please key in a valid deadline input (missing '/' or missing description)");
            }
            break;
        default:
            break;
        }
        try {
            String toBeAppend = task.taskToString();
            storage.appendTask(toBeAppend);
        } catch (IOException e) {
            ui.printError("cannot update file");
        } catch (NullPointerException e) {
            ui.printError("error finding task");
        }
    }

    private void markCommand() {
        try {
            int taskNum = prepareMark(input);
            tasks.findTask(taskNum).markDone();
            ui.printMarkTask(tasks, taskNum);
            storage.writeFile(tasks);
        } catch (DukeException e) {
            ui.printError("Please input task number");
        } catch (IOException e) {
            ui.printError("cannot update file");
        }
    }

    private void unmarkCommand() {
        try {
            int taskNum = prepareUnmark(input);
            tasks.findTask(taskNum).markDone();
            ui.printUnmarkTask(tasks, taskNum);
            storage.writeFile(tasks);
        } catch (DukeException e) {
            ui.printError("Please input task number");
        } catch (IOException e) {
            ui.printError("cannot update file");
        }
    }

    private void deleteCommand() {
        try {
            int taskNum = prepareDelete(input);
            ui.printDeleteTask(tasks, taskNum);
            tasks.deleteTask(taskNum);
            storage.writeFile(tasks);
        } catch (DukeException e) {
            ui.printError("Please input task number");
        } catch (IOException e) {
            ui.printError("cannot update file");
        } catch (IndexOutOfBoundsException e) {
            ui.printError("please choose a valid task");
        }
    }

    public boolean exitCommand() {
        try {
            storage.writeFile(tasks);
            ui.printBye();
        } catch (IOException e) {
            ui.printError("unable to write to file");
        }
        return false;
    }

    public boolean handleCommand() {
        boolean isRun = true;
        String[] command = input.split(" ", 2);
        switch (command[0]) {
        case TODO:
        case DEADLINE:
        case EVENT:
            addCommand();
            break;
        case MARK:
            markCommand();
            break;
        case UNMARK:
            unmarkCommand();
            break;
        case DELETE:
            deleteCommand();
            break;
        case LIST:
            tasks.listTask();
            break;
        case BYE:
            isRun = exitCommand();
            break;
        default:
            ui.printError("what are you saying?");
            break;
        }
        return isRun;
    }
}
