import java.util.ArrayList;

public class TaskList{

    public int size = 0;
    protected ArrayList<Task> list = new ArrayList<Task>();
    public TaskList(){}

    public void addToDo(String description){
        ToDo toDo = new ToDo(description);
        FileHandler.addTask(toDo);
        list.add(toDo);
        this.size++;
    }

    public void addDeadline(String description, String dueDate){
        Deadline deadline = new Deadline(description, dueDate);
        FileHandler.addTask(deadline);
        list.add(deadline);
        this.size++;
    }

    public void addEvent(String description, String dateTime){
        Event event = new Event(description, dateTime);
        FileHandler.addTask(event);
        list.add(event);
        this.size++;
    }

    public Task getTask(int index){
        return (Task)list.get(index);
    }

    public void printList(){
        for (int i=0; i<size; i++){
            Task tempTask = this.getTask(i);
            String taskType = tempTask.getTaskType();
            if (taskType == "T"){
                tempTask = (ToDo)tempTask;
                System.out.print((i+1) + ") ");
                System.out.print("[" + tempTask.getTaskType() + "]");
                System.out.print("[" + tempTask.getStatusIcon() + "] ");
                System.out.println(tempTask.description);   
            } else if (taskType == "D"){
                tempTask = (Deadline) tempTask;
                System.out.print((i+1) + ") ");
                System.out.print("[" + tempTask.getTaskType() + "]");
                System.out.print("[" + tempTask.getStatusIcon() + "] ");
                System.out.print(tempTask.description);
                System.out.println(" (by: " + tempTask.getDueDate() + ")");   
            } else if (taskType == "E"){
                tempTask = (Event) tempTask;
                System.out.print((i+1) + ") ");
                System.out.print("[" + tempTask.getTaskType() + "]");
                System.out.print("[" + tempTask.getStatusIcon() + "] ");
                System.out.print(tempTask.description);
                System.out.println(" (by: " + tempTask.getDateTime() + ")"); 
            }
        }
    }

    public Task searchTask(String task_description){
        for (int i=0; i<size; i++){
            Task tempTask = this.getTask(i);
            if (tempTask.description.equals(task_description)) return tempTask;
        }
        return null;
    }

    public int getTaskIndex(String task_description){
        int index;
        for (int i=0; i<size; i++){
            index = i;
            Task tempTask = this.getTask(i);
            if (tempTask.description.equals(task_description)) return index;
        }
        return -1;
    }

    public int getSize(){
        return size;
    }

    public void deleteTask(int index){
        list.remove(index);
        this.size--;
    }
}