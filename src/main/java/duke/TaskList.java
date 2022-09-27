package duke;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> taskList = new ArrayList<Task>(0);
    private final String ILLEGAL_INDEX_ERROR = ":( %d is out of bounds of the task list";
    private final String UPDATE_TASK_FAILURE = ":( This task is already %sed";

    public void addTask(Task task){
        taskList.add(task);
    }

    public void updateTaskStatus(int index, String userCommand) throws IllegalInputException{
        if (index > taskList.size() || index < 1){
            throw new IllegalInputException(String.format(ILLEGAL_INDEX_ERROR,index));
        }
        index--;
        boolean isDone = userCommand.equals("mark");
        Task task = taskList.get(index);
        if (isDone == task.isDone()){
            throw new IllegalInputException(String.format(UPDATE_TASK_FAILURE,userCommand));
        }
        task.setDone(isDone);
    }

    public Task deleteTask(int index) throws IllegalInputException{
        if (index > taskList.size() || index < 1){
            throw new IllegalInputException(String.format(ILLEGAL_INDEX_ERROR,index));
        }
        index--;
        Task taskToDelete = taskList.get(index);
        taskList.remove(index);
        return taskToDelete;
    };

    public ArrayList<Task> getTaskList(){
        return this.taskList;
    }

    public Task generateTask(String taskType, String[] details){
        switch (taskType){
        case "todo":
            return new ToDo(details[0]);
        case "deadline":
            return new Deadline(details[0], details[1]);
        case "event":
            return new Event(details[0], details[1]);
        default:
            return null;
        }
    }

    public ArrayList<Task> search(String keyword){
        ArrayList<Task> results = new ArrayList<>();
        for (Task task :taskList){
            if (task.description.contains(keyword)){
                results.add(task);
            }
        }
        return results;
    }

}
