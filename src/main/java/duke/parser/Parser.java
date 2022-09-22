package duke.parser;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.Task;
import duke.ui.Ui;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";
    private static final String DELETE = "delete";
    private static final String LIST = "list";
    private static final String FIND = "find";
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

    /**
     * parses the input to return a Todo task.
     * if input is not complete, prompt user for input again
     * @param input input of user to be parsed
     * @return Todo task
     * @throws DukeException if input is incomplete (input length is < 6)
     */
    private static Todo prepareTodo(String input) throws DukeException {
        if (input.length() < 6) {
            throw new DukeException();
        }
        String description = input.substring(5);
        return new Todo(description);
    }

    /**
     * parses the input to return an Event task
     * @param input input of user to be parsed into Event
     * @return Event task
     * @throws DukeException if input is incomplete (input does not contain /)
     */
    private static Event prepareEvent(String input) throws DukeException {
        if (!input.contains("/at")) {
            throw new DukeException();
        }
        String[] command = input.split(" ", 2);
        String[] eventInput = command[1].split("/at", 2);
        String description = eventInput[0].trim();
        if (description.length() == 0) {
            throw new DukeException();
        }
        String at = eventInput[1].trim();
        try {
            LocalDate dateOfEvent = LocalDate.parse(at);
            String eventDate = dateOfEvent.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            return new Event(description, eventDate);
        } catch (DateTimeParseException e) {
            System.out.println("date is not of valid format and will not be treated as a date");
        }
        return new Event(description, at);
    }

    /**
     * parses the input to return a Deadline task
     * checks if input date is of the correct format and will be treated as LocalDate
     * else input date will be treated as a String
     * @param input input of user to be parsed into Deadline
     * @return Deadline task
     * @throws DukeException if input is incomplete (input does not contain /)
     */
    private static Deadline prepareDeadline(String input) throws DukeException {
        if (!input.contains("/by")) {
            throw new DukeException();
        }
        String[] command = input.split(" ", 2);
        String[] deadlineInput= command[1].split("/by", 2);
        String description = deadlineInput[0].trim();
        if (description.length() == 0) {
            throw new DukeException();
        }
        String by = deadlineInput[1].trim();
        String[] date = by.split(" ", 2);
        try {
            LocalDate dateOfDeadline = LocalDate.parse(date[0]);
            String deadlineDate = dateOfDeadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            return new Deadline(description, deadlineDate + " " + date[1]);

        } catch (DateTimeParseException e) {
            System.out.println("date is not of valid format and will not be treated as a date");
        }
        return new Deadline(description, by);
    }

    /**
     * parses the input to return index of task to be marked
     * @param input user input of task to be marked
     * @return index of task
     * @throws DukeException if task number is not given
     */
    private static int prepareMark(String input) throws DukeException {
        String[] words = input.split(" ", 2);
        if (words.length != 2) {
            throw new DukeException();
        }
        return Integer.parseInt(words[1]) - 1;
    }

    /**
     * parses the input to return index of task to be unmarked
     * @param input user input of task to be unmarked
     * @return index of task
     * @throws DukeException if task number is not given
     */
    private static int prepareUnmark(String input) throws DukeException {
        String[] words = input.split(" ", 2);
        if (words.length != 2) {
            throw new DukeException();
        }
        return Integer.parseInt(words[1]) - 1;
    }

    /**
     * parses input to return word to be searched
     * @param input user input of task to be found
     * @return keyword to be searched
     */
    private static String prepareFind(String input) {
        String[] words = input.split(" ", 2);
        return words[1];
    }


    /**
     * parses the input to return index of task to be deleted
     * @param input user input of task to be deleted
     * @return index of task
     * @throws DukeException if task number is not given
     */
    private static int prepareDelete(String input) throws DukeException {
        String[] words = input.split(" ", 2);
        if (words.length != 2) {
            throw new DukeException();
        }
        return Integer.parseInt(words[1]) - 1;
    }

    /**
     * parses the input into command and task to be added
     * adds respective task based on command to TaskList tasks
     * if command is todo, add todo
     * if command is event, add event
     * if command is deadline, add deadline
     * appends task to file after added to tasks
     */
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
                ui.printToUser("Please key in a valid event input (missing '/at' or missing description)");
            }
            break;
        case DEADLINE:
            try {
                task = prepareDeadline(input);
                tasks.addTask(task);
                ui.printAddTask(tasks);
            } catch (DukeException e) {
                ui.printToUser("Please key in a valid deadline input (missing '/by' or missing description)");
            }
            break;
        default:
            break;
        }
        try {
            String toBeAppend = task.taskToString();
            storage.appendTask(toBeAppend);
        } catch (IOException | NullPointerException e) {
            ui.printError("cannot update file");
        }
    }

    /**
     * parses the input and returns the index of task to be marked as done
     * marks task as done
     * writes to file after task is marked
     */
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

    /**
     * parses the input and returns the index of task to be unmarked
     * unmarks the task
     * writes to file after task is unmarked
     */
    private void unmarkCommand() {
        try {
            int taskNum = prepareUnmark(input);
            tasks.findTask(taskNum).markUndone();
            ui.printUnmarkTask(tasks, taskNum);
            storage.writeFile(tasks);
        } catch (DukeException e) {
            ui.printError("Please input task number");
        } catch (IOException e) {
            ui.printError("cannot update file");
        }
    }

    /**
     * parses the input and returns index of task to be deleted.
     * deletes the task from tasks
     * writes updated TaskList to file
     */
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

    /**
     * parses the input to return keyword to be searched
     * adds tasks found to a TaskList and prints the tasks found
     */
    private void findCommand() {
        String word = prepareFind(input);
        TaskList tasksFound = tasks.findTasksBySearch(word);
        ui.printTasksFound(tasksFound);
    }

    /**
     * prints bye and exits the program by returning false to Duke
     * @return false to stop the program
     */
    public boolean exitCommand() {
        try {
            storage.writeFile(tasks);
            ui.printBye();
        } catch (IOException e) {
            ui.printError("unable to write to file");
        }
        return false;
    }

    /**
     * Function to handle the command input by user, parses the input and carries out command based on the user input
     * if case: BYE returns false to exit the program, else return true
     * @return a boolean isRun. False if case: bye, otherwise returns true
     */
    public boolean handleCommand() {
        boolean isRunning = true;
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
            ui.printListOfTasks(tasks);
            break;
        case FIND:
            findCommand();
            break;
        case BYE:
            isRunning = exitCommand();
            break;
        default:
            ui.printError("what are you saying?");
            break;
        }
        return isRunning;
    }
}
