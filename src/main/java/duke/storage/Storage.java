package duke.storage;

import duke.data.TaskList;
import duke.data.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

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

    public Storage() {
        this.tasks = new TaskList();
    }

    public static File prepareFile() throws IOException {
        //@@author chydarren-reused
        // Reused from https://stackoverflow.com/a/38985883
        // with minor modifications
        File directory = new File(DIRECTORY_PATH);
        if(!directory.exists()) {
            directory.mkdir();
        }

        File file = new File(FILE_PATH);
        if(!file.exists()) {
            file.createNewFile();
        }
        return file;
    }

    public TaskList readFromFile() throws IOException {
        File file = prepareFile();
        Scanner in = new Scanner(file);
        while (in.hasNext()) {
            String[] inputSplits = in.nextLine().split(DELIMITER_WITH_BACKSLASH);
            boolean isDone = inputSplits[1].equals(NUMERIC_DONE);
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

    public static void writeToFile(TaskList tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(FILE_PATH);
        for (Task task : tasks.getTasks()) {
            String taskFileEntry = tasks.getTaskFileEntry(task);
            fileWriter.write(taskFileEntry + LINE_SEPARATOR);
        }
        fileWriter.close();
    }
}
