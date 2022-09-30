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
    private final String TODO = "todo";
    private final String DEADLINE = "deadline";

    /**
     * add a Task base on the input of the user
     *
     * @param val input of user
     * @param tasks list of existing tasks
     * @param ui deals with interactions with the user
     * @param parser  deals with making sense of the user command
     */
    public void addTask(String val, ArrayList<Task> tasks, Ui ui, Parser parser, Storage storage) {
        String taskType = parser.taskType(val, ui);
        if(taskType == null){
            return;
        }

        String description = parser.description(val, ui, taskType);
        if(description == null && !taskType.equals(TODO)){
            return;
        }

        String time = parser.time(val, taskType);


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
            return;
        }

        try {
            storage.saveLine(tasks.get(tasks.size() - 1).toString());
        } catch (IOException e) {
            ui.failToSaveMsg();
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


        ui.removeTaskMsg(tasks.get(index));
        tasks.remove(index);
        ui.numOfTaskMsg(tasks.size());


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

    public void find(String val, ArrayList<Task> tasks, Parser parser, Ui ui){
        String key = parser.getKeyword(val,ui);
        if(key == null){
            return;
        }


        ui.findMsg();
        for(int i = 0; i < tasks.size(); i++){
            if(tasks.get(i).getDescription().contains(key)){
                ui.printTask(tasks.get(i));
            }
        }
    }

}
