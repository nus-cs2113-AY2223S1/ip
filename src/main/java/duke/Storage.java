package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * This class contains the main mechanism to read and write into datafiles, and hence acts as the program's way of saving user data.
 *
 * @author Dhanish
 */
public class Storage {

    private static final String TEXT_FILE_NAME = "/duke.txt";
    private static final int TASK_TYPE_INDEX = 0;
    private static final int STATUS_INDEX = 1;
    private static final int DESCRIPTION_INDEX = 2;
    private static final int TIME_INDEX = 3;

    private File dataFile;

    /**
     * Constructor used to initialize file path of data file
     */
    public Storage() {
        String filePath = retrieveFilePath();
        dataFile = new File(filePath);
    }

    private static String retrieveFilePath() {
        Path path = Paths.get("src", "main", "java", "duke", "data");
        boolean directoryExists = Files.exists(path);
        if (!directoryExists) {
            new File(path.toString()).mkdirs();
        }
        return path + TEXT_FILE_NAME;
    }

    /**
     * This method takes in an empty {@code TaskList}, reads the data from the data file and loads the data into the {@code TaskList}.
     *
     * @param taskList - empty {@code TaskList} in which the data in the data file is to be loaded
     */
    public void loadData(TaskList taskList) {
        if (!dataFile.exists()) {
            createDataFile();
            return;
        }

        Ui.printLine();
        System.out.println("\tLoading existing data...");
        Ui.printLine();
        System.out.println();

        Scanner fileScanner = initializeScanner();
        if (fileScanner == null) {
            return;
        }

        while (fileScanner.hasNext()) {
            loadNextTask(taskList, fileScanner);
        }
    }

    private static void loadNextTask(TaskList taskList, Scanner fileScanner) {
        String[] inputs;
        String taskType, description, status, time = "";
        inputs = acceptAndProcessInput(fileScanner);
        taskType = inputs[TASK_TYPE_INDEX];
        status = inputs[STATUS_INDEX];
        description = inputs[DESCRIPTION_INDEX];
        if (inputs.length > 3) {
            time = inputs[TIME_INDEX];
        }
        Task task = TaskList.initialiseTaskFromParameters(taskType, description, status, time);
        taskList.loadTask(task);
    }

    private Scanner initializeScanner() {
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(dataFile);
        } catch (FileNotFoundException e) {
            Ui.showErrorMessage("Error! Data file cannot be found!");
        }
        return fileScanner;
    }

    private void createDataFile() {
        try {
            dataFile.createNewFile();
        } catch (IOException e) {
            Ui.showErrorMessage("Error! Data file to store tasks cannot be created!");
        }
    }

    private static String[] acceptAndProcessInput(Scanner fileScanner) {
        String input = fileScanner.nextLine().trim();
        return input.split(" \\| ", 4);
    }

    /**
     * This method processes the {@code Task}s in the {@code TaskList} one by one, and logs them into a data file.
     * It is usually called after the {@code TaskList} is changed one way or another.
     *
     * @param taskList - {@code TaskList} that contains {@code Task}s to be saved and logged into a data file.
     */
    public void saveData(TaskList taskList) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(dataFile);
        } catch (IOException e) {
            Ui.showErrorMessage("Error! File writer could not be created!");
            Duke.exit(-1);
        }

        try {
            for (Task task : taskList.getTasks()) {
                logTaskIntoDataFile(fileWriter, task);
            }
        } catch (IOException e) {
            Ui.showErrorMessage("Error! Data could not be written into data file!");
            Duke.exit(-1);
        }

        try {
            fileWriter.close();
        } catch (IOException e) {
            Ui.showErrorMessage("Error! File writer could not be closed!");
            Duke.exit(-1);
        }
    }

    private static void logTaskIntoDataFile(FileWriter fileWriter, Task task) throws IOException {
        fileWriter.write(task.getTaskType());
        fileWriter.write(" | ");
        fileWriter.write(task.getStatusIcon());
        fileWriter.write(" | ");
        fileWriter.write(task.getDescription());
        if (task instanceof Event) {
            fileWriter.write(" | ");
            fileWriter.write(((Event) task).getTime());
        } else if (task instanceof Deadline) {
            fileWriter.write(" | ");
            fileWriter.write(((Deadline) task).getDeadline());
        }
        fileWriter.write(System.lineSeparator());
    }

}
