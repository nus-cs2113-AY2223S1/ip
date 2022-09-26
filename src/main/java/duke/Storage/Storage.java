package duke.Storage;

import duke.exception.StorageReadException;
import duke.exception.UnrecognisedCommandException;
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

public class Storage {

    private static String DEFAULT_FOLDER = "./data";
    private static String DEFAULT_FILE_PATH = "./data/duke.txt";
    private static String SEPARATOR_LINE = "|";

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
        TaskList taskList = new TaskList();
        return taskList;
    }

    private static TaskList createManager() {
        try {
            return createMissingFile();
        } catch (IOException e) {
            System.out.println("The storage file is missing and I have failed in creating it for you.");
            return new TaskList();
        }
    }

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
            keyword = CommandParser.getKeyword(newLine);
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
        TaskExecutor.listCommand(storedTasks.getSize());
        UserInterface.printBorderLines();
        return storedTasks;
    }

    private static String saveTask(Task task) throws UnrecognisedCommandException {
        String saveMessage;
        saveMessage = task.saveFormat();
        return saveMessage;
    }

    private static void saveStorage() throws IOException, UnrecognisedCommandException {
        String saveMessage;
        FileWriter storingData = new FileWriter(DEFAULT_FILE_PATH);
        int taskNumber = TaskList.getSize();
        if (taskNumber == 0) { // Guard Clause
            System.out.println("☹ OOPS!!! You don't have any tasks yet. Why not try creating some?");
            return;
        }

        for (int i = 0; i < taskNumber; i++) {
            Task task = TaskList.get(i);
            saveMessage = saveTask(task);
            storingData.write(saveMessage + System.lineSeparator());
        }
        storingData.close();
    }

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

    public static void saveManager(TaskList taskList) {
        try {
            saveStorage();
        } catch (IOException e) {
            System.out.println("Sorry, but I have failed to save your list into the hard disk.");
        } catch (UnrecognisedCommandException e) {
            System.out.println(e.getMessage());
        }
    }
}
