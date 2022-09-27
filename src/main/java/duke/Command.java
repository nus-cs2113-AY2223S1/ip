package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;
import java.util.stream.Collectors;


public class Command {

    public static final String EVENT_DESCRIPTION_SEPARATOR = " /at ";
    public static final String DEADLINE_DESCRIPTION_SEPARATOR = " /by ";

    private Storage storage;
    private Ui ui;
    private TaskList taskList;
    private Parser parser;

    public Command() {
    }

    public Command(Storage storage, Ui ui, TaskList taskList, Parser parser) {
        this.storage = storage;
        this.ui = ui;
        this.taskList = taskList;
        this.parser = parser;
    }

    /**
     * Returns the feedback to the user after executing the command given.
     *
     * @param commandTypeAndArguments command given by the user.
     * @return feedback after executing the command.
     * @throws EmptyDescriptionException if description of the command is empty.
     * @throws InvalidCommandException   if the command is invalid.
     * @throws DukeException             if there is error accessing local txt file.
     */
    public String execute(String[] commandTypeAndArguments) throws EmptyDescriptionException, InvalidCommandException, DukeException {
        String commandType = parser.getCommand(commandTypeAndArguments);
        String commandArguments = this.parser.getCommandArguments(commandTypeAndArguments);
        switch (commandType) {
        case "list":
            return executeListCommand();
        case "mark":
            return executeMark(true, commandArguments);
        case "unmark":
            return executeMark(false, commandArguments);
        case "todo":
            return executeCreateToDo(commandArguments);
        case "deadline":
            return executeCreateDeadline(commandArguments);
        case "event":
            return executeCreateEvent(commandArguments);
        case "delete":
            return executeDelete(commandArguments);
        case "find":
            return executeFind(commandArguments);
        case "bye":
            exitProgramme();
        default:
            throw new InvalidCommandException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private String executeFind(String commandArguments) throws EmptyDescriptionException {
        handleEmptyDescriptionException(commandArguments.length() == 0, "The description of a find command cannot be empty.");
        ArrayList<Task> filteredTasks = getFilteredTasks(commandArguments);
        ui.showMatchingTasks(filteredTasks);
        return "";
    }

    private ArrayList<Task> getFilteredTasks(String commandArguments) {
        return (ArrayList<Task>) taskList.getTasks().stream()
                .filter(t -> t.getDescription().contains(commandArguments)).collect(Collectors.toList());
    }

    private static void handleEmptyDescriptionException(boolean commandArguments, String message) throws EmptyDescriptionException {
        if (commandArguments) {
            throw new EmptyDescriptionException(message);
        }
    }


    /**
     * Executes the delete command and returns a feedback string to the user
     *
     * @param commandArguments Index of the task to be deleted.
     * @return Feedback to the user.
     * @throws DukeException If error accessing and writing to txt file.
     */
    private String executeDelete(String commandArguments) throws DukeException {
        int taskIndex = Integer.parseInt(commandArguments) - 1;
        String feedback = ui.getInitialDeleteFeedback(taskIndex, taskList);
        taskList.removeTask(taskIndex);
        storage.overWriteDukeTxt(taskList.getTasks());
        return ui.getFinalDeleteFeedback(feedback, taskList);
    }



    /**
     * Executes the create event command
     *
     * @param commandArguments  Arguments given by the user.
     * @return Feedback to the user.
     * @throws EmptyDescriptionException If arguments string is empty or the number of arguments < 2
     * @throws DukeException If error accessing or writing to txt file.
     */
    private String executeCreateEvent(String commandArguments) throws EmptyDescriptionException, DukeException {
        handleEmptyDescriptionException(commandArguments.length() == 0, "The description of an event cannot be empty");
        String[] separatedArguments = parser.getDescriptionAndTime(commandArguments, EVENT_DESCRIPTION_SEPARATOR);
        handleEmptyDescriptionException(separatedArguments.length < 2, "The description of an event cannot be empty");
        return createEvent(separatedArguments);
    }

    /**
     * Returns a feedback to the user after creating an event and writing to txt file.
     *
     * @param separatedArguments Arguments for the Event.
     * @return feedback to the user.
     * @throws DukeException If error accessing or writing to txt file.
     */
    private String createEvent(String[] separatedArguments) throws DukeException {
        Event newEvent = new Event(separatedArguments[0], separatedArguments[1]);
        taskList.addTask(newEvent);
        storage.appendToFile(newEvent.toString());
        return ui.getAddTaskFeedback(newEvent, taskList);
    }



    /**
     * Executes the create deadline command
     *
     * @param commandArguments Arguments given by the user.
     * @return feedback to the user.
     * @throws EmptyDescriptionException If arguments string is empty or the number of arguments < 2
     * @throws DukeException If error accessing or writing to txt file.
     */

    private String executeCreateDeadline(String commandArguments) throws EmptyDescriptionException, DukeException {
        handleEmptyDescriptionException(commandArguments.length() == 0, "The description  of a deadline cannot be empty");
        String[] separatedArguments = parser.getDescriptionAndTime(commandArguments, DEADLINE_DESCRIPTION_SEPARATOR);
        handleEmptyDescriptionException(separatedArguments.length < 2, "The description of a deadline cannot be empty");
        return createDeadline(separatedArguments);
    }

    /**
     * Returns a feedback to the user after creating a deadline and writing to the txt file.
     *
     * @param separatedArguments Arguments for the deadline.
     * @return Feedback to the user.
     * @throws DukeException If error accessing or writing to the txt file.
     */
    private String createDeadline(String[] separatedArguments) throws DukeException {
        Deadline newDeadline = new Deadline(separatedArguments[0], separatedArguments[1]);
        taskList.addTask(newDeadline);
        storage.appendToFile(newDeadline.toString());
        return ui.getAddTaskFeedback(newDeadline, taskList);
    }


    /**
     * Execute create todo command.
     *
     * @param commandArgument Command arguments given by the user.
     * @return Feedback to the user.
     * @throws EmptyDescriptionException If the commandArgument is empty.
     * @throws DukeException If error accessing or writing to the txt file.
     */

    private String executeCreateToDo(String commandArgument) throws EmptyDescriptionException, DukeException {
        handleEmptyDescriptionException(commandArgument.length() == 0, "The description of a todo cannot be empty.");
        ToDo newToDo = new ToDo(commandArgument);
        taskList.addTask(newToDo);
        storage.appendToFile(newToDo.toString());
        return ui.getAddTaskFeedback(newToDo, taskList);
    }

    /**
     * Updates the status of the task.
     *
     * @param isMarkAsDone The status of the task to be updated to.
     * @param commandArgument Command argument given by the user.
     * @return Feedback to the user.
     * @throws DukeException If error accessing or writing to the txt file.
     */
    private String executeMark(boolean isMarkAsDone, String commandArgument) throws DukeException {
        int taskIndex = Integer.parseInt(commandArgument) - 1;
        Task targetTask = taskList.getTasks().get(taskIndex);
        String feedback;
        if (isMarkAsDone) {
            targetTask.setDone(true);
            feedback = ui.getMarkMessage();
        } else {
            targetTask.setDone(false);
            feedback = ui.getUnmarkMessage();
        }
        storage.overWriteDukeTxt(taskList.getTasks());
        return  ui.appendFeedbackForUpdatingStatus(feedback, targetTask);
    }


    private String executeListCommand() {
        ui.showTaskList(taskList.getTasks());
        return "";
    }

    public void exitProgramme() {
        ui.showExitMessage();
        System.exit(0);
    }

    /**
     * Creates event task
     *
     * @param words Description for the event
     * @return Event
     */

    public Event createEvent(ArrayList<String> words) {
        Event event = new Event(words.get(2), words.get(3));
        updateStatus(event, words.get(1));
        return event;
    }

    /**
     * Creates deadline
     *
     * @param words Description for the deadline
     * @return Deadline
     */
    public Deadline createDeadline(ArrayList<String> words) {
        Deadline deadline = new Deadline(words.get(2), words.get(3));
        updateStatus(deadline, words.get(1));
        return deadline;
    }

    /**
     * Creates todo
     *
     * @param words Description for the todo
     * @return Todo
     */

    public ToDo createToDo(ArrayList<String> words) {
        ToDo todo = new ToDo(words.get(2));
        updateStatus(todo, words.get(1));
        return todo;
    }

    private void updateStatus(Task task, String taskStatus) {
        if (taskStatus.equals("1")) {
            task.setDone(true);
        }
    }
}
