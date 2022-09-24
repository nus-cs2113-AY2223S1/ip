package duke;

import duke.task.Task;

import java.io.IOException;
import java.util.ArrayList;

public class Parser {
    public String command;
    public String filePath;
    public ArrayList<Task> tasks;
    public int numberOfTasks;

    public String[] words;
    public String firstWord;

    public Parser(String command, String filePath, ArrayList<Task> tasks, int numberOfTasks) {
        this.command = command;
        this.filePath = filePath;
        this.tasks = tasks;
        this.numberOfTasks = numberOfTasks;

        words = command.split(" ");
        firstWord = words[0];
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getNumberOfTasks() {
        return numberOfTasks;
    }

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
        } else {
            throw new DukeException();
        }
    }
}
