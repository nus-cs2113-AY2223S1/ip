package duke.storage;

import duke.data.TaskList;
import duke.exception.DukeException;
import duke.data.task.Deadline;
import duke.data.task.Event;
import duke.data.task.Task;
import duke.data.task.Todo;
import duke.exception.StorageInitializationException;
import duke.exception.StorageOutputException;
import duke.parser.DukeDateTimeParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Storage is the class that represents the file storage of the application.
 * Operations in the storage include the file I/O operations to create, open,
 * read, append, write and delete the file.
 * <p>
 * The class is used to store the added list of tasks in the application into a file,
 * such that the list of tasks in previous executions can be read from the file.
 */
public class Storage {
    // A relative file path of the file used to store the task list
    private String filePath;
    // A TaskList object used to hold the tasks stored in the file during initialisation
    private TaskList storedTaskList;
    // A DukeDateTimeParser object to parse the file output in string into a LocalDateTime object
    private DukeDateTimeParser dukeDateTimeParser;

    /**
     * Constructor of <code>Storage</code>. Stores the given file path,
     * and creates a new TaskList and DukeDateTimeParser object.
     *
     * @param filePath A relative file path of the file used to store the task list
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.storedTaskList = new TaskList();
        this.dukeDateTimeParser = new DukeDateTimeParser();
    }

    /**
     * Initialise the application by opening and reading the file specified in filePath
     * and return the TaskList object containing all the tasks stored in the file.
     *
     * @return A TaskList object with all the tasks stored in file storage.
     * @throws DukeException Exception triggered on erroneous file output operations.
     */
    public TaskList initialize() throws DukeException {
        openDukeFile();
        return storedTaskList;
    }

    /**
     * Check if the file specified in filePath exists,
     * open the file and read it if an existing file is found.
     *
     * @throws DukeException Exception triggered on erroneous file output operations.
     */
    public void openDukeFile() throws DukeException {
        if (Files.exists(Paths.get(filePath))) {
            File dukeFile = new File(filePath);
            safeReadDukeFile(dukeFile);
        }
    }

    /**
     * Creates a Scanner object to read the file and call another method to read it.
     * Capture the exceptions returned and throws a DukeException if there's any.
     *
     * @param dukeFile File object containing the opened file
     * @throws DukeException Exception triggered on erroneous file output operations.
     */
    private void safeReadDukeFile(File dukeFile) throws DukeException {
        try {
            Scanner in = new Scanner(dukeFile);
            readDukeFileAndInitializeTask(in);
        } catch (FileNotFoundException exception) {
            throw new StorageInitializationException();
        } catch (DukeException exception) {
            throw new StorageInitializationException();
        }
    }

    /**
     * Read the file attached to the Scanner object line by line,
     * split each of the lines representing a task into a string array separated by " | ",
     * then passes the split line into another method to add the task into task list.
     *
     * @param in Scanner object attached to the file pointer.
     * @throws DukeException Exception triggered on erroneous file output operations.
     */
    private void readDukeFileAndInitializeTask(Scanner in) throws DukeException {
        while (in.hasNext()) {
            String line = in.nextLine();
            String[] splits = line.split(" \\| ");
            initializeTask(splits);
        }
    }

    /**
     * Determine the type of task represented in the line,
     * create a corresponding Task object for it and stores the necessary parameters.
     * Then, mark the task as completed if necessary.
     *
     * @param splits Line split into parts based on delimiter " | ".
     * @throws DukeException Exception triggered on erroneous file output operations.
     */
    private void initializeTask(String[] splits) throws DukeException {
        switch (splits[0]) {
        case "T":
            storedTaskList.addTodo(splits[2]);
            break;
        case "D":
            storedTaskList.addDeadline(splits[2], dukeDateTimeParser.parse(splits[3]));
            break;
        case "E":
            storedTaskList.addEvent(splits[2], dukeDateTimeParser.parse(splits[3]));
            break;
        default:
            break;
        }
        if (splits[1].equals("1")) {
            storedTaskList.markTask(storedTaskList.getSize());
        }
    }

    /**
     * Rewrite the file with the updated task list provided.
     * Used when the modification made to the task list results in changes
     * that is not in the end of the file, which is unsuitable to use append operation,
     * and thus requires a rewrite.
     *
     * @param taskList The most recent TaskList object containing the updated tasks' information
     * @throws DukeException Exception triggered on erroneous file input operations.
     */
    public void rewriteDukeFile(TaskList taskList) throws DukeException {
        try {
            // Creates the file if it does not exist
            if (!Files.exists(Paths.get(filePath))) {
                File dukeFile = new File(filePath);
                dukeFile.createNewFile();
            }

            // Write each task in the task list into the file
            FileWriter dukeFileWriter = new FileWriter(filePath, false);
            for (Task task : taskList.getTasks()) {
                String output = retrieveTaskInformationForFileStorage(task);
                dukeFileWriter.append(output);
            }

            dukeFileWriter.close();
        } catch (IOException exception) {
            throw new StorageOutputException();
        }
    }

    /**
     * Append the file with the updated task list provided.
     * Used when a new task is added to the task list,
     * such that an append operation is sufficient to add the new task into the file storage.
     *
     * @param taskList The most recent TaskList object containing the updated tasks' information
     * @throws DukeException Exception triggered on erroneous file input operations.
     */
    public void appendDukeFile(TaskList taskList) throws DukeException {
        try {
            FileWriter dukeFileWriter;
            if (Files.exists(Paths.get(filePath))) {
                dukeFileWriter = new FileWriter(filePath, true);
            } else {
                File dukeFile = new File(filePath);
                dukeFile.createNewFile();
                dukeFileWriter = new FileWriter(filePath, false);
            }

            Task newTask = taskList.getTasks().get(taskList.getTasks().size() - 1);
            String output = retrieveTaskInformationForFileStorage(newTask);
            dukeFileWriter.append(output);
            dukeFileWriter.close();
        } catch (IOException exception) {
            throw new StorageOutputException();
        }
    }

    /**
     * Take in a Task object and format the task information
     * into the format suitable to write into the file storage.
     *
     * @param task A task to be written into the file storage.
     * @return A formatted string containing the information of the task, ready to be stored in file storage.
     */
    private static String retrieveTaskInformationForFileStorage(Task task) {
        String output = task.getTaskInStorageFormat();
        output += System.lineSeparator();
        return output;
    }
}
