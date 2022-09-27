package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.NotDirectoryException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Storage {
    private final String DATA_DIRECTORY = "data";
    private final String SAVE_FILE_NAME = "duke.txt";
    private final int TASK_TYPE = 0;
    private final int TASK_STATUS = 1;
    private final int TASK_DESCRIPTION = 2;
    private final int TASK_TIMING = 3;
    private Path savedFilePath;
    public void setHomePath(){
        savedFilePath = Paths.get(DATA_DIRECTORY,SAVE_FILE_NAME);
    }
    public TaskList readData() throws FileNotFoundException {
        File savedFile = savedFilePath.toFile();
        Scanner fileScanner = new Scanner(savedFile);
        TaskList taskList = new TaskList();
        while (fileScanner.hasNextLine()){
            String[] data = fileScanner.nextLine().split(Pattern.quote("|"));
            switch (data[TASK_TYPE]){
            case "T":
                ToDo oldToDo = new ToDo(data[TASK_DESCRIPTION]);
                if (data[TASK_STATUS].equals("true")){
                    oldToDo.setDone(true);
                }
                taskList.addTask(oldToDo);
                break;
            case "E":
                Event oldEvent = new Event(data[TASK_DESCRIPTION], data[TASK_TIMING]);
                if (data[TASK_STATUS].equals("true")){
                    oldEvent.setDone(true);
                }
                taskList.addTask(oldEvent);
                break;
            case "D":
                Deadline oldDeadline = new Deadline(data[TASK_DESCRIPTION], data[TASK_TIMING]);
                if (data[TASK_STATUS].equals("true")){
                    oldDeadline.setDone(true);
                }
                taskList.addTask(oldDeadline);
                break;
            default:
            }
        }
        return taskList;
    }

    public void saveData(TaskList taskList) throws IOException {
        File dataDirectory = new File(DATA_DIRECTORY);
        if (!dataDirectory.exists()) {
            boolean isDirectoryCreated = dataDirectory.mkdir();
            if (!isDirectoryCreated) {
                throw new NotDirectoryException(dataDirectory.toString());
            }
        }
        File saveFile = new File(savedFilePath.toString());
        FileWriter fileWriter = new FileWriter(saveFile);
        for (Task task:taskList.getTaskList()) {
            String taskDetails = task.getSaveString();
            fileWriter.write(taskDetails);
            fileWriter.write('\n');
        }
        fileWriter.close();
    }
}
