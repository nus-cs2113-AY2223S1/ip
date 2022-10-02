package process;
import java.io.IOException;
import java.util.ArrayList;

import command.Command;
import exception.DukeException;
import storage.Storage;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import ui.Ui;

/**
 * Handles all types of task 
 */
public class TaskManager {
    private static ArrayList<Task> taskList = new ArrayList<Task>();
    public boolean handleTask(String inputText) throws DukeException {
        Command command = Parser.parse(inputText);
        boolean isProgramEnd = command.execute(taskList);
        return isProgramEnd;
    }

    public void handleRestoration(String restoration) throws DukeException{
        final String[] splits = restoration.split(" \\| ");
        boolean isDone;
        String description;
        String dueTime;
        String eventTime;
        switch (splits[0]) {
        case "T":
            isDone = splits[1].equals("1") ? true : false;
            description = splits[2];
            Todo todo = new Todo(description,isDone);
            taskList.add(todo);
            break;
        case "D":
            isDone = splits[1].equals("1") ? true : false;
            description = splits[2];
            dueTime = splits[3];
            Deadline deadline = new Deadline(description,isDone,dueTime);
            taskList.add(deadline);
            break;
        case "E":
            isDone = splits[1].equals("1") ? true : false;
            description = splits[2];
            eventTime = splits[3];
            Event event = new Event(description,isDone,eventTime);
            taskList.add(event);
            break;
        default:
            throw new DukeException("RestorationFileCorrupted");
        }
    }

}
