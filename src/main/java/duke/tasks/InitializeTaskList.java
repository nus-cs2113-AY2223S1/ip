package duke.tasks;
/**
 * Initialises a taskList and sets message about the details of the task
 * TaskList <code>taskList</code> is the list of tasks from user
 * <code>initialisationMessage</code> is a String representing the results of initialisation of taskList
 */
public class InitializeTaskList {
    public TaskList taskList;
    public String initialisationMessage;
    public InitializeTaskList(TaskList taskList, String initialisationMessage){
        this.taskList = taskList;
        this.initialisationMessage = initialisationMessage;
    }
}
