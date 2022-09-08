import java.util.ArrayList;

/**
* Add the ability to store whatever text entered by the user and display them back to the user when requested.
* */

public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<Task>();

    /** Constructor */

    public TaskList(){
    }

    public String getTaskDescription(int serialNumber){
        String taskDescriptionOutput = "  [";
        if (taskList.get(serialNumber - 1).getIsDone()){
            taskDescriptionOutput += "X] ";
        } else {
            taskDescriptionOutput += "] ";
        }
        taskDescriptionOutput += taskList.get(serialNumber -1).getTaskDescription() + "\n";
        return taskDescriptionOutput;
    }

    // Display taskList
    public String getTaskList(){
        String listOutput = "";
        for (int i = 0; i < taskList.size(); i++) {
            listOutput += Integer.toString(i + 1);
            listOutput += " .";
            listOutput += getTaskDescription(i + 1);
        }
        return listOutput;
    }

    // Add new task
    public void addNewTask(String taskDescription) {
        Task newTask = new Task(taskDescription);
        taskList.add(newTask);
    }


    // set completed status
    public void setMarkAsCompleted(int n, Boolean bool) {
        taskList.get(n - 1).setIsDone(bool);
    }

}
