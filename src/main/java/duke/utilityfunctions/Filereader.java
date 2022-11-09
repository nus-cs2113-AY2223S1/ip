package duke.utilityfunctions;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * This class handles generation of tasks after user has keyed in inputs to create new todos/events/deadlines.
 */
public class Filereader {
    /**
     * Generates tasks of the Task datatype by taking in input strings
     * @param input the input string provided by the user
     * @return the task of Task datatype
     */
    public Task generateTask(String input) {
        Task task = null;
        char taskIcon = input.charAt(1);
        char taskStatus = input.charAt(4);
        if(taskIcon == 'E'){
            int firstDivider = input.indexOf("]",5 );
            String taskDesc = input.substring(firstDivider + 2);
            task = new Event(taskDesc);
        } else if (taskIcon == 'D') {
            int firstDivider = input.indexOf("]",5 );
            String taskDesc = input.substring(firstDivider + 2);
            task = new Deadline(taskDesc);
        } else if (taskIcon == 'T') {
            int firstDivider = input.indexOf("]",5 );
            String taskDesc = input.substring(firstDivider + 2);
            task = new Todo(taskDesc);
        }
        if(taskStatus == 'X'){
            task.markTask();
        }
        return task;
    }
}
