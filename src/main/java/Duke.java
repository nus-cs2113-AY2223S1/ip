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
            //Solution below adapted from https://github.com/kohnh/ip/blob/master/src/main/java/TaskManager.java
            if(val.contains("list")){
                printList(tasks, length);
                System.out.println(SEPARATOR);
                val = input.nextLine();
                continue;
            }


            String firstWord = val.substring(0, val.indexOf(' '));
            switch(firstWord){
            case "mark":
                markTask(val, tasks, true, "Nice! I've marked this task as done:");
                break;
            case "unmark":
                markTask(val, tasks, false, "OK, I've marked this task as not done yet:");
                break;
            case TODO:
                tasks[length] = new Todo(val.substring(TODO.length()));
                length++;
                System.out.println("Now you have " + length + " tasks in the list.");
                break;
            case DEADLINE:
                tasks[length] = new Deadline(val.substring(DEADLINE.length(), val.indexOf("/")), val.substring((val.indexOf("/") + 4)));
                length++;
                System.out.println("Now you have " + length + " tasks in the list.");
                break;
            case EVENT:
                tasks[length] = new Event(val.substring(EVENT.length(), val.indexOf("/")), val.substring((val.indexOf("/") + 4)));
                length++;
                System.out.println("Now you have " + length + " tasks in the list.");
                break;
            }

            System.out.println(SEPARATOR);
            val = input.nextLine();
        }

        byeMsg();
    }

    private static void markTask(String val, Task[] tasks, boolean status, String x) {
        int id = val.indexOf(" ");
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
