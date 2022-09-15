package duke;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static Boolean isRunning = true;
    public static String inputLine;
    public static ArrayList<Task> tasks = new ArrayList<>();

    public static void printGreetings() {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public static String findTaskSpecifics(String details) throws IllegalTaskException {
        String[] taskDetails = details.split(" ");
        if (taskDetails.length < 2) {
            throw new IllegalTaskException();
        }
        String typeOfTask = details.substring(details.indexOf(" "), details.indexOf("/"));
        return typeOfTask;
    }

    public static void printDefaultTaskResponse(int number, ArrayList<Task> tasks) {
        System.out.println("Got it. I've added this task:\n" + tasks.get(number-1).toString() +
                "\n" + "Now you have " + tasks.size() + " tasks in the list.");
    }

    public static void sayGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
        isRunning = false;
    }

    public static void insertDeadlineTask(String inputLine, ArrayList<Task> tasks) {
        try {
            String deadlineSpecifics = findTaskSpecifics(inputLine);
            String deadline = inputLine.substring(inputLine.indexOf("/by") + 3);
            tasks.add(new Deadline(deadlineSpecifics, deadline));
            printDefaultTaskResponse(tasks.size(),tasks);
        } catch (IllegalTaskException exception) {
            System.out.println("Uh oh, the description of a deadline task cannot be empty. Try again.");
        }
    }

    public static void insertToDoTask(String inputLine, ArrayList<Task> tasks) {
        try {
            String todoSpecifics = inputLine.substring(inputLine.indexOf(" "));
            tasks.add(new Todo(todoSpecifics));
            printDefaultTaskResponse(tasks.size(),tasks);
        } catch (StringIndexOutOfBoundsException exception) {
            System.out.println("Uh oh, the description of a todo task cannot be empty. Try again.");
        }
    }

    public static void insertEventTask(String inputLine, ArrayList<Task> tasks) {
        try {
            String eventSpecifics = findTaskSpecifics(inputLine);
            String eventDate = inputLine.substring(inputLine.indexOf("/at") + 3);
            tasks.add(new Event(eventSpecifics, eventDate));
            printDefaultTaskResponse(tasks.size(),tasks);
        } catch (IllegalTaskException exception) {
            System.out.println("Uh oh, the description of a event task cannot be empty. Try again");
        }
    }

    public static void printTaskList(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i+1 + "." + tasks.get(i).toString());
        }
    }

    public static void markTask(String[] taskDescriptions, ArrayList<Task> tasks) {
        int taskToMark = Integer.parseInt(taskDescriptions[1]) - 1;
        System.out.println("Nice! I've marked this task as done:\n");
        tasks.get(taskToMark).markAsDone();
        System.out.println(tasks.get(taskToMark).toString());
    }

    public static void unmarkTask(String[] taskDescriptions, ArrayList<Task> tasks) {
        int taskToUnmark = Integer.parseInt(taskDescriptions[1]) - 1;
        System.out.println("OK, I've marked this task as not done yet:\n");
        tasks.get(taskToUnmark).unmark();
        System.out.println(tasks.get(taskToUnmark).toString());
    }

    public static void runTaskList() {
        Scanner in = new Scanner(System.in);
        while (isRunning) {
            inputLine = in.nextLine();
            String[] details = inputLine.split(" ");
            String command = details[0];
            switch (command) {
            case "bye":
                sayGoodbye();
                break;
            case "deadline":
                insertDeadlineTask(inputLine, tasks);
                break;
            case "todo":
                insertToDoTask(inputLine, tasks);
                break;
            case "event":
                insertEventTask(inputLine, tasks);
                break;
            case "mark":
                markTask(details, tasks);
                break;
            case "unmark":
                unmarkTask(details, tasks);
                break;
            case "list":
                printTaskList(tasks);
                break;
            default:
                System.out.println("You have entered an invalid command, please try again.");
                break;
            }
        }
    }

    public static void main(String[] args) {
        printGreetings();
        runTaskList();
    }
}
