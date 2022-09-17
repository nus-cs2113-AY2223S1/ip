package duke.data;

import duke.task.Task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class LocalStorage {
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

    public static ArrayList<Task> loadTasks(String path, String filename) throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();

        Path tasksFilePath = Paths.get(path + filename);

        String tasksStr = Files.readString(tasksFilePath);

        String[] tasksStrArr = tasksStr.split("\n");
        for (String taskStr : tasksStrArr) {
            Task task = Task.convertFromString(taskStr);
            tasks.add(task);
        }

        return tasks;
    }
}
