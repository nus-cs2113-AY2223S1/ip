import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private final String EVENT = "event";
    private final String TODO = "todo";
    private final String DEADLINE = "deadline";

    //test whether will tasks change if i don't return it
    //didn't return here

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
            return;
        }

        try {
            Storage.saveLine(tasks.get(tasks.size() - 1).toString());
        } catch (IOException e) {
            ui.failToSaveMsg();
        }

        ui.numOfTaskMsg(tasks.size());
    }

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
