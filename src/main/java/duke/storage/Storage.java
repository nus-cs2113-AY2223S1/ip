package duke.storage;

import duke.data.TaskList;
import duke.data.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Provides reading of file input into the program
 * and writing from program back into the file.
 */
public class Storage {
    private static final String DIRECTORY_PATH = "data";
    private static final String FILE_PATH = "data/duke.txt";
    private static final String NUMERIC_DONE = "1";
    private static final String DELIMITER_WITH_BACKSLASH = " \\| ";
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String TASK_TYPE_TODO = "T";
    private static final String TASK_TYPE_DEADLINE = "D";
    private static final String TASK_TYPE_EVENT = "E";

    private TaskList tasks;

    /**
     * Instantiates a new task list when user initialises a new instance of this class.
     */
    public Storage() {
        this.tasks = new TaskList();
    }

    /**
     * Checks if directory or file exists.
     * If either or both do not exist, program will create them.
     *
     * @return The file that the program will be working with.
     * @throws IOException If an error is caught in the IO stream.
     */
    public static File prepareFile() throws IOException {
        //@@author chydarren-reused
        // Reused from https://stackoverflow.com/a/38985883
        // with minor modifications
        File directory = new File(DIRECTORY_PATH);
        // Creates a new directory if the directory does not exist
        if(!directory.exists()) {
            directory.mkdir();
        }

        File file = new File(FILE_PATH);
        // Creates a new file if the file does not exist
        if(!file.exists()) {
            file.createNewFile();
        }
        return file;
    }

    /**
     * Reads each line of file input and initialises the task list with those inputs.
     *
     * @return A task list that the program will be working with.
     * @throws IOException If an error is caught in the IO stream.
     */
    public TaskList readFromFile() throws IOException {
        File file = prepareFile();
        Scanner in = new Scanner(file);
        // Loops each time there is a next line found in the file input
        while (in.hasNext()) {
            // Splits each line in file based on the delimiter: |
            String[] inputSplits = in.nextLine().split(DELIMITER_WITH_BACKSLASH);
            // Sets boolean isDone variable to indicate whether the task is done or undone
            boolean isDone = inputSplits[1].equals(NUMERIC_DONE);
            // Adds each task from the file input into the task list based on the task type: T, D or E
            switch (inputSplits[0]) {
            case TASK_TYPE_TODO:
                tasks.addTodo(inputSplits[2], isDone);
                break;
            case TASK_TYPE_DEADLINE:
                tasks.addDeadline(inputSplits[2], inputSplits[3], isDone);
                break;
            case TASK_TYPE_EVENT:
                tasks.addEvent(inputSplits[2], inputSplits[3], isDone);
                break;
            default:
                break;
            }
        }
        return tasks;
    }

    /**
     * Writes each task from the task list into the file.
     *
     * @param tasks A task list that the program has been working with.
     * @throws IOException If an error is caught in the IO stream.
     */
    public static void writeToFile(TaskList tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(FILE_PATH);
        // Loops each task from the task list
        for (Task task : tasks.getTasks()) {
            // Formats the task into the required format for writing to file
            String taskFileEntry = tasks.getTaskFileEntry(task);
            fileWriter.write(taskFileEntry + LINE_SEPARATOR);
        }
        fileWriter.close();
    }
}
