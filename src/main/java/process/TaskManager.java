package process;

import command.Command;
import exception.DukeException;
import storage.Storage;
import storage.Restore;
import task.Task;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Handles all types of tasks and maintain a task list to store them
 */
public class TaskManager {
    private static ArrayList<Task> taskList = new ArrayList<Task>();

    /**
     * Handles the user input and executes the corresponding action
     * 
     * @param inputText the user input line
     * @return the flag whether the program should end
     * @throws DukeException if something wrong happens in the input line
     */
    public boolean handleTask(String inputText) throws DukeException {
        Command command = Parser.parse(inputText);
        boolean isProgramEnd = command.execute(taskList);
        updateRestorationFile();
        return isProgramEnd;
    }

    /**
     * Handles the input line from the input file and add the task to the task list
     * 
     * @param restoreLine the user input line
     * @throws DukeException if restoration fails
     */
    public void handleRestoration(String restoreLine) throws DukeException {
        Restore restore = new Restore();
        restore.restoreFromLine(taskList, restoreLine);
    }

    /**
     * Updates the restoration file
     * 
     * @throws DukeException if update fails
     */
    private void updateRestorationFile() throws DukeException {
        String[] writeLines = this.getAllTasks(taskList);
        try {
            Storage.writeToFile(writeLines);
        } catch (IOException e) {
            throw new DukeException(DukeException.UPDATE_FILE_ERROR);
        }
    }

    private String[] getAllTasks(ArrayList<Task> taskList) {
        String[] tasks = new String[taskList.size()];
        for (int i = 0; i < taskList.size(); i++) {
            tasks[i] = taskList.get(i).getStorageFormat();
        }
        return tasks;
    }
}
