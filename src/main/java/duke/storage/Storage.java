package duke.storage;

import duke.exceptions.FileFormatException;
import duke.tasks.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Storage {

    private final String TODO_STORAGE_DELIMITER = "T\\s\\|\\s[01]\\s\\|.+";
    private final String EVENT_STORAGE_DELIMITER = "E\\s\\|\\s[01]\\s\\|.+\\|.+";
    private final String DEADLINE_STORAGE_DELIMITER = "D\\s\\|\\s[01]\\s\\|.+\\|.+";
    private final String TODO_TASK_TYPE = "T";
    private final String EVENT_TASK_TYPE = "E";
    private final String DEADLINE_TASK_TYPE = "D";
    public static final String PARTITION_DELIMITER = "\\s\\|\\s";


    private final String filePath;
    private static File file = null;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.file = new File(filePath);
    }

    // check whether I need file not found exception here
    public void loadDataIntoTaskList(TaskList taskList) throws FileFormatException, FileNotFoundException{
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String dataLine = myReader.nextLine();
                if(!dataLine.matches(TODO_STORAGE_DELIMITER) &&
                        !dataLine.matches(EVENT_STORAGE_DELIMITER) &&
                        !dataLine.matches(DEADLINE_STORAGE_DELIMITER)){
                    throw new FileFormatException();
                }

                String[] dataLineSplit = dataLine.split(PARTITION_DELIMITER);
                String taskType = dataLineSplit[0];
                boolean isDone = dataLineSplit[1].equals("1");
                String taskDescription = dataLineSplit[2];

                if (taskType.equals(TODO_TASK_TYPE)){
                    taskList.addTodo(taskDescription, isDone);
                } else if (taskType.equals(EVENT_TASK_TYPE)){
                    String at = dataLineSplit[3];
                    taskList.addEvent(taskDescription, at, isDone);
                } else if (taskType.equals(DEADLINE_TASK_TYPE)){
                    String by = dataLineSplit[3];
                    taskList.addDeadline(taskDescription, by, isDone);
                }
            }
            myReader.close();
    }


    public void createFile() throws IOException {
        Path pathToFile = Paths.get(filePath);
        Files.createDirectories(pathToFile.getParent());
        file.createNewFile();
    }

    public InitializeTaskList initializeTaskList() throws IOException {

        TaskList taskList = new TaskList();
        String initialisationMessage = "";
        try {
            loadDataIntoTaskList(taskList);
        } catch (FileNotFoundException e){
            initialisationMessage += "File not found.\n";

            try {
                initialisationMessage += "Creating " + filePath + "\n";
                createFile();
            } catch (IOException e_){
                initialisationMessage += "Error creating new file";
            }

        } catch (FileFormatException e){
            initialisationMessage += "File data contains format errors.";
        }
        return new InitializeTaskList(taskList, initialisationMessage);
    }

    public static void writeToFile(TaskList taskList) throws IOException{
        FileWriter outputFile = new FileWriter(file);
        for (int i = 0; i < taskList.taskList.size(); i++) {
            Task task = taskList.taskList.get(i);
            outputFile.write(task.writeTaskToFile() + "\n");
        }
        outputFile.close();
    }
}
