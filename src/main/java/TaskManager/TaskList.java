package TaskManager;
import java.util.ArrayList;
import UI.UI;

public class TaskList{
    /*
     * List information
     */
    protected static int size = 0;
    protected static ArrayList<Task> list = new ArrayList<Task>();

    /*
     * Adds ToDo task to list
     */
    public static void addToDo(String description){
        ToDo toDo = new ToDo(description);
        list.add(toDo);
        size++;
    }

    /*
     * Adds Deadline task to list
     */
    public static void addDeadline(String description, String dueDate){
        Deadline deadline = new Deadline(description, dueDate);
        list.add(deadline);
        size++;
    }

    /*
     * Adds Event task to list
     */
    public static void addEvent(String description, String dateTime){
        Event event = new Event(description, dateTime);
        list.add(event);
        size++;
    }

    /*
     * Retrieves task based on index
     */
    public static Task getTaskAtIndex(int index){
        return (Task)list.get(index);
    }

    /*
     * Prints all tasks and relevant info
     */
    public static void printList(){
        if (size == 0){
            System.out.println("Yay you have no tasks!");
            return;
        }
        System.out.println("Tasks:");
        for (int i=0; i<size; i++){
            Task tempTask = getTaskAtIndex(i);
            UI.printTaskInfo(tempTask);
        }
    }

    /*
     * Returns task in list based on taskDescription
     */
    public static Task searchTask(String taskDescription){
        for (int i=0; i<size; i++){
            Task tempTask = getTaskAtIndex(i);
            if (tempTask.description.equals(taskDescription)) return tempTask;
        }
        return null;
    }

    /*
     * Returns index of task in list based on taskDescription
     */
    public static int getTaskIndex(String taskDescription){
        int index;
        for (int i=0; i<size; i++){
            index = i;
            Task tempTask = getTaskAtIndex(i);
            if (tempTask.description.equals(taskDescription)) return index;
        }
        return -1;
    }

    /*
     * Returns size of list
     */
    public static int getSize(){
        return size;
    }

    /*
     * Removes task from list at index
     */
    public static void deleteTask(int index){
        list.remove(index);
        size--;
    }

    /*
     *  Removes task from list based on taskDescription
     */
    public static void deleteTask(String taskDescription){
        int index = getTaskIndex(taskDescription);
        list.remove(index);
        size--;
    }

    /*
     * Marks task as done based on index
     */
    public static void markTask(int index){
        list.get(index).markAsDone();
    }

    /*
     * Marks task as done based on taskDescription
     */
    public static void markTask(String taskDescription){
        int index = getTaskIndex(taskDescription);
        list.get(index).markAsDone();
    }

    /*
     * Marks task as not done based on index
     */
    public static void unmarkTask(int index){
        list.get(index).markAsNotDone();
    }

    /*
     * Marks task as not done based on taskDescription
     */
    public static void unmarkTask(String taskDescription){
        int index = getTaskIndex(taskDescription);
        list.get(index).markAsNotDone();
    }

    /*
     * Finds all tasks containing keyword
     * Returns result as an ArrayList of Tasks
     */
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