import java.util.Scanner;

public class Duke {
    private static String EVENT = "event";
    private static String TODO = "todo";
    private static String DEADLINE = "deadline";

    public static void main(String[] args) {
        welcomeMsg();

        Scanner input = new Scanner(System.in);
        String val = input.nextLine();

        Task[] tasks = new Task[100];
        int length = 0;

        while(!val.equals("bye")){
            System.out.println("____________________________________________________________");
            if(val.equals("list")){
                System.out.println("Here are the tasks in your list:");
                for(int i = 0; i < length; i++) {
                    System.out.println("  " + (i + 1) + "." + tasks[i]);
                }
            } else if (!val.contains("mark")) {
                System.out.println("Got it. I've added this task:");
                int index = val.indexOf("/");

                if(val.contains(TODO)){
                    tasks[length] = new Todo(val.substring(TODO.length()));
                } else if (val.contains(DEADLINE)) {
                    tasks[length] = new Deadline(val.substring(DEADLINE.length(), index),val.substring((index + 4)));
                } else if (val.contains(EVENT)) {
                    tasks[length] = new Event(val.substring(EVENT.length(), index),val.substring((index + 4)));
                }

                System.out.println(tasks[length]);
                length++;
                System.out.println("Now you have " + length + " tasks in the list.");
            } else if (val.indexOf("mark") == 0) {
                int id = val.indexOf(" ");
                String ind = val.substring(id + 1);
                int index = Integer.parseInt(ind) - 1;
                tasks[index].setDone(true);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks[index]);
            } else{
                int id = val.indexOf(" ");
                String ind = val.substring(id + 1);
                int index = Integer.parseInt(ind) - 1;
                tasks[index].setDone(false);
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(tasks[index]);
            }
            System.out.println("____________________________________________________________");
            val = input.nextLine();

        }

        byeMsg();
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
