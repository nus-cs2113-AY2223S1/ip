package data;

import tasks.Task;
import tasks.TaskList;

import java.io.*;
import java.nio.file.Paths;

import static ui.Messages.saveMessage;

/**
 * Handles writing of the user's task list to Duke.txt.
 */
public class Storage {
    private static final File dukeFile = Paths.get("src/main/java/data/Duke.txt").toFile();

    /**
     * Saves the task list to Duke.txt in the data folder.
     */
    public static void saveToFile(TaskList taskList){
        try(PrintWriter duke = new PrintWriter(dukeFile)) {
            for(int i = 0; i < taskList.getTaskList().size(); i++){
                Task task = taskList.getTaskList().get(i);
                duke.println(task.toString());
            }
            saveMessage();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
