package duke.task;

import java.util.ArrayList;
import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.ExceptionType;

public class TaskManager {

    public static ArrayList<Task> Tasks;
    public TaskManager() {
        Tasks = new ArrayList<>();
    }

    public static String addTask(Command taskType, String arguments) throws DukeException {
        switch (taskType) {
        case TODO:
            try {
                Tasks.add(new ToDo(arguments));
            } catch (DukeException e) {
                throw e;
            }
            break;
        case EVENT:
            try {
                Tasks.add(new Event(arguments));
            } catch (DukeException e) {
                throw e;
            }
            break;
        case DEADLINE:
            try {
                Tasks.add(new Deadline(arguments));
            } catch (DukeException e) {
                throw e;
            }
            break;
        default:
            break;
        }
        return Tasks.get(Tasks.size() - 1).listTask();
    }

    public static String listTask(int taskNumber) {
        return Tasks.get(taskNumber - 1).listTask();
    }

    public static String markAsDone(int taskNumber) throws DukeException {
        checkTaskNumberValid(taskNumber);
        Task task = Tasks.get(taskNumber - 1);
        task.markAsDone();
        return task.listTask();
    }

    public static String markAsNotDone(int taskNumber) throws DukeException {
        checkTaskNumberValid(taskNumber);
        Task task = Tasks.get(taskNumber - 1);
        task.markAsNotDone();
        return task.listTask();
    }

    public static String deleteTask(int taskNumber) throws DukeException {
        checkTaskNumberValid(taskNumber);
        String task = Tasks.get(taskNumber - 1).listTask();
        Tasks.remove(taskNumber - 1);
        return task;
    }

    private static void checkTaskNumberValid(int taskNumber) throws DukeException {
        if (taskNumber < 1 || taskNumber > Tasks.size()) {
            throw new DukeException(ExceptionType.INVALID_TASK_NUMBER);
        }
    }

}
