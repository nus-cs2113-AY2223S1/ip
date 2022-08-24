import java.util.Scanner;

public class Duke {
    //========================================= GLOBAL VARIABLES ========================//
    static Scanner in = new Scanner(System.in);
    static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    static Task[] toDoList = new Task[100];


    //========================================= GLOBAL METHOD ========================//
    public static void echo(String line) { //Echo user input
        System.out.println(line);
    }

    public static String ask() {
        System.out.print(">>> ");
        return in.nextLine();
    }

    public static void list() {
        for (int i = 0; i < Task.numberOfTasks; i++) {
            System.out.print(Integer.toString(i+1) + ".");
            toDoList[i].printTask();
        }
    }

    public static void add(String line) {
        String newTaskType = line.split(" ")[0];
        if (!(newTaskType.equalsIgnoreCase("todo") || newTaskType.equalsIgnoreCase("deadline") || newTaskType.equalsIgnoreCase("event"))) {
            System.out.println("Please indicate task type: todo/deadline/event");
        } else {
            String[] taskDescriptor = line.replace(newTaskType, "").trim().split("/");
            switch (newTaskType.toLowerCase()) {
            case "todo":
                toDoList[Task.numberOfTasks] = new Todo(taskDescriptor[0]);
                break;
            case "deadline":
                toDoList[Task.numberOfTasks] = new Deadline(taskDescriptor[0], taskDescriptor[1].trim());
                break;
            case "event":
                toDoList[Task.numberOfTasks] = new Event(taskDescriptor[0], taskDescriptor[1].trim());
                break;
            default:
                break;
            }
            System.out.println("===================================================");
            System.out.println("Got it! You just add a new Task");
            System.out.print("\t");
            toDoList[Task.numberOfTasks - 1].printTask();
            System.out.println("Number of tasks in the list: " + Integer.toString(Task.numberOfTasks));
            System.out.println("===================================================");
        }
    }

    public static void mark(String line) {
        String[] parseArg = line.split(" ");
        int index = Integer.parseInt(parseArg[1]);
        if (parseArg[0].equalsIgnoreCase("mark")) {
            toDoList[index - 1].isDone = true;
        } else {
            toDoList[index - 1].isDone = false;
        }
        list();
    }


    //========================================= MAIN ========================//
    public static void main(String[] args) {
        String line;
        System.out.println(logo);

        //Increment Level 0
        System.out.println("===================================================");
        System.out.println("Hello! I'm Duke made by Than Duc Huy");
        System.out.println("Instruction on how to use Duke");
        System.out.println("> Type anything to add to to-do list");
        System.out.println("> Type \"list\" to list all the tasks");
        System.out.println("> Type \"bye\" to exit");
        System.out.println("What do you want to do with the to do list?");
        System.out.println("===================================================");

        while (true) {
            line = ask();
            if (line.toLowerCase().contains("bye")) { // If there is any bye, exit!
                break;
            } else if (line.equalsIgnoreCase("list")) {
                list();
            } else if (line.toLowerCase().contains("mark")) {
                mark(line);
            } else {
                add(line);
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("===================================================");
    }

}
