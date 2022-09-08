import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.util.Scanner;

public class Duke {
    private static final String EVENT = "event";
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";

    private static final String SEPARATOR = "____________________________________________________________";
    private static final int LENGTH = 100;

    public static void main(String[] args) {
        welcomeMsg();

        Scanner input = new Scanner(System.in);
        String val = input.nextLine();

        Task[] tasks = new Task[LENGTH];
        int length = 0;

        while(!val.equals("bye")){
            System.out.println(SEPARATOR);
            if(val.equals("list")){
                printList(tasks, length);
            } else if (val.contains("unmark")) {
                markTask(val, tasks, false, "OK, I've marked this task as not done yet:");
            } else if (val.contains("mark")){
                markTask(val, tasks, true, "Nice! I've marked this task as done:");
            } else{
                length += addTask(val, tasks, length);
            }
            System.out.println(SEPARATOR);
            val = input.nextLine();
        }

        byeMsg();
    }

    private static int addTask(String val, Task[] tasks, int length) {
        if(val.contains(TODO)){
            if(val.substring(TODO.length()).trim().isEmpty()){
                emptyError(TODO);
                return 0;
            }

            tasks[length] = new Todo(val.substring(TODO.length()));

        } else if (val.contains(DEADLINE)) {
            if(val.substring(DEADLINE.length()).trim().isEmpty()){
                emptyError(DEADLINE);
                return 0;
            }
            tasks[length] = new Deadline(val.substring(DEADLINE.length(), val.indexOf("/")), val.substring((val.indexOf("/") + 4)));

        } else if (val.contains(EVENT)) {
            if(val.substring(EVENT.length()).trim().isEmpty()){
                emptyError(EVENT);
                return 0;
            }
            tasks[length] = new Event(val.substring(EVENT.length(), val.indexOf("/")), val.substring((val.indexOf("/") + 4)));

        }else{
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            return 0;
        }
        length++;
        System.out.println("Now you have " + length + " tasks in the list.");
        return 1;
    }

    private static void emptyError(String taskName){
        System.out.println( "☹ OOPS!!! The description of a " + taskName + " cannot be empty.");
    }

    private static void markTask(String val, Task[] tasks, boolean status, String x) {
        int id = val.indexOf(" ");
        if(id < 0){
            System.out.println("☹ OOPS!!! The index of the task cannot be empty.");
            return;
        }
        String ind = val.substring(id + 1);
        int index = Integer.parseInt(ind) - 1;
        tasks[index].setDone(status);
        System.out.println(x);
        System.out.println(tasks[index]);
    }

    private static void printList(Task[] tasks, int length) {
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < length; i++) {
            System.out.println("  " + (i + 1) + "." + tasks[i]);
        }
    }

    private static void byeMsg() {
        System.out.println("____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________");
    }

    private static void welcomeMsg() {
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Duke\n" +
                " What can I do for you?\n" +
                "____________________________________________________________");
    }
}
