import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    static ArrayList<Task> tasklist = new ArrayList<>();

    public static void Echo(String input){
        Task task;
        String[] inputArr = input.split(" ");
        int task_no;
        switch (inputArr[0]){
            case ("bye"):
                System.out.println("-------------------------------------------------------------------------------");
                System.out.println("Bye. Hope to see you again soon");
                System.out.println("-------------------------------------------------------------------------------");
                break;
            case ("list"):
                System.out.println("-------------------------------------------------------------------------------");
                System.out.println("Here are the tasks in your list:");
                int i = 1;
                for (Task l: tasklist){
                    System.out.print(i + ".");
                    System.out.println(l);
                    i++;
                }
                System.out.println("-------------------------------------------------------------------------------");
                break;
            case ("mark"):
                task_no = Integer.parseInt(inputArr[1]);
                Task marktask = tasklist.get(task_no - 1);
                marktask.setisDone(true);
                System.out.println("-------------------------------------------------------------------------------");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(marktask);
                System.out.println("-------------------------------------------------------------------------------");
                break;
            case ("unmark"):
                task_no = Integer.parseInt(inputArr[1]);
                Task unmarktask = tasklist.get(task_no - 1);
                unmarktask.setisDone(false);
                System.out.println("-------------------------------------------------------------------------------");
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(unmarktask);
                System.out.println("-------------------------------------------------------------------------------");
            case ("todo"):
                task = createTodo(input);
                tasklist.add(task);
                System.out.println("-------------------------------------------------------------------------------");
                System.out.println("Got it. I've added this task:");
                System.out.println(task);
                System.out.println("Now you have " + tasklist.size() + " tasks in the list.");
                System.out.println("-------------------------------------------------------------------------------");
                break;
            case ("deadline"):
                task = createDeadline(input);
                tasklist.add(task);
                System.out.println("-------------------------------------------------------------------------------");
                System.out.println("Got it. I've added this task:");
                System.out.println(task);
                System.out.println("Now you have " + tasklist.size() + " tasks in the list.");
                System.out.println("-------------------------------------------------------------------------------");
                break;
            case ("event"):
                task = createEvent(input);
                tasklist.add(task);
                System.out.println("-------------------------------------------------------------------------------");
                System.out.println("Got it. I've added this task:");
                System.out.println(task);
                System.out.println("Now you have " + tasklist.size() + " tasks in the list.");
                System.out.println("-------------------------------------------------------------------------------");
                break;
            default:
                if (input.contains("unmark")){
                    break;
                }
        }
    }


    public static Task createTodo(String input){
        return new Todo(input.substring(5));
    }
    public static Task createDeadline(String input){
        int cutoff = input.indexOf("/by");
        return new Deadlines(input.substring(9, cutoff - 1 ), input.substring(cutoff + 4));
    }
    public static Task createEvent(String input){
        int cutoff = input.indexOf("/at");
        return new Events(input.substring(6, cutoff - 1), input.substring(cutoff + 4));
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner in = new Scanner(System.in);
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can i do for you?");
        System.out.println("-------------------------------------------------------------------------------");

        String input = new String("");

        while(input.contains("bye") == false){
            input = in.nextLine();
            Echo(input);
        }

    }
}
