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

public class Storage {

    public static final String path = "data/duke.txt";

    public static List<Task> readDataFile() {
        return readDataFile(path);
    }

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
            // empty list
        }
        return tasks;
    }

    public static void writeDataFile(List<Task> tasks) throws DukeException {
        writeDataFile(tasks, Storage.path);
    }

    public static void writeDataFile(List<Task> tasks, String path) throws DukeException {
        try {
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
            throw new DukeException("Data file cannot be written to.");
        }
    }
}
