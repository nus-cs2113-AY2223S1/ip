package duke.Storage;

import duke.exception.NoTasksException;
import duke.exception.StorageReadException;
import duke.manager.CommandParser;
import duke.manager.TaskExecutor;
import duke.manager.UserInterface;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Class that deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {

    // paths are not based on root location
    private static final String DEFAULT_FOLDER = "./data";
    private static final String DEFAULT_FILE_PATH = "./data/duke.txt";
    private static final String SEPARATOR_LINE = "|";

    /**
     * Creates the directory/folder and the file to load from at the start of execution if
     * the directory/folder and/or file is missing.
     *
     * @return a list that stores tasks
     * @throws IOException If an input or output exception occurred
     */
    private static TaskList createMissingFile() throws IOException {
        // first handle the folder-does-not-exist-case
        File folder = new File(DEFAULT_FOLDER);
        if (!folder.exists()) {
            folder.mkdir();
        }
        // then handle FileNotFoundException
        File file = new File(DEFAULT_FILE_PATH);
        file.createNewFile(); // this part throws IOException
        System.out.println("Since the storage file is missing, I have created it for you.");
        return new TaskList();
    }

    /**
     * Calls the <code>createMissingFile</code> and handles any IOExceptions that occurs.
     *
     * @return an empty list to store tasks in
     */
    private static TaskList createManager() {
        try {
            return createMissingFile();
        } catch (IOException e) {
            System.out.println("The storage file is missing and I have failed in creating it for you.");
            return new TaskList();
        }
    }

    /**
     * Creates todo, deadline and event tasks locally based on the data stored in the file.
     *
     * @param input the truncated stored data with only description and time fields left
     * @param keyword the type of task to create
     * @return the created <code>Task</code> object
     * @throws StorageReadException If unexpected characters or values are read during loading of data
     */
    private static Task loadTask(String input, String keyword) throws StorageReadException {
        Task currentTask;
        String time;
        String description;
        // separator refers to |
        int lastSeparator;
        switch (keyword) {
        case "t":
            description = input;
            currentTask = new Todo(description);
            break;
        case "d":
            lastSeparator = input.lastIndexOf(SEPARATOR_LINE); // to account for existence of | in description
            description = FileParser.getStoredDescription(input, lastSeparator);
            time = FileParser.getStoredTime(input, lastSeparator);
            currentTask = new Deadline(description, time);
            break;
        case "e":
            lastSeparator = input.lastIndexOf(SEPARATOR_LINE); // to account for existence of | in description
            description = FileParser.getStoredDescription(input, lastSeparator);
            time = FileParser.getStoredTime(input, lastSeparator);
            currentTask = new Event(description, time);
            break;
        default:
            throw new StorageReadException("keyword");
        }
        return currentTask;
    }

    /**
     * Reads the data in the indicated storage file line by line and processes it for task creation.
     *
     * @return a list with the stored tasks
     * @throws FileNotFoundException If the file to read data from does not exist
     * @throws StorageReadException If unexpected characters or values are read during loading of data
     */
    private static TaskList loadStorage() throws FileNotFoundException, StorageReadException {
        Task currentTask;
        String newLine;
        String keyword;
        String truncatedInput;
        String unprocessedInput;
        boolean isDone;
        // separator refers to |
        int firstSeparatorPosition;
        int afterBooleanSeparator;
        TaskList storedTasks = new TaskList();
        // create File for the given file path
        File file = new File(DEFAULT_FILE_PATH);
        Scanner storedData = new Scanner(file);
        while (storedData.hasNext()) {
            newLine = storedData.nextLine();
            keyword = CommandParser.getType(newLine);
            isDone = FileParser.getStoredBoolean(newLine);
            // get the parts of newLine that is not retrieved yet
            // firstly cut the part with the keyword: "T,D,E"
            firstSeparatorPosition = newLine.indexOf(SEPARATOR_LINE);
            truncatedInput = newLine.substring(firstSeparatorPosition + 2);
            // then cut the part with the boolean
            afterBooleanSeparator = truncatedInput.indexOf(SEPARATOR_LINE);
            unprocessedInput = truncatedInput.substring(afterBooleanSeparator + 2);
            // create tasks based on storage input
            currentTask = loadTask(unprocessedInput, keyword);
            currentTask.setDone(isDone);
            storedTasks.addTask(currentTask, false);
        }
        TaskExecutor.runList(storedTasks.getSize());
        UserInterface.printBorderLines();
        return storedTasks;
    }

    /**
     * Creates a string with the task details for saving into the hard disk.
     *
     * @param task task to save in the hard disk
     * @return a string message containing task data to write into the file
     */
    private static String saveTask(Task task) {
        String saveMessage;
        saveMessage = task.saveFormat();
        return saveMessage;
    }

    /**
     * Loops for every task in the list and crafts a string of task details and writes it
     * into the file at the default file path.
     *
     * @throws IOException If an input or output exception occurred.
     */
    private static void saveStorage() throws IOException, NoTasksException {
        String saveMessage;
        FileWriter storingData = new FileWriter(DEFAULT_FILE_PATH);
        int taskNumber = TaskList.getSize();

        for (int i = 0; i < taskNumber; i++) {
            Task task = TaskList.get(i);
            saveMessage = saveTask(task);
            storingData.write(saveMessage + System.lineSeparator());
        }
        storingData.close();
    }

    /**
     * Calls the <code>loadStorage</code> method and handles the FileNotFoundException if file
     * is not present in the file path and the StorageReadException if there was an error in
     * reading the stored data.
     *
     * @return either a list with stored tasks or an empty list
     */
    public static TaskList loadManager() {
        try {
            return loadStorage();
        } catch (FileNotFoundException e) {
            return createManager();
        } catch (StorageReadException e) {
            System.out.println(e.getMessage());
            return new TaskList();
        }
    }

    /**
     * Calls the method to save any changes and handles any IOExceptions.
     */
    public static void saveManager() {
        try {
            saveStorage();
        } catch (IOException e) {
            System.out.println("Sorry, but I have failed to save your list into the hard disk.");
        } catch (NoTasksException e) {
            System.out.println(e.getMessage());
        }
    }
}
