package duke.storage;

import duke.exception.DukeException;
import duke.exception.LoadErrorDukeException;
import duke.task.*;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.Scanner;

/**
 * Represents operations with save file
 */
public class Storage {

    private static final Path dataDirectory = Paths.get("src/main/java/duke/data/");
    private static final Path dataFile = Paths.get("src/main/java/duke/data/data.txt");

    public Storage() {
    }

    /**
     * Creates save file, and save file directory if necessary
     * @throws IOException if error encountered
     */
    public static void createDataFile() throws IOException {
        if (!Files.exists(dataDirectory)) {
            Files.createDirectory(dataDirectory);
        }
        Files.createFile(dataFile);
    }

    /**
     * Loads tasks from save file into taskList
     * @throws FileNotFoundException if save file does not exist
     * @throws DukeException if error encountered when loading task
     */
    public static void loadTasks() throws FileNotFoundException, DukeException {
       LoadTasks.loadTasks(dataFile);
    }

    /**
     * Saves tasks from taskList to save file
     * @throws IOException if save file does not exist
     * @throws DukeException if error encountered when saving task
     */
    public static void saveTasks() throws IOException, DukeException {
        SaveTasks.saveTasks(dataFile);
    }

}
