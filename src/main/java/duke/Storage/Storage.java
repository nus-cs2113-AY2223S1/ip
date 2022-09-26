package duke.Storage;

import duke.exception.StorageReadException;
import duke.exception.UnrecognisedCommandException;
import duke.manager.Parser;
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

    /*
        Will follow the format below:
        T | 1 | read book
        D | 0 | return book | June 6th
        E | 0 | project meeting | Aug 6th 2-4pm
    */

    private static String DEFAULT_FOLDER = "./data";
    private static String DEFAULT_FILE_PATH = "./data/duke.txt";
    private static String SPACES_BETWEEN_WORDS = " ";
    private static String SEPARATOR_LINE = "|";
    private static String SPACED_SEPARATOR = " | ";

    private static boolean getStoredBoolean(String input) throws StorageReadException {
        String[] splitInput = input.split(SPACES_BETWEEN_WORDS);
        int booleanNumber = Integer.parseInt(splitInput[2]);
        if (booleanNumber == 1) {
            return true;
        } else if (booleanNumber == 0) {
            return false;
        } else {
            throw new StorageReadException("boolean");
        }
    }

    private static String getStoredDescription(String unprocessedInput, int separatorPosition) {
        String description = unprocessedInput.substring(0, separatorPosition - 1);
        return description;
    }

    private static String getStoredTime(String unprocessedInput, int separatorPosition) {
        String time = unprocessedInput.substring(separatorPosition + 2);
        return time;
    }

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
            description = getStoredDescription(input, lastSeparator);
            time = getStoredTime(input, lastSeparator);
            currentTask = new Deadline(description, time);
            break;
        case "e":
            lastSeparator = input.lastIndexOf(SEPARATOR_LINE); // to account for existence of | in description
            description = getStoredDescription(input, lastSeparator);
            time = getStoredTime(input, lastSeparator);
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
            keyword = Parser.getKeyword(newLine);
            isDone = getStoredBoolean(newLine);
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
            storedTasks.addTask(currentTask);
        }
        return storedTasks;
    }

    private static String saveTodoMessage(Task task, String formattedKeyword) {
        String saveMessage = formattedKeyword + SPACED_SEPARATOR + task.isDone() + SPACED_SEPARATOR
                + task.getDescription();
        return saveMessage;
    }

    private static String saveDeadlineOrEventMessage(Task task, String formattedKeyword, String time) {
        String saveMessage = formattedKeyword + SPACED_SEPARATOR + task.isDone() + SPACED_SEPARATOR
                + task.getDescription() + SPACED_SEPARATOR + time;
        return saveMessage;
    }

    private static String saveTask(Task task) throws UnrecognisedCommandException {
        String keyword;
        String time;
        String saveMessage;
        keyword = task.getKeyword();
        switch (keyword) {
        case "todo":
            saveMessage = saveTodoMessage(task, "T");
            break;
        case "deadline":
            time = ((Deadline)task).getBy();
            saveMessage = saveDeadlineOrEventMessage(task, "D", time);
            break;
        case "event":
            time = ((Event)task).getAt();
            saveMessage = saveDeadlineOrEventMessage(task, "E", time);
            break;
        default:
            throw new UnrecognisedCommandException();
        }
        return saveMessage;
    }

    private static void saveStorage(TaskList taskList) throws IOException, UnrecognisedCommandException {
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
            saveStorage(taskList);
        } catch (IOException e) {
            System.out.println("Sorry, but I have failed to save your list into the hard disk.");
        } catch (UnrecognisedCommandException e) {
            System.out.println(e.getMessage());
        }
    }
}
