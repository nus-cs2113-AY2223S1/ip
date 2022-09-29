package duke;

import duke.task.Task;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a parser to parse the user's input and
 * calls the appropriate function depending on the command given (the first word).
 */
public class Parser {
    public String command;
    public String filePath;
    public ArrayList<Task> tasks;
    public int numberOfTasks;

    public String[] words;
    public String firstWord;

    /**
     * Initializes the Parser object.
     *
     * @param command User's input.
     * @param filePath Name of the file used to save tasks.
     * @param tasks Array used to store all the tasks.
     * @param numberOfTasks Number of tasks currently in the list.
     */
    public Parser(String command, String filePath, ArrayList<Task> tasks, int numberOfTasks) {
        this.command = command;
        this.filePath = filePath;
        this.tasks = tasks;
        this.numberOfTasks = numberOfTasks;

        words = command.split(" ");
        firstWord = words[0];
    }

    /**
     * Get the updated tasks array used to store all the current tasks.
     *
     * @return Array used to store all the tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Get the updated number of tasks.
     *
     * @return Number of tasks currently in the list.
     */
    public int getNumberOfTasks() {
        return numberOfTasks;
    }

    /**
     * Parses the user's input and perform the action needed corresponding
     * to the command given (the first word).
     *
     * @param command User's input.
     * @throws DukeException If the command (first word) is an unrecognizable command.
     */
    public void performAction(String command) throws DukeException {
        UI ui = new UI();
        Storage storage = new Storage(filePath, tasks, numberOfTasks);
        TaskList taskList = new TaskList(command, filePath, tasks, numberOfTasks);

        if (firstWord.equals("mark")) {
            //Mark task as done
            taskList.markAsDone(words[1], false);
            tasks = taskList.getTasks();
        } else if (firstWord.equals("unmark")) {
            //Mark task as undone
            taskList.markAsUndone(words[1], false);
            tasks = taskList.getTasks();
        } else if (firstWord.equals("list")) {
            //Print the list
            taskList.printList();
        } else if (firstWord.equals("todo") | firstWord.equals("deadline") | firstWord.equals("event")) {
            //Add a task to the list
            try {
                Task currentTask = taskList.createTask(command);
                taskList.addTask(currentTask, false);
                storage.appendToFile(command);
                tasks = taskList.getTasks();
                numberOfTasks = taskList.getNumberOfTasks();
            } catch (DukeException e) {
                ui.printEmptyDescriptionError();
            } catch (IOException e) {
                ui.printLoadingError();
            }
        } else if (firstWord.equals("delete")) {
            //Remove a task from the list
            taskList.removeTask(Integer.parseInt(words[1]) - 1);
            tasks = taskList.getTasks();
            numberOfTasks = taskList.getNumberOfTasks();
            try {
                storage.rewriteFile();
            } catch (IOException e) {
                ui.printLoadingError();
            }
        } else if (firstWord.equals("find")) {
            //Find a task in the list that contains the substring given
            taskList.findTask(command.substring(6));
        } else {
            throw new DukeException();
        }
    }
}
