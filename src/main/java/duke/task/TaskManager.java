package duke.task;

import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.ExceptionType;

public class TaskManager {

    private static final int TASKS_LIMIT = 100;
    public static Task[] Tasks = new Task[TASKS_LIMIT];

    public TaskManager() {
    }

    public static int getTasksCount() {
        return Task.tasksCount;
    }

    public static void addTask(Command taskType, String arguments) throws DukeException {
        Task.tasksCount++;
        switch (taskType) {
        case TODO:
            try {
                Tasks[Task.tasksCount] = new ToDo(arguments);
            } catch (DukeException e) {
                Task.tasksCount--;
                throw e;
            }
            break;
        case EVENT:
            try {
                Tasks[Task.tasksCount] = new Event(arguments);
            } catch (DukeException e) {
                Task.tasksCount--;
                throw e;
            }
            break;
        case DEADLINE:
            try {
                Tasks[Task.tasksCount] = new Deadline(arguments);
            } catch (DukeException e) {
                Task.tasksCount--;
                throw e;
            }
            break;
        default:
            break;
        }
    }

    public static String listTask(int taskNumber) {
        return Tasks[taskNumber].listTask();
    }

    public static void markAsDone(int taskNumber) throws DukeException {
        if (taskNumber < 1 || taskNumber > Task.tasksCount) {
            throw new DukeException(ExceptionType.INVALID_TASK_NUMBER);
        }
        Tasks[taskNumber].markAsDone();
    }

    public static void markAsNotDone(int taskNumber) throws DukeException {
        if (taskNumber < 1 || taskNumber > Task.tasksCount) {
            throw new DukeException(ExceptionType.INVALID_TASK_NUMBER);
        }
        Tasks[taskNumber].markAsNotDone();
    }

    public static void loadTask(Task task) {
        Tasks[getTasksCount()] = task;
    }
}
