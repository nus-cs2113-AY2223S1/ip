import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.IOException;
import java.util.ArrayList;

/**
 * includes all the operations that can be done on tasks
 */

public class TaskList {
    private final String EVENT = "event";
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";

    //test whether will tasks change if i don't return it
    //didn't return here



    /**
     * add a Task base on the input of the user
     *
     * @param val input of user
     * @param tasks list of existing tasks
     * @param ui deals with interactions with the user
     * @param parser  deals with making sense of the user command
     */
    public void addTask(String val, ArrayList<Task> tasks, Ui ui, Parser parser) {
        String taskType = parser.taskType(val, ui);
        if(taskType == null){
            return;
        }

        String description = parser.description(val, ui, taskType);
        if(description == null && !taskType.equals(TODO)){
            return;
        }

        String time = parser.time(val, ui, taskType);


        switch(taskType){
        case TODO:
            tasks.add(new Todo(description));
            break;
        case DEADLINE:
            tasks.add(new Deadline(description, time));
            break;
        case EVENT:
            tasks.add(new Event(description, time));
            break;
        default:
            ui.unknownError();
        }

        ui.numOfTaskMsg(tasks.size());
    }


    /**
     * Delete the task referred in val
     *
     * @param val input of user
     * @param tasks list of existing tasks
     * @param ui deals with interactions with the user
     * @param storage deals with making sense of the user command
     * @param parser  deals with making sense of the user command
     */
    public void deleteTask(String val, ArrayList<Task> tasks, Ui ui, Storage storage, Parser parser) {
        int index = parser.indexIs(val, ui, tasks.size());

        if(index == -1){
            return;
        }

        //试一下能不能把task remove放进括号里
        ui.removeTaskMsg(tasks.get(index));
        tasks.remove(index);
        ui.numOfTaskMsg(tasks.size());

        //要不要协调一点，把save都放在最外层
        try {
            storage.saveTask(tasks);
        } catch (IOException e) {
            ui.failToSaveMsg();
        }
    }

    /**
     * mark or unmark the task referred in val
     *
     * @param val input of user
     * @param tasks list of existing tasks
     * @param status true means mark the task, false means unmark the task
     * @param ui deals with interactions with the user
     * @param parser  deals with making sense of the user command
     */
    public void markTask(String val, ArrayList<Task> tasks, boolean status, Ui ui, Parser parser) {
        int index = parser.indexIs(val, ui, tasks.size());

        if(index == -1){
            return;
        }
        tasks.get(index).setDone(status);
        ui.markTaskMsg(status, tasks.get(index));
    }


}
