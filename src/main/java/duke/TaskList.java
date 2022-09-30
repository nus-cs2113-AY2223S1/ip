package duke;

import java.util.ArrayList;

/**
 * Class to handle list of tasks input by user.
 */
public class TaskList {
    protected ArrayList<Task> taskList = new ArrayList<Task>(0);
    private final String ILLEGAL_INDEX_ERROR = ":( %d is out of bounds of the task list";
    private final String UPDATE_TASK_FAILURE = ":( This task is already %sed";

    public void addTask(Task task){
        taskList.add(task);
    }

    /**
     * Updates the task with index specified by user and according to user command.
     *
     * @param index Task index.
     * @param userCommand Update action.
     * @throws IllegalInputException If task index is out of bounds or if task cannot be updated.
     */
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

    /**
     * Returns a Task created and initialised with task information.
     *
     * @param taskType Type of task.
     * @param details Task information.
     * @return Task created.
     */
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

    /**
     * Returns an ArrayList of Task consisting of search results.
     *
     * @param keyword Keyword to search for in task description.
     * @return Search results.
     */
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
