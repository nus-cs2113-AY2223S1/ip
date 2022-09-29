package storage;

import dukeTasksPackage.TaskList;

import java.util.ArrayList;
import java.util.List;

public class encodeTaskList {
    public static List<String> encode(TaskList taskList) {
        final List<String> encodedText = new ArrayList<>();
        for (int i = 0; i < taskList.getTaskListSize(); i++) {
            String encodedTask = taskList.getTask(i).toFileString();
            encodedText.add(encodedTask);
        }
        return encodedText;
    }
}
