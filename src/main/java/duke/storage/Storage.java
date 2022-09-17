package duke.storage;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.task.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final String TODO = "T";
    private static final String EVENT = "E";
    private static final String DEADLINE = "D";
    private static final String DONE = "1";
    private static final String UNDONE = "0";
    private static final String STORE_DIVIDER = " \\| ";
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadTasks() throws DukeException {
        try {
            File file = new File(filePath);
            ArrayList<Task> list = new ArrayList<>();
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String taskLine = scanner.nextLine();
                String[] analysedTaskLine = taskLine.split(STORE_DIVIDER);
                Task task = handleTaskLine(analysedTaskLine);
                list.add(task);
            }
            return list;
        } catch (FileNotFoundException e) {
            throw new DukeException("OOPS! I cannot find your file ☹");
        }
    }

    public void writeTasks(TaskList taskList) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            String formattedTaskList = taskList.formatTaskListToStringToStore();
            fileWriter.write(formattedTaskList);
            fileWriter.close();
        } catch (IOException e) {
            String fileDirectory = filePath.replace(filePath.substring(filePath.lastIndexOf("/")), "");
            File file = new File(fileDirectory);
            if (file.mkdir()) {
                writeTasks(taskList);
            } else {
                throw new DukeException("OOPS!!! Something went wrong when storing your tasks ☹");
            }
        }
    }

    public Task handleTaskLine(String[] analysedTaskLine) throws DukeException {
        Task task;
        switch (analysedTaskLine[0]) {
        case TODO:
            task = new Todo(analysedTaskLine[2]);
            break;
        case EVENT:
            task = new Event(analysedTaskLine[2], analysedTaskLine[3]);
            break;
        case DEADLINE:
            task = new Deadline(analysedTaskLine[2], analysedTaskLine[3]);
            break;
        default:
            throw new DukeException("Something went wrong with the tasks in your saved files ☹");
        }

        switch (analysedTaskLine[1]) {
        case DONE:
            task.setAsDone();
            break;
        case UNDONE:
            task.setAsUndone();
            break;
        default:
            throw new DukeException("Cannot understand your task status ☹");
        }

        return task;
    }
}
