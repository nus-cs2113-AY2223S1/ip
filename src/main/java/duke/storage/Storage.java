package duke.storage;

import duke.common.Message;
import duke.exception.FileCorruptedException;
import duke.parser.Parser;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.DukeUI;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    private static final String DIRECTORY_NAME_DATA = "data";
    private static final String FILE_NAME_DUKE = "duke.txt";
    private static final String PATH_DUKE = DIRECTORY_NAME_DATA + "/" + FILE_NAME_DUKE;

    private static final File dataDir = new File(DIRECTORY_NAME_DATA);
    private static final File dataFile = new File(PATH_DUKE);

    public Storage() {
    }

    /**
     * Creates the data folder and file if it doesn't exist
     * The file contents will be wiped out for every instance of the program
     *
     * @param ui DukeUI object to interact with UI
     */
    public void createDataFile(DukeUI ui) {
        try {
            dataDir.mkdirs();
            dataFile.createNewFile();
        } catch (IOException e) {
            ui.printMessage("Error - Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Loads the task contents from data/duke.txt
     *
     * @param ui    DukeUI object to interact with UI
     * @param tasks TaskList object to add tasks from file to program
     * @throws FileCorruptedException If file content has been tampered and cannot be parsed
     * @throws IOException            If any IO operation fails
     */
    public void loadDataFile(DukeUI ui, TaskList tasks) throws FileCorruptedException, IOException {
        try {
            createDataFile(ui);
            Scanner scanner = new Scanner(dataFile);
            while (scanner.hasNext()) {
                Parser.processData(tasks, scanner.nextLine());
            }
        } catch (IOException e) {
            ui.printMessage(Message.TEXT_INDENTATION + "Error - file cannot be created. Please try again \n");
            throw new IOException();
        } catch (FileCorruptedException e) {
            ui.printMessage(Message.TEXT_INDENTATION + "Error - file is corrupted!\n");
            throw new FileCorruptedException();
        }
    }

    /**
     * Appends a task to ./data/duke.txt
     *
     * @param ui   DukeUI object to interact with UI
     * @param task TaskList object to add tasks from program to file
     */
    public void appendTaskToFile(DukeUI ui, Task task) {
        try {
            FileWriter file = new FileWriter(PATH_DUKE, true);
            String taskInfo = task.getTaskInfoForFile() + "\n";
            file.write(taskInfo);
            file.close();
        } catch (IOException e) {
            ui.printMessage("Error - Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Update the contents of task information to ./data/duke.txt
     *
     * @param ui    DukeUI object to interact with UI
     * @param tasks TaskList object to update tasks from program to file
     */
    public void updateTasksToFile(DukeUI ui, TaskList tasks) {
        try {
            FileWriter file = new FileWriter(PATH_DUKE);
            StringBuilder taskInfo = new StringBuilder();
            for (int i = 0; i < tasks.getTaskSize(); ++i) {
                Task curTask = tasks.getCurrentTask(i);
                taskInfo.append(curTask.getTaskInfoForFile()).append("\n");
            }
            file.write(String.valueOf(taskInfo));
            file.close();

        } catch (IOException e) {
            ui.printMessage("Error - Something went wrong: " + e.getMessage());
        }
    }

}
