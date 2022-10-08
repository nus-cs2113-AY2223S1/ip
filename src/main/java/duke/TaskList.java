package duke;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Contains the task list and the methods used to
 * insert, add, mark, unmark, delete and find tasks.
 */
public class TaskList {
    public static ArrayList<Task> tasks = new ArrayList<>();

    public TaskList(ArrayList<Task> tasks) {
        TaskList.tasks = tasks;
    }

    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Executes the command given by the user to update the list of tasks.
     * The specific command is interpreted by the Parser class beforehand.
     *
     * @param command The specific command to be executed.
     * @param inputLine The user input containing specifics related to the task.
     */
    public void runTaskCommand(Command command, String inputLine) {
        Ui ui = new Ui();
        switch (command) {
        case DEADLINE:
            insertDeadlineTask(inputLine, tasks);
            break;
        case TODO:
            insertToDoTask(inputLine, tasks);
            break;
        case EVENT:
            insertEventTask(inputLine, tasks);
            break;
        case LIST:
            ui.printTaskList(tasks);
            break;
        case MARK:
            markTask(inputLine, tasks);
            break;
        case UNMARK:
            unmarkTask(inputLine, tasks);
            break;
        case DELETE:
            deleteTask(inputLine, tasks);
            break;
        case EXIT:
            ui.printExitingMessage();
            break;
        case FIND:
            findTask(inputLine, tasks);
            break;
        default:
            ui.printInvalidCommandMessage();
            break;
        }
    }

    private void findTask(String inputLine, ArrayList<Task> tasks) {
        Ui ui = new Ui();
        try {
            String[] keywords = inputLine.split(" ");
            ArrayList<Task> filteredTasks = new ArrayList<>();
            for (Task taskToCheck : tasks) {
                if (taskToCheck.task.contains(keywords[1])) {
                    filteredTasks.add(taskToCheck);
                }
            }
            ui.printFilteredTaskList(filteredTasks);
        } catch (ArrayIndexOutOfBoundsException exception) {
            ui.printEmptyTaskDescriptionMessage();
        }
    }

    private void insertDeadlineTask(String inputLine, ArrayList<Task> tasks) {
        Ui ui = new Ui();
        try {
            String deadlineSpecifics = findTaskSpecifics(inputLine);
            String deadline = inputLine.substring(inputLine.indexOf("/by") + 3);
            addTask(new Deadline(deadlineSpecifics, false, deadline));
            ui.printDefaultTaskResponseMessage(tasks.size(), tasks);
        } catch (IllegalTaskException exception) {
            ui.printEmptyTaskDescriptionMessage();
        }
    }

    private void insertToDoTask(String inputLine, ArrayList<Task> tasks) {
        Ui ui = new Ui();
        try {
            String todoSpecifics = inputLine.substring(inputLine.indexOf(" "));
            addTask(new Todo(todoSpecifics, false));
            ui.printDefaultTaskResponseMessage(tasks.size(), tasks);
        } catch (StringIndexOutOfBoundsException exception) {
            ui.printEmptyTaskDescriptionMessage();
        }
    }

    private void insertEventTask(String inputLine, ArrayList<Task> tasks) {
        Ui ui = new Ui();
        try {
            String eventSpecifics = findTaskSpecifics(inputLine);
            String eventDate = inputLine.substring(inputLine.indexOf("/at") + 3);
            addTask(new Event(eventSpecifics, false, eventDate));
            ui.printDefaultTaskResponseMessage(tasks.size(), tasks);
        } catch (IllegalTaskException exception) {
            ui.printEmptyTaskDescriptionMessage();
        }
    }

    private void markTask(String inputLine, ArrayList<Task> tasks) {
        Storage storage = new Storage();
        Ui ui = new Ui();
        try {
            String[] taskDescriptions = inputLine.split(" ");
            int taskToMark = Integer.parseInt(taskDescriptions[1]) - 1;
            ui.printTaskMarkedMessage();
            tasks.get(taskToMark).markTaskAsDone();
            storage.insertIntoFile(tasks);
            System.out.println(tasks.get(taskToMark).toString());
        } catch (IOException ioException) {
            ui.printMarkingTaskInputErrorMessage();
        } catch (NumberFormatException exception) {
            ui.printMarkingTaskNumberErrorMessage();
        } catch (ArrayIndexOutOfBoundsException indexOutOfBoundsException) {
            ui.printInvalidTaskMessage();
        }
    }

    private void unmarkTask(String inputLine, ArrayList<Task> tasks) {
        Storage storage = new Storage();
        Ui ui = new Ui();
        try {
            String[] taskDescriptions = inputLine.split(" ");
            int taskToUnmark = Integer.parseInt(taskDescriptions[1]) - 1;
            ui.printUnmarkTaskMessage();
            tasks.get(taskToUnmark).unmarkTask();
            storage.insertIntoFile(tasks);
            System.out.println(tasks.get(taskToUnmark).toString());
        } catch (IOException ioException) {
            ui.printUnmarkTaskInputErrorMessage();
        } catch (NumberFormatException exception) {
            ui.printUnmarkTaskNumberErrorMessage();
        } catch (ArrayIndexOutOfBoundsException indexOutOfBoundsException) {
            ui.printInvalidTaskMessage();
        }
    }

    private void addTask(Task task) {
        tasks.add(task);
        Storage storage = new Storage();
        Ui ui = new Ui();
        try {
            storage.insertIntoFile(tasks);
        } catch (IOException ioexception) {
            ui.printFileUpdatingErrorMessage();
        }
    }

    private void deleteTask(String inputLine, ArrayList<Task> tasks) {
        Storage storage = new Storage();
        Ui ui = new Ui();
        try {
            String[] taskDescriptions = inputLine.split(" ");
            int taskToDelete = Integer.parseInt(taskDescriptions[1]);
            ui.printDeleteTaskMessage(tasks, taskToDelete);
            tasks.remove(taskToDelete - 1);
            storage.insertIntoFile(tasks);
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            ui.printInvalidTaskMessage();
        } catch (IOException ioException) {
            ui.printDeletingTaskErrorMessage();
        } catch (NumberFormatException numberFormatException) {
            ui.printDeletingTaskNumberErrorMessage();
        }
    }

    private String findTaskSpecifics(String details) throws IllegalTaskException {
        String[] taskDetails = details.split(" ");
        if (taskDetails.length < 2) {
            throw new IllegalTaskException();
        }
        String typeOfTask = details.substring(details.indexOf(" "), details.indexOf("/"));
        return typeOfTask;
    }
}
