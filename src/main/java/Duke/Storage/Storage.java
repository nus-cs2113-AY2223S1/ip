package Duke.Storage;

import Duke.Tasks.TaskManager;
import Duke.Tasks.Tasks;
import Duke.Tasks.ToDo;
import Duke.Tasks.Event;
import Duke.Tasks.Deadline;
import Duke.UI.UI;

import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Storage class transfers user list data into a text file and reads from the saved text file upon opening the program.
 */
public class Storage {

    private final static String FILEPATH = "./data/dukesaved.txt";
    private static File file = new File(FILEPATH);
    private static String directory;

    public Storage() {
        this.directory = FILEPATH;
    }

    /**
     * Load list data from existing text file.
     *
     * @param taskManager task list
     * @throws IOException IO error when creating or reading from file.
     */
    public void loadData(TaskManager taskManager) throws IOException {
        try {
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                readFromFile(s.nextLine());
            }
        } catch (FileNotFoundException e) {
            fileNotFoundMessage();
            createFile();
        }
    }

    /**
     * Creates a new file if there is no existing one.
     *
     * @throws IOException IO error when creating the file
     */
    public void createFile() throws IOException {
        Path pathToFile = Paths.get(FILEPATH);
        Files.createDirectories(pathToFile.getParent());
        file.createNewFile();
    }

    /**
     * Prints message that program is unable to find file and will proceed to create a new one.
     */
    public void fileNotFoundMessage() {
        System.out.println(
                UI.PRINT_LINE
                        + "File not Found.\n"
                        + "Creating " + FILEPATH + "\n"
                        + UI.PRINT_LINE
        );
    }

    /**
     * Read list data from existing text file.
     *
     * @param data task from list
     * @throws FileNotFoundException unable to find file
     */
    public static void readFromFile(String data) throws FileNotFoundException {
        String[] dataBreakdown = data.split("\\|");
        switch (dataBreakdown[0]) {
        case "T":
            addFileToDo(dataBreakdown[1], dataBreakdown[2]);
            break;
        case "E":
            addFileEvent(dataBreakdown[1], dataBreakdown[2], dataBreakdown[3]);
            break;
        case "D":
            addFileDeadline(dataBreakdown[1], dataBreakdown[2], dataBreakdown[3]);
            break;
        }
    }

    /**
     * Writes updated list data to text file.
     *
     * @throws IOException IO error when writing to file
     */
    public static void writeToFile() throws IOException {
        try {
            FileWriter fileWriter = new FileWriter(FILEPATH);
            for (int i = 0; i < TaskManager.taskList.size(); i++) {
                fileWriter.write(TaskManager.taskList.get(i).toFile());
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("IO Error writing to file");
        }
    }

    /**
     * Adds todo task from file to running program list.
     *
     * @param mark        indicates if task is done or not done
     * @param description task description
     */
    public static void addFileToDo(String mark, String description) {
        ToDo toDo = new ToDo(description);
        insertTaskFromFile(toDo);
        if (mark.equals("1")) {
            toDo.insertDone();
        } else if (mark.equals("0")) {
            toDo.insertNotDone();
        }
    }

    /**
     * Adds event task from file to running program list.
     *
     * @param mark        indicates if task is done or not done
     * @param description task description
     * @param at          event details
     */
    public static void addFileEvent(String mark, String description, String at) {
        Event event = new Event(description, at);
        insertTaskFromFile(event);
        if (mark.equals("1")) {
            event.insertDone();
        } else if (mark.equals("0")) {
            event.insertNotDone();
        }
    }

    /**
     * Adds deadline task from file to running program list.
     *
     * @param mark        indicates if task is done or not done
     * @param description task description
     * @param by          deadline due date
     */
    public static void addFileDeadline(String mark, String description, String by) {
        Deadline deadline = new Deadline(description, by);
        insertTaskFromFile(deadline);
        if (mark.equals("1")) {
            deadline.insertDone();
        } else if (mark.equals("0")) {
            deadline.insertNotDone();
        }
    }

    /**
     * Insert tasks into the next array element of list.
     *
     * @param task task in text file
     */
    public static void insertTaskFromFile(Tasks task) {
        TaskManager.taskList.add(TaskManager.numOfTasks, task);
        TaskManager.numOfTasks++;
    }


}

