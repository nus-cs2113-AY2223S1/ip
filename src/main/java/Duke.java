//package duke;
import java.util.Scanner;
import java.util.Arrays;


public class Duke {

    private static TasksList tasksList = new TasksList();

    public static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    public static void printGreeting() {
        printHorizontalLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printHorizontalLine();
    }

    public static void printExitText() {
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    public static void echo(String input) {
        System.out.println(input);
        printHorizontalLine();
    }

    public static void main(String[] args) throws EmptyArgumentException {
        printGreeting();
        String input;
        Scanner in = new Scanner(System.in);
        while (true) {
            input = in.nextLine();
            if (input.strip().isEmpty()) {
                throw new EmptyArgumentException();
            }
            String[] inputWords = input.split(" ", 2);
            switch (inputWords[0]) {
            case "bye":
                printExitText();
                break;
            case "list":
                tasksList.printTaskList();
                break;
            case "mark":
                int taskNumber =  Integer.parseInt(inputWords[1]) - 1;
                tasksList.markTask(taskNumber, "mark", true);
                break;
            case "unmark":
                taskNumber =  Integer.parseInt(inputWords[1]) - 1;
                tasksList.markTask(taskNumber, "unmark", false);
                break;
            case "todo":
                Todo newTodo = new Todo(inputWords[1], 'T');
                tasksList.addToTasksList(newTodo);
                break;
            case "deadline":
                String[] DescriptionWithTime = inputWords[1].split("/by ", 2);
                Deadline newDeadlineTask = new Deadline(DescriptionWithTime[0], 'D', DescriptionWithTime[1]);
                tasksList.addToTasksList(newDeadlineTask);
                break;
            case "event":
                DescriptionWithTime = inputWords[1].split("/at ", 2);
                Event newEvent = new Event(DescriptionWithTime[0], 'E', DescriptionWithTime[1]);
                tasksList.addToTasksList(newEvent);
                break;
            default:
                Task newTask = new Task(input, 'N');
                tasksList.addToTasksList(newTask);
                break;
            }
        }
    }
}
