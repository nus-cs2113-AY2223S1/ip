import java.util.ArrayList;

public class TaskList{
    public int size = 0;
    protected ArrayList<Task> list = new ArrayList<Task>();

    public TaskList(){}

    public void addTask(String task_name){
        Task task = new Task(task_name);
        list.add(task);
        this.size++;
    }

    public Task getTask(int index){
        return list.get(index);
    }

    public void printList(){
        for (int i=0; i<size; i++){
            Task tempTask = this.getTask(i);
            System.out.print((i+1) + ") ");
            System.out.print("[" + tempTask.getStatusIcon() + "] ");
            System.out.println(tempTask.description);
        }
    }

    public Task searchTask(String task_description){
        for (int i=0; i<size; i++){
            Task tempTask = this.getTask(i);
            if (tempTask.description.equals(task_description)) return tempTask;
        }
        return null;
    }
}