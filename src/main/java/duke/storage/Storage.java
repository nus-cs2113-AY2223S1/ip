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

public class Storage {

    private static final Path dataDirectory = Paths.get("src/main/java/duke/data/");
    private static final Path dataFile = Paths.get("src/main/java/duke/data/data.txt");

    public Storage() {
    }

    public static void createDataFile() throws IOException {
        if (!Files.exists(dataDirectory)) {
            Files.createDirectory(dataDirectory);
        }
        Files.createFile(dataFile);
    }

    public static void loadTasks() throws FileNotFoundException, DukeException {
       LoadTasks.loadTasks(dataFile);
    }

    public static void saveTasks() throws IOException, DukeException {
        SaveTasks.saveTasks(dataFile);
    }

}
