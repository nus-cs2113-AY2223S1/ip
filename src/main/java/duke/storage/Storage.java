package duke.storage;

import duke.exception.DukeException;
import duke.task.TaskList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Represents operations with save file
 */
public class Storage {

    private final Path dataDirectory = Paths.get("src/main/java/duke/data/");
    private final Path dataFile = Paths.get("src/main/java/duke/data/data.txt");

    public Storage() {
    }

    /**
     * Creates new save file, and save file directory if necessary
     *
     * @throws IOException if save file or directory creation fails
     */
    public void createDataFile() throws IOException {
        if (!Files.exists(dataDirectory)) {
            Files.createDirectory(dataDirectory);
        }
        Files.createFile(dataFile);
    }

    /**
     * Loads tasks from save file into taskList
     *
     * @throws FileNotFoundException if save file does not exist
     * @throws DukeException         if any task fails to load
     */
    public void loadTasks(TaskList taskList) throws FileNotFoundException, DukeException {
        LoadTasks.loadTasks(taskList, dataFile);
    }

    /**
     * Saves tasks from taskList to save file
     *
     * @throws IOException   if save file does not exist
     * @throws DukeException if any task fails to save
     */
    public void saveTasks(TaskList taskList) throws IOException, DukeException {
        SaveTasks.saveTasks(taskList, dataFile);
    }

}
