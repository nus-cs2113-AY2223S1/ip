import java.util.Scanner;

public class Duke {
    public static Boolean isRunning = true;

    public static void printGreetings() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public static String findTaskSpecifics(String details) {
        return details.substring(details.indexOf(" "), details.indexOf("/"));
    }

    public static void printDefaultTaskResponse(int number, Task[] tasks) {
        System.out.println("Got it. I've added this task:\n" + tasks[number].toString() + "\n"
                + "Now you have " + (number + 1) + " tasks in the list.");
    }

    public static void runTaskList() {
        Task[] tasks = new Task[100];
        String line;
        int number_of_tasks = 0;
        while (isRunning) {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            String[] details = line.split(" ");
            String command = details[0];
            switch (command) {
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                isRunning = false;
                break;
            case "deadline":
                String deadline_specifics = findTaskSpecifics(line);
                String deadline = line.substring(line.indexOf("by") + 2);
                tasks[number_of_tasks] = new Deadline(deadline_specifics, deadline);
                printDefaultTaskResponse(number_of_tasks,tasks);
                number_of_tasks++;
                break;
            case "todo":
                String todo_specifics = line.substring(line.indexOf(" "));
                tasks[number_of_tasks] = new Todo(todo_specifics);
                printDefaultTaskResponse(number_of_tasks,tasks);
                number_of_tasks++;
                break;
            case "event":
                String event_specifics = findTaskSpecifics(line);
                String event_date = line.substring(line.indexOf("at") + 2);
                tasks[number_of_tasks] = new Event(event_specifics, event_date);
                printDefaultTaskResponse(number_of_tasks,tasks);
                number_of_tasks++;
                break;
            case "mark":
                int task_to_mark = Integer.parseInt(details[1])-1;
                System.out.println("Nice! I've marked this task as done:\n");
                tasks[task_to_mark].markAsDone();
                System.out.println(tasks[task_to_mark].toString());
                break;
            case "unmark":
                int task_to_unmark = Integer.parseInt(details[1])-1;
                System.out.println("OK, I've marked this task as not done yet:\n");
                tasks[task_to_unmark].unmark();
                System.out.println(tasks[task_to_unmark].toString());
                break;
            default:
                System.out.println("Here are the tasks in your list:\n");
                for (int i = 0; i < number_of_tasks; i++) {
                    System.out.println((i + 1) + "." + tasks[i].toString());
                }
                break;
            }
        }
    }

    public static void main(String[] args) {
        printGreetings();
        runTaskList();
    }
}
