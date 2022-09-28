package TaskManager;
import java.util.ArrayList;

public class TaskList{

    protected static int size = 0;
    protected static ArrayList<Task> list = new ArrayList<Task>();

    public static void addToDo(String description){
        ToDo toDo = new ToDo(description);
        list.add(toDo);
        size++;
    }

    public static void addDeadline(String description, String dueDate){
        Deadline deadline = new Deadline(description, dueDate);
        list.add(deadline);
        size++;
    }

    public static void addEvent(String description, String dateTime){
        Event event = new Event(description, dateTime);
        list.add(event);
        size++;
    }

    public static Task getTaskAtIndex(int index){
        return (Task)list.get(index);
    }

    public static void printList(){
        if (size == 0){
            System.out.println("Yay you have no tasks!");
            return;
        }
        System.out.println("Tasks:");
        for (int i=0; i<size; i++){
            Task tempTask = getTaskAtIndex(i);
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
                System.out.println(" (at: " + tempTask.getDateTime() + ")"); 
            }
        }
    }

    public static Task searchTask(String task_description){
        for (int i=0; i<size; i++){
            Task tempTask = getTaskAtIndex(i);
            if (tempTask.description.equals(task_description)) return tempTask;
        }
        return null;
    }

    public static int getTaskIndex(String task_description){
        int index;
        for (int i=0; i<size; i++){
            index = i;
            Task tempTask = getTaskAtIndex(i);
            if (tempTask.description.equals(task_description)) return index;
        }
        return -1;
    }

    public static int getSize(){
        return size;
    }

    public static void deleteTask(int index){
        list.remove(index);
        size--;
    }

    public static void deleteTask(String description){
        int index = getTaskIndex(description);
        list.remove(index);
        size--;
    }

    public static void markTask(int index){
        list.get(index).markAsDone();
    }

    public static void markTask(String description){
        int index = getTaskIndex(description);
        list.get(index).markAsDone();
    }

    public static void unmarkTask(int index){
        list.get(index).markAsNotDone();
    }

    public static void unmarkTask(String description){
        int index = getTaskIndex(description);
        list.get(index).markAsNotDone();
    }

    public static ArrayList<Task> find(String keyword){
        ArrayList<Task> result = new ArrayList<Task>();

        for (int i=0; i<size; i++){
            Task tempTask = getTaskAtIndex(i);
            if (tempTask.getDescription().contains(keyword)
                || tempTask.getDateTime().contains(keyword)
                || tempTask.getDueDate().contains(keyword)){
                result.add(tempTask);
            }
        }
        return result;
    }
}