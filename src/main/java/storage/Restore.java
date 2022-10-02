package storage;

import java.util.ArrayList;

import exception.DukeException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

public class Restore {

    /**
     * Handles the restoration of tasks
     * 
     * @param taskList    the task list
     * @param restoreLine the input line that needs to be restored
     * @throws DukeException if something wrong happens in restoration
     */
    public void restoreFromLine(ArrayList<Task> taskList, String restoreLine) throws DukeException {
        final String TASK = "T";
        final String DEADLINE = "D";
        final String EVENT = "E";
        final String[] splits = restoreLine.split(" \\| ");
        boolean isDone;
        String description;
        String dueTime;
        String eventTime;
        switch (splits[0]) {
        case TASK:
            if (splits.length != 3) {
                throw new DukeException(DukeException.RESTORATION_FILE_CORRUPTED_ERROR);
            }
            isDone = splits[1].equals("1") ? true : false;
            description = splits[2];
            Todo todo = new Todo(description, isDone);
            taskList.add(todo);
            break;
        case DEADLINE:
            if (splits.length != 4) {
                throw new DukeException(DukeException.RESTORATION_FILE_CORRUPTED_ERROR);
            }
            isDone = splits[1].equals("1") ? true : false;
            description = splits[2];
            dueTime = splits[3];
            Deadline deadline = new Deadline(description, isDone, dueTime);
            taskList.add(deadline);
            break;
        case EVENT:
            if (splits.length != 4) {
                throw new DukeException(DukeException.RESTORATION_FILE_CORRUPTED_ERROR);
            }
            isDone = splits[1].equals("1") ? true : false;
            description = splits[2];
            eventTime = splits[3];
            Event event = new Event(description, isDone, eventTime);
            taskList.add(event);
            break;
        default:
            throw new DukeException(DukeException.RESTORATION_FILE_CORRUPTED_ERROR);
        }
    }
}
