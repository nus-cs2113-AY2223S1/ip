package duke.data;

import duke.task.Task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class LocalStorage {
    /**
     * Save tasks in memory to local storage.
     *
     * @param tasks List of tasks to save.
     * @param path Path of the directory to save.
     * @param filename Filename of the tasks file.
     * @throws IOException If there was an error writing to local storage.
     */
    public static void saveTasks(ArrayList<Task> tasks, String path, String filename) throws IOException {
        Path tasksDirectoryPath = Paths.get(path);
        Path tasksFilePath = Paths.get(path + filename);

        if (Files.notExists(tasksDirectoryPath)) {
            Files.createDirectories(tasksDirectoryPath);
        }

        String tasksStr = "";
        for (Task task : tasks) {
            tasksStr += Task.convertToString(task);
            tasksStr += "\n";
        }

        Files.writeString(tasksFilePath, tasksStr, StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

    }

    /**
     * Read tasks from local storage to memory.
     *
     * @param path Path of the directory to load.
     * @param filename Filename of the tasks file.
     * @return List of tasks that was loaded.
     * @throws IOException If there was an error reading from local storage.
     */
    public static ArrayList<Task> loadTasks(String path, String filename) throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();

        Path tasksFilePath = Paths.get(path + filename);

        String tasksStr = Files.readString(tasksFilePath);

        String[] tasksStrArr = tasksStr.split("\n");
        for (String taskStr : tasksStrArr) {
            Task task = Task.convertFromString(taskStr);
            if (task != null) {
                tasks.add(task);
            }
        }

        return tasks;
    }
}
