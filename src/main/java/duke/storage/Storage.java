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

    private final Path dataDirectory = Paths.get("src/main/java/duke/data/");
    private final Path dataFile = Paths.get("src/main/java/duke/data/data.txt");

    public Storage() {
    }

    public void createDataFile() throws IOException {
        if (!Files.exists(dataDirectory)) {
            Files.createDirectory(dataDirectory);
        }
        Files.createFile(dataFile);
    }

    public void loadTasks(TaskList taskList) throws FileNotFoundException, DukeException {
       LoadTasks.loadTasks(taskList, dataFile);
    }

    public void saveTasks(TaskList taskList) throws IOException, DukeException {
        SaveTasks.saveTasks(taskList, dataFile);
    }

}
