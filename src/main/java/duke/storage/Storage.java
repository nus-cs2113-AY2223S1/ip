package duke.storage;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.task.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class that reads the given file to get the data and stores the data to the file when exiting Duke.
 */
public class Storage {
    private static final String TODO = "T";
    private static final String EVENT = "E";
    private static final String DEADLINE = "D";
    private static final String DONE = "1";
    private static final String UNDONE = "0";
    private static final String STORE_DIVIDER = " \\| ";
    private final String filePath;

    /**
     * Constructor for Storage.
     *
     * @param filePath The path to the file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Read the tasks from a given file.
     *
     * @return The list of tasks stored in the file.
     * @throws DukeException If the file cannot be found.
     */
    public ArrayList<Task> loadTasks() throws DukeException {
        try {
            File file = new File(filePath);
            ArrayList<Task> list = new ArrayList<>();
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String taskLine = scanner.nextLine();
                String[] splitTaskLine = taskLine.split(STORE_DIVIDER);
                Task task = handleTaskLine(splitTaskLine);
                list.add(task);
            }
            return list;
        } catch (FileNotFoundException e) {
            throw new DukeException("OOPS! I cannot find your file");
        }
    }

    /**
     * Writes the current tasks to a file when exiting Duke.
     *
     * @param taskList The list of tasks to be stored.
     * @throws DukeException If there is an exception occurs.
     */
    public void writeTasks(TaskList taskList) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            String formattedTaskList = taskList.formatTaskListToStringToStore();
            fileWriter.write(formattedTaskList);
            fileWriter.close();
        } catch (IOException e) {
            String fileDirectory = filePath.replace(filePath.substring(filePath.lastIndexOf("/")), "");
            File file = new File(fileDirectory);
            if (file.mkdir()) {
                writeTasks(taskList);
            } else {
                throw new DukeException("OOPS!!! Something went wrong when storing your tasks");
            }
        }
    }

    /**
     * Analyses the information the tasks stored in the file.
     *
     * @param splitTaskLine The raw task information.
     * @return A Task with full information.
     * @throws DukeException If there is an exception occurs.
     */
    public Task handleTaskLine(String[] splitTaskLine) throws DukeException {
        Task task;
        switch (splitTaskLine[0]) {
        case TODO:
            task = new Todo(splitTaskLine[2]);
            break;
        case EVENT:
            task = new Event(splitTaskLine[2], splitTaskLine[3]);
            break;
        case DEADLINE:
            task = new Deadline(splitTaskLine[2], splitTaskLine[3]);
            break;
        default:
            throw new DukeException("Something went wrong with the tasks in your saved files");
        }

        switch (splitTaskLine[1]) {
        case DONE:
            task.setAsDone();
            break;
        case UNDONE:
            task.setAsUndone();
            break;
        default:
            throw new DukeException("Cannot understand your task status");
        }

        return task;
    }
}
