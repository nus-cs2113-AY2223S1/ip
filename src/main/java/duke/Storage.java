package duke;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

import duke.task.Task;
import duke.task.TaskFactory;

/**
 * The Storage class is responsible for saving and loading data from the data
 * file. The default path for the data file is ./data/duke.txt
 */
public class Storage {

    public static final String FILE_PATH = "data/duke.txt";
    private static final String FILE_WRITING_ERROR_MESSAGE = "Data file cannot be written to.";

    /**
     * Reads a list of tasks from the default data file.
     * 
     * @return A list of tasks
     */
    public static List<Task> readDataFile() {
        return readDataFile(FILE_PATH);
    }

    /**
     * Reads a list of tasks from the specified data file.
     * 
     * @param path Path to the data file
     * @return A list of tasks
     */
    public static List<Task> readDataFile(String path) {
        List<Task> tasks = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new FileInputStream(path));
            while (scanner.hasNextLine()) {
                try {
                    tasks.add(TaskFactory.createTask(scanner.nextLine()));
                } catch (DukeException e) {
                    // skip corrupted entries
                }
            }
        } catch (FileNotFoundException e) {
            // if data file does not exist, then task list should be empty
        }
        return tasks;
    }

    /**
     * Writes a list of tasks to the default data file.
     * 
     * @param tasks The list of tasks to be written
     * @throws DukeException Throws an exception if the file cannot be written to
     */
    public static void writeDataFile(List<Task> tasks) throws DukeException {
        writeDataFile(tasks, FILE_PATH);
    }

    /**
     * Writes a list of tasks to the specified data file.
     * 
     * @param tasks The list of tasks to be written
     * @param path  The path of the file to write to
     * @throws DukeException Throws an exception if the file cannot be written to
     */
    public static void writeDataFile(List<Task> tasks, String path) throws DukeException {
        try {
            // create ./data folder if it does not exist
            File dataFile = new File(path);
            if (!dataFile.getParentFile().exists()) {
                dataFile.getParentFile().mkdirs();
            }
            FileWriter writer = new FileWriter(dataFile);
            for (Task task : tasks) {
                writer.append(task.serialize());
                writer.append("\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeException(FILE_WRITING_ERROR_MESSAGE);
        }
    }
}
