package Duke;
import java.util.ArrayList;

public class TaskManager {

    private static final int RESIZE_FACTOR = 2;
    private static int NUM_TASK;
    private static int taskCount;

    private static ArrayList<Task> tasks;

    public TaskManager() {
        NUM_TASK = 100;
        taskCount = 0;
        tasks = new ArrayList<Task>(NUM_TASK);
    }

    private void resize() {
        ArrayList<Task> buffer = new ArrayList<Task>(NUM_TASK * RESIZE_FACTOR);

        if (taskCount >= 0) {
            System.arraycopy(tasks, 0, buffer, 0, taskCount);
        }

        tasks = buffer;
        NUM_TASK *= RESIZE_FACTOR;
    }

    private void printSummary() {
        System.out.println("Beep boop, now you have " + taskCount + " tasks");

        if(taskCount == NUM_TASK){
            System.out.println("BEEP BEEP BEEEEEEP! LIST FULL!!! Increasing list capacity");
            resize();
        }

    }

    public void clearAllTask() {
        taskCount = 0;
        tasks = new ArrayList<Task>(NUM_TASK);
    }

    public static void listAllTask() {
        System.out.println("Beep beep, listing out the tasks....Loading.....");
        for(int i = 0; i < taskCount; i++){
            System.out.println("\t" + (i + 1) + "." + tasks.get(i).toString());
        }
    }

    public void addTask(String descriptionTask) {
        tasks.add( new Task(descriptionTask));
        taskCount += 1;

        System.out.println("added: " + descriptionTask);

        System.out.println("\t" + tasks.get(taskCount - 1).toString());
        printSummary();
    }

    public void addTodo(String descriptionToDo) {
        tasks.add( new Todo(descriptionToDo));
        taskCount += 1;

        System.out.println("HELLO BEEP, added a new ToDo: ");

        System.out.println("\t" + tasks.get(taskCount - 1).toString());
        printSummary();
    }

    public void addDeadline(String description, String deadlineBy) {
        tasks.add( new Deadline(description, deadlineBy) );
        taskCount += 1;

        System.out.println("OH NO BEEP BEEP, a new Deadline: " + description);

        System.out.println("\t" + tasks.get(taskCount - 1).toString());
        printSummary();
    }

    public void addEvent(String description, String eventAt) {
        tasks.add( new Event(description, eventAt));
        taskCount += 1;

        System.out.println("OH NO BEEP BEEP, a new Event: ");

        System.out.println("\t" + tasks.get(taskCount - 1).toString());
        printSummary();
    }

    public void setTask(int taskIndex, boolean isDone) {
        final String MESSAGE_DONE = "Nice! I've marked this task as done:";
        final String MESSAGE_NOT_DONE = "OK, I've marked this task as not done yet:";
        final String ERROR_OUT_OF_BOUND = "Sorry, the task does not seem to exist :<";

        tasks.get(taskIndex).setStatus(isDone);

        if (taskIndex > taskCount) { //to add exception here
            System.out.println(ERROR_OUT_OF_BOUND);
            return;
        }

        if (isDone) {
            System.out.println(MESSAGE_DONE);
        } else {
            System.out.println(MESSAGE_NOT_DONE);
        }

        System.out.println("\t" + tasks.get(taskIndex).toString());
    }

    public void deleteTask(int taskIndex) {
        final String MESSAGE_DELETE = "Noted. I've removed this task: ";

        System.out.println(MESSAGE_DELETE);
        System.out.println("\t" + tasks.get(taskIndex).toString());
        tasks.remove(taskIndex);
        taskCount -= 1;

        printSummary();
    }

}
