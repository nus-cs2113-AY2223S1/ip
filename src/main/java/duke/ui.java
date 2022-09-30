package duke;

import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.File;

/*
 *The user interface
 */
public class ui {
    /**
     * Greets
     */
    public static void greet(){
        System.out.println("Hello! I'm Duke ");
        System.out.println("What can I do for you? \n");
    }

    /**
     * Says goodbye.
     */
    public static void bye(){
        System.out.println(" Bye. Hope to see you again soon! \n");
    }

    /**
     * Prints current task list.
     * @param ListOfTasks The task list retrieved directly from the storage.
     */
    public static void list(ArrayList<TaskList> ListOfTasks) {
        int index = 0;
        for (TaskList task : ListOfTasks) {
            index = index +1;
            System.out.println(index +". "+ task.toString());
        }
    }
    /**
     * Deletes a task from the list and prints out the task.
     * @param task The object which the user wants to delete
     */
    public static void delete(TaskList task){
        TaskList.removeTask(task);
    }

    /**
     * Marks task with index specified as done.
     * @param task Mark task with index specified fetched from the task list.
     */
    public static void mark(TaskList task){
        System.out.println("Nice! I've marked this task as done:");
        task.UpdateStatus();
        System.out.println(task.toString());

    }
    /**
     * Marks task with index specified as not done.
     * @param task Mark task with index specified fetched from the task list.
     */
    public static void unmark(TaskList task){
        System.out.println("OK, I've marked this task as not done yet:");
        task.UpdateStatus();
        System.out.println(task.toString());

    }
    /**
     * Add a new Deadline.
     * @param str the description of deadline + by when
     * @return a newly created Deadline class for storage.
     */

    public static Deadline addDeadline(String str){
        String action = str.split("/",2)[0];
        String time = str.split("/",2)[1].split("by ",2)[1];
        System.out.println("Got it. I've added this task;");
        Deadline newTask = Deadline.AddTask( action , time);
        Deadline.countRemainingTasks();
        return newTask;

    }
    /**
     * Adds a new Event.
     * @param str The description of event + at what time
     * @return A newly created Event class for storage.
     */
    public static Event addEvent(String str){
        String action = str.split("/",2)[0];
        String time = str.split("/",2)[1].split("at ",2)[1];
        System.out.println("Got it. I've added this task;");
        Event newEvent = Event.AddEvent( action , time);
        Event.countRemainingTasks();
        return newEvent;

    }
    /**
     * Adds a new ToDo task.
     * @param str The description of ToDo task.
     * @return A newly created ToDo class for storage.
     */
    public static ToDo addToDo(String str){
        System.out.println("Got it. I've added this task;");
        ToDo newTodo = ToDo.addToDo(str);
        ToDo.countRemainingTasks();
        return newTodo;

    }

    /**
     * Syncs a Event from cache file.
     * @param str The description of Event + at what time.
     * @param isDone A boolean indicator to show if the task is marked done.
     * @return A newly created Events class for storage
     */
    public static Event Events(String str, boolean isDone) {
        String name = str.split(" \\| ",2)[0];
        String at = str.split(" \\| ",2)[1];
        Event task = new Event(name, at);
        if (isDone) {
            task.UpdateStatus();
        }
        return task;
    }
    /**
     * Syncs a Todo from cache file.
     * @param name The description of Todo
     * @param isDone A boolean indicator to show if the task is marked done.
     * @return A newly created Todo class for storage.
     */
    public static ToDo ToDos(String name, boolean isDone) {
        ToDo task = new ToDo(name);
        if (isDone) {
            task.UpdateStatus();
        }
        return task;
    }
    /**
     * Syncs a Deadline from cache file.
     * @param str The description of deadline + by when.
     * @param isDone A boolean indicator to show if the task is marked done.
     * @return A newly created Deadline class for storage.
     */
    public static Deadline Deadlines(String str, boolean isDone) {
        String name = str.split(" \\| ",2)[0];
        String by = str.split(" \\| ",2)[1];

        Deadline task = new Deadline(name, by);
        if (isDone) {
            task.UpdateStatus();
        }
        return task;
    }

    /**
     * Loads previous cache file contents and adds to current task list.
     * @param file The duke.txt in .\data\ folder
     * @return The synced task list.
     */
    public static ArrayList<TaskList> loading(File file) throws FileNotFoundException {
        ArrayList<TaskList> ListOfTasks = Storage.retrival(file);
        return ListOfTasks;
    }

    /**
     * Prints list of tasks that satisfies the requirements.
     * @param keyword The substring.
     * @param tasks The storage.
     */
    public static void find(String keyword, ArrayList<TaskList> tasks){

        ArrayList<TaskList> results = new ArrayList<>();
        Integer index =0;
        for (TaskList task: tasks){
            if (task.name.contains(keyword)){
                results.add(task);
            }

        }
        if (results.isEmpty()){
            System.out.println("No matching tasks were found unfortunately :(");
        }
        else {
            System.out.println("Here are the matching tasks in your list:");
        }
        for (TaskList task : results) {
            index = index + 1;
            System.out.println(index +". "+ task.toString());
        }
    }


}
