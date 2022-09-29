package duke.storage;

import duke.exceptions.FileFormatException;
import duke.tasks.*;

import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Reads and writes list of task into storage file
 */
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

    /** Constructor */
    public Storage(String filePath) {
        this.filePath = filePath;
        file = new File(filePath);
    }
    /**
     * Loads data from file into <code>taskList</code>
     *
     * @param taskList taskList to be loaded with data from storage file
     * @throws FileFormatException When the entry format is wrong
     * @throws FileNotFoundException When file does not exist
     */
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
                    TaskList.addTodo(taskDescription, isDone);
                } else if (taskType.equals(EVENT_TASK_TYPE)){
                    String at = dataLineSplit[3];
                    TaskList.addEvent(taskDescription, at, isDone);
                } else if (taskType.equals(DEADLINE_TASK_TYPE)){
                    String by = dataLineSplit[3];
                    TaskList.addDeadline(taskDescription, by, isDone);
                }
            }
            myReader.close();
    }

    /**
     * Creates a data file and parent directory if it does not already exists
     *
     * @throws IOException When the file path is not available
     */
    public void createFile() throws IOException {
        Path pathToFile = Paths.get(filePath);
        Files.createDirectories(pathToFile.getParent());
        file.createNewFile();
    }
    /**
     * Initialise a new taskList and load its data into file if file exists otherwise create new file to store data
     *
     * @return InitializedTaskList containing <code>taskList</code> and <code>initialisationMessage</code>
     * containing String of initialisation message of details of successful initialisation of taskList
     * @throws IOException if unable to write to file
     */
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
    /**
     * Writes taskList to file in a formatted manner
     *
     * @param taskList list of task to write to data
     * @throws IOException if there is error while trying to write into file
     */
    public static void writeToFile(TaskList taskList) throws IOException {
        FileWriter outputFile = new FileWriter(file);
        for (int i = 0; i < TaskList.taskList.size(); i++) {
            Task task = TaskList.taskList.get(i);
            outputFile.write(task.writeTaskToFile() + "\n");
        }
        outputFile.close();
    }
}
