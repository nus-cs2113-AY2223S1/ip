package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskManager;

import java.util.Scanner;

public class UI {

    static Scanner in = new Scanner(System.in);

    public static void line() {
        System.out.println("------------------------------");
    }

    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        line();
        System.out.println("Hi! I'm Slave Kai, Zhou Zhou's 256IQ bot\nHow can I help you?");
        line();
    }

    public UI() {
        greet();
    }

    public static String input() {
        return in.nextLine();
    }

    public static void output(String output) {
        System.out.println(output);
    }

    public static void respond(Command command) {
        line();
        switch (command) {
        case HI:
            System.out.println("Howdy!");
            break;
        case LIST:
            System.out.println("You have " + TaskManager.Tasks.size() + " tasks");
            for (Task task : TaskManager.Tasks) {
                System.out.println(task.listTask());
            }
            break;
        case HELP:
            System.out.println("You asked for help, but I don't feel like helping ;p\n" +
                    "Maybe try saying the magic word?");
            break;
        case PLEASE:
            System.out.println("Slave Kai is glad to help! \n" +
                    "Available commands: \n" +
                    "help - to try asking for help\n" +
                    "please - to actually get help\n" +
                    "list - to list all tasks\n" +
                    "mark {task number} - to mark that task as done\n" +
                    "unmark {task number} - to mark that task as not done\n" +
                    "todo {description} - to add a new todo task\n" +
                    "event {description} /{event time} - to add a new event task\n" +
                    "deadline {description} /{deadline time} - to add a new deadline task\n" +
                    "bye - to exit");
            break;
        case BYE:
            System.out.println("Please don't go :(");
            break;
        default:
            System.out.println("Invalid input. Boo! Please type help for help");
            break;
        }
        line();
    }

    public static void respond(Command command, String arguments) {
        line();
        switch (command) {
        case ADD:
            System.out.println("Task added:");
            System.out.println(arguments);
            break;
        case MARK:
            System.out.println("Well done. I've marked this task as done:");
            System.out.println(arguments);
            break;
        case UNMARK:
            System.out.println("Boo! I've marked this task as not done yet:");
            System.out.println(arguments);
            break;
        case DELETE:
            System.out.println("Task deleted:");
            System.out.println(arguments);
            break;
        default:
            System.out.println("Invalid input. Boo! Please type help for help");
            break;
        }
        line();
    }

    public static void respond(DukeException e) {
        line();
        switch (e.exceptionType) {
        case MISSING_DESCRIPTION:
            System.out.println("Please describe the task\n" +
                    "Follow this format:\n" +
                    "todo {description}\n" +
                    "Example: todo find a girlfriend\n" +
                    "event/deadline {description} /{time}\n" +
                    "Example: deadline find a girlfriend /last year");
            break;
        case MISSING_TIME:
            System.out.println("Please specify a time\n" +
                    "Follow this format:\n" +
                    "event/deadline {description} /{time}\n" +
                    "Example: event find a girlfriend /never");
            break;
        case INVALID_TASK_NUMBER:
            System.out.println("Invalid task number: task does not exist\n" +
                    "Please try again");
            break;
        default:
            System.out.println("Invalid input. Boo! Please type help for help");
            break;
        }
        line();
    }
}
