package duke.storage;

import static duke.common.Constants.FILE_SEPARATOR;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.exception.StorageLoadException;
import duke.exception.StorageWriteException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.tasklist.TaskList;

public class Storage {
    private final File file;

    /**
     * Creates a new storage object that reads from the specified relative filename.
     *
     * @param filename Relative file path to load database from.
     */
    public Storage(String filename) {
        this.file = new File(filename);
    }

    /**
     * Reads from the file containing the task information and returns a new {@link TaskList} containing the tasks.
     *
     * @return TaskList consisting of the tasks read from the file.
     */
    public TaskList load() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner fileInput = new Scanner(file);
            while (fileInput.hasNext()) {
                String line = fileInput.nextLine();
                String[] fields = line.split(FILE_SEPARATOR);
                String taskType = fields[0];
                boolean taskStatus = Boolean.parseBoolean(fields[1]);
                String description = fields[2];
                Task currTask;
                switch (taskType) {
                case "T":
                    currTask = new Todo(description);
                    break;
                case "D":
                    String by = fields[3];
                    currTask = new Deadline(description, by);
                    break;
                case "E":
                    String at = fields[3];
                    currTask = new Event(description, at);
                    break;
                default:
                    throw new StorageLoadException("Invalid type identifier");
                }
                currTask.setDone(taskStatus);
                tasks.add(currTask);
            }
        } catch (Exception e) {
            throw new StorageLoadException(e);
        }
        return new TaskList(tasks);
    }

    /**
     * Reads from the file containing the task information and returns a new {@link TaskList} containing the tasks.
     * If the file does not exist, creates an empty file with the filename and returns an empty {@link TaskList}.
     *
     * @return TaskList consisting of the tasks read from the file.
     */
    public TaskList createOrLoad() {
        try {
            if (file.createNewFile()) { // false if file exists
                return new TaskList();
            }
        } catch (IOException e) {
            throw new StorageLoadException(e);
        }
        return load();
    }

    /**
     * Encodes and saves the provided TaskList to the file.
     *
     * @param tasks TaskList containing the tasks to save.
     */
    public void save(TaskList tasks) {
        List<Task> taskList = tasks.asList();
        try {
            FileWriter writer = new FileWriter(file);
            for (Task task : taskList) {
                String taskType;
                String timeField = "";
                if (task instanceof Todo) {
                    taskType = "T";
                } else if (task instanceof Deadline) {
                    taskType = "D";
                    timeField = ((Deadline) task).getBy();
                } else if (task instanceof Event) {
                    timeField = ((Event) task).getAt();
                    taskType = "E";
                } else {
                    throw new StorageWriteException("Invalid task encountered");
                }
                String line = taskType + FILE_SEPARATOR + task.isDone() + FILE_SEPARATOR + task.getDescription() +
                        FILE_SEPARATOR + timeField;
                writer.write(line + System.lineSeparator());
            }
            writer.close();
        } catch (Exception e) {
            throw new StorageWriteException(e);
        }
    }
}
