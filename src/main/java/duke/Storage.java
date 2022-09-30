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

/**
 * Class to handle storage of task lists locally.
 */
public class Storage {
    private final String DATA_DIRECTORY = "data";
    private final String SAVE_FILE_NAME = "duke.txt";
    private final String CREATE_DIRECTORY_FAILED = "Please create a directory named data before using duke!";
    private final int TASK_TYPE = 0;
    private final int TASK_STATUS = 1;
    private final int TASK_DESCRIPTION = 2;
    private final int TASK_TIMING = 3;

    /** Path to save and read files from */
    private Path savedFilePath;
    private Path dataDirectoryPath;

    /**
     * Initialise savedFilePath to desired file path and dataDirectoryPath to desired directory.
     */
    public void setHomePath(){
        String home = System.getProperty("user.home");
        savedFilePath = Paths.get(home,DATA_DIRECTORY,SAVE_FILE_NAME);
        dataDirectoryPath = Paths.get(home,DATA_DIRECTORY);
    }

    public String getHomePath(){
        return this.savedFilePath.toString();
    }

    /**
     * Returns TaskList read from saved file.
     *
     * @return TaskList
     * @throws FileNotFoundException if saved file is not found
     */
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

    /**
     * Saves task list information into a file.
     *
     * @param taskList List of tasks to save.
     * @throws IOException If unable to create or modify a save file indicated at savedFilePath
     */
    public void saveData(TaskList taskList) throws IOException {
        File saveFile = new File(savedFilePath.toString());
        FileWriter fileWriter = new FileWriter(saveFile);
        for (Task task:taskList.getTaskList()) {
            String taskDetails = task.getSaveString();
            fileWriter.write(taskDetails);
            fileWriter.write('\n');
        }
        fileWriter.close();
    }

    /**
     * Makes data directory and duke.txt file if it does not already exist.
     *
     * @throws IOException If failed to create directory or text file
     */
    public void makeDirectory() throws IOException{
        File dataDirectory = new File(dataDirectoryPath.toString());
        if (!dataDirectory.exists()) {
            boolean isDirectoryCreated = dataDirectory.mkdir();
            if (isDirectoryCreated){
                File saveFile = new File(savedFilePath.toString());
                FileWriter fileWriter = new FileWriter(saveFile);
                fileWriter.close();
            } else {
                throw new NotDirectoryException(CREATE_DIRECTORY_FAILED);
            }
        }
    }
}
