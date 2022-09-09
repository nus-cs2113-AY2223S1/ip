package duke.storage;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.task.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final String TODO = "T";
    private static final String EVENT = "E";
    private static final String DEADLINE = "D";
    private static final String DONE = "1";
    private static final String UNDONE = "0";
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadTasks() throws DukeException {
        File file = new File(filePath);
        ArrayList<Task> list = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String taskLine = scanner.nextLine();
                String[] analysedTaskLine = taskLine.split(" | ");
                Task task = handleTaskLine(analysedTaskLine);
                list.add(task);
            }
            return list;
        } catch (FileNotFoundException e) {
            throw new DukeException("OOPS! I cannot find your file");
        }
    }

    public void saveTasks(TaskList taskList) {

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
            throw new DukeException("Something is wrong with the tasks in your saved files");
        }

        switch (analysedTaskLine[1]) {
        case DONE:
            task.setDone(true);
            break;
        case UNDONE:
            task.setDone(false);
            break;
        default:
            throw new DukeException("Cannot understand your task status!");
        }

        return task;
    }
}
