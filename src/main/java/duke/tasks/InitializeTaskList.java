package duke.tasks;

public class InitializeTaskList {
    public TaskList taskList;
    public String initialisationMessage;
    public InitializeTaskList(TaskList taskList, String initialisationMessage){
        this.taskList = taskList;
        this.initialisationMessage = initialisationMessage;
    }
}
