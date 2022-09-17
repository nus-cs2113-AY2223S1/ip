package duke.command;

import duke.exception.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.ui.Ui;

import java.util.ArrayList;

public class Menu {
    private ArrayList<Task> tasks;
    private static final int MAX_TASK_STRING_LENGTH = 9;
    private static final boolean IS_MARK_METHOD = true;
    private static final boolean IS_UNMARK_METHOD = false;
    private Ui ui = new Ui();

    public Menu() {
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void greet() {
        ui.displayGreetingMessage();
    }

    public void list() {
        String listContent = "";
        for (int i = 0; i < tasks.size(); i++) {
            listContent += String.format("%d.%s", i + 1, tasks.get(i).getTaskFullDetails());
            listContent += System.lineSeparator();
        }
        listContent += "There are a total of " + tasks.size() + " tasks.";
        ui.displayListingMessage(listContent);
    }

    public void addTask(String taskType, String userInput, boolean isSilent) throws DukeException {
        String taskName;
        String[] splits;
        switch (taskType) {
        case "todo":
            checkTodoFormat(userInput);
            taskName = userInput;
            tasks.add(new Todo(taskName));
            break;
        case "deadline":
            checkDeadlineFormat(userInput);
            splits = splitTaskName(" /by ", userInput);
            // Task name: splits[0], deadline: splits[1]
            tasks.add(new Deadline(splits[0], splits[1]));
            break;
        case "event":
            checkEventFormat(userInput);
            splits = splitTaskName(" /at ", userInput);
            // Task name: splits[0], deadline: splits[1]
            tasks.add(new Event(splits[0], splits[1]));
            break;
        default:
            break;
        }
        String taskDetail = tasks.get(tasks.size() - 1).getTaskFullDetails();
        if (!isSilent) {
            ui.displayTaskAdditionMessage(taskDetail, tasks.size());
        }
    }

    public void deleteTask(String inputValue) throws DukeException {
        checkTaskIndexInput(inputValue);
        int taskIndex = Integer.parseInt(inputValue);
        String taskDetail = tasks.get(taskIndex - 1).getTaskFullDetails();
        tasks.remove(taskIndex - 1);
        ui.displayTaskDeletionMessage(taskDetail, tasks.size());
    }

    public void mark(String inputValue, boolean isSilent) throws DukeException {
        checkTaskIndexInput(inputValue);
        int taskIndex = Integer.parseInt(inputValue);
        tasks.get(taskIndex - 1).setDone(true);
        String taskName = tasks.get(taskIndex - 1).getTaskName();
        if (!isSilent) {
            ui.displayMarkOrUnmarkMessage(taskName, IS_MARK_METHOD);
        }
    }

    public void unmark(String inputValue) throws DukeException {
        checkTaskIndexInput(inputValue);
        int taskIndex = Integer.parseInt(inputValue);
        tasks.get(taskIndex - 1).setDone(false);
        String taskName = tasks.get(taskIndex - 1).getTaskName();
        ui.displayMarkOrUnmarkMessage(taskName, IS_UNMARK_METHOD);
    }

    public void quit() {
        ui.displayExitMessage();
    }



    public boolean containsTaskDescription(String userInput, String separator) {
        // Trim description from userInput
        String[] splits = userInput.split(separator, 2);
        String description = splits[0];

        // Check if description is empty or only contain whitespaces
        description = description.replaceAll("\\s+", "");
        if (description.equals("")) {
            return false;
        } else {
            return true;
        }
    }

    public boolean containsTaskTime(String userInput, String separator) {
        // Trim description from userInput
        String[] splits = userInput.split(separator, 2);
        String time = splits[1];

        // Check if description is empty or only contain whitespaces
        time = time.replaceAll("\\s+", "");
        if (time.equals("")) {
            return false;
        } else {
            return true;
        }
    }

    public boolean containsTimeSeparator(String userInput, String separator) {
        if (!userInput.contains(separator)) {
            return false;
        } else {
            return true;
        }
    }

    public void checkTodoFormat(String userInput) throws DukeException {
        if (!containsTaskDescription(userInput, "")) {
            throw new TodoMissingDescriptionException();
        }
    }

    public void checkDeadlineFormat(String userInput) throws DukeException {
        if (!containsTimeSeparator(userInput, " /by ")) {
            throw new DeadlineMissingTagException();
        }
        if (!containsTaskDescription(userInput, " /by ")) {
            throw new DeadlineMissingDescriptionException();
        }
        if (!containsTaskTime(userInput, " /by ")) {
            throw new DeadlineMissingTimeException();
        }
    }

    public void checkEventFormat(String userInput) throws DukeException {
        if (!containsTimeSeparator(userInput, " /at ")) {
            throw new EventMissingTagException();
        }
        if (!containsTaskDescription(userInput, " /at ")) {
            throw new EventMissingDescriptionException();
        }
        if (!containsTaskTime(userInput, " /at ")) {
            throw new EventMissingTimeException();
        }
    }

    public void checkTaskIndexInput(String inputValue) throws DukeException {
        // Check if the string only contains digits
        if (!inputValue.matches("^\\d+$")) {
            throw new InvalidIndexException();
        }
        // Check that the index will not exceed 1000000000
        if (inputValue.length() > MAX_TASK_STRING_LENGTH) {
            throw new InvalidIndexException();
        }
        int taskIndex = Integer.parseInt(inputValue);
        if (taskIndex <= 0 || taskIndex > tasks.size()) {
            // Out-of-bound task index
            throw new InvalidIndexException();
        }
    }

    public static String[] splitTaskName(String regex, String userInput) {
        String[] splits = userInput.split(regex, 2);
        if (splits.length == 1) {
            return new String[]{splits[0], ""};
        }
        return splits;
    }
}
