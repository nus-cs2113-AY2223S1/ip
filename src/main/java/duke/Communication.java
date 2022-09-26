package duke;

import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.File;

public class Communication {
    public static void greet(){
        System.out.println("Hello! I'm Duke ");
        System.out.println("What can I do for you? \n");
    }

    public static void bye(){
        System.out.println(" Bye. Hope to see you again soon! \n");
    }

    public static void copy(String userInput){
        System.out.println(userInput);
    }

    public static void list(ArrayList<Task> ListOfTasks) {
        for (Task task : ListOfTasks) {
            System.out.println(task.toString());
        }
    }
    public static void delete(Task task){
        Task.removeTask(task);
    }
    public static void mark(Task task){
        System.out.println("Nice! I've marked this task as done:");
        task.UpdateStatus();
        System.out.println(task.toString());

    }
    public static void unmark(Task task){
        System.out.println("OK, I've marked this task as not done yet:");
        task.UpdateStatus();
        System.out.println(task.toString());

    }

    public static Deadline addDeadline(String str){
        String action = str.split("/",2)[0];
        String time = str.split("/",2)[1].split("by ",2)[1];
        System.out.println("Got it. I've added this task;");
        Deadline newTask = Deadline.AddTask( action , time);
        Deadline.countRemainingTasks();
        return newTask;

    }
    public static Event addEvent(String str){
        String action = str.split("/",2)[0];
        String time = str.split("/",2)[1].split("at ",2)[1];
        System.out.println("Got it. I've added this task;");
        Event newEvent = Event.AddEvent( action , time);
        Event.countRemainingTasks();
        return newEvent;

    }
    public static ToDo addToDo(String str){
        System.out.println("Got it. I've added this task;");
        ToDo newTodo = ToDo.addToDo(str);
        ToDo.countRemainingTasks();
        return newTodo;

    }

    public static Event Events(String str, boolean isDone) {
        String name = str.split(" \\| ",2)[0];
        String at = str.split(" \\| ",2)[1];
        Event task = new Event(name, at);
        if (isDone) {
            task.UpdateStatus();
        }
        return task;
    }
    public static ToDo ToDos(String name, boolean isDone) {
        ToDo task = new ToDo(name);
        if (isDone) {
            task.UpdateStatus();
        }
        return task;
    }
    public static Deadline Deadlines(String str, boolean isDone) {
        String name = str.split(" \\| ",2)[0];
        String by = str.split(" \\| ",2)[1];

        Deadline task = new Deadline(name, by);
        if (isDone) {
            task.UpdateStatus();
        }
        return task;
    }
    public static ArrayList<Task> loading(File file) throws FileNotFoundException {
        ArrayList<Task> ListOfTasks = Cache.retrival(file);
        return ListOfTasks;
    }


}
