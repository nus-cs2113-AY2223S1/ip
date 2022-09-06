package Duke;
import java.util.ArrayList;

public class TaskManager {
    private final static ArrayList<Task> tasks = new ArrayList<>();

    public void addTasks(Task newTask) {
        tasks.add(newTask);
    }
    public void markDone(int taskNumber) {
        tasks.get(taskNumber).setDone(true);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println();
    }

    public void unmarkDone(int taskNumber) {
        tasks.get(taskNumber).setDone(false);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println();
    }
    public void printList() {
        System.out.println("\tHere are the tasks in your list:");
        for(Task element : tasks){
            element.printTask();
        }
    }

    public void printTask(int i) {
        tasks.get(i).printTask();
    }

    public int getSize() {
        return tasks.size();
    }

    public void printSize() {
        System.out.println("\t There are currently " + tasks.size() + " task(s) in the list :)");
    }
}
