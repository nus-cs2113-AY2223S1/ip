package Duke;

import Duke.Tasks.*;
import Duke.Exceptions.*;

import java.util.Scanner;

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

    public static void main(String[] args) throws EmptyArgumentException, InvalidCommandFormatException, TaskListEmptyException, TaskNumberOutOfBoundsException {
        printGreeting();
        String input;
        Scanner in = new Scanner(System.in);
        while (true) {
            input = in.nextLine();
            String[] inputWords = input.split(" ", 2);
            switch (inputWords[0]) {
            case "bye":
                printExitText();
                break;
            case "list":
                tasksList.printTaskList();
                break;
            case "mark":
                if (inputWords.length == 1) {
                    String correctFormatMessage = "The command should be 'mark (task number to mark)'.";
                    throw new InvalidCommandFormatException(correctFormatMessage);
                }
                int taskNumber =  Integer.parseInt(inputWords[1]) - 1;
                tasksList.markTask(taskNumber, "mark", true);
                break;
            case "unmark":
                if (inputWords.length == 1) {
                    String correctFormatMessage = "The command should be 'unmark (task number to mark)'.";
                    throw new InvalidCommandFormatException(correctFormatMessage);
                }
                taskNumber = Integer.parseInt(inputWords[ 1 ]) - 1;
                tasksList.markTask(taskNumber, "unmark", false);
                break;
            case "todo":
                if (inputWords.length < 2) {
                    String correctFormatMessage = "The command should be 'todo (task name)'.";
                    throw new InvalidCommandFormatException(correctFormatMessage);
                }
                Todo newTodo = new Todo(inputWords[1], 'T');
                tasksList.addToTasksList(newTodo);
                break;
            case "deadline":
                if (inputWords.length < 2) {
                    String correctFormatMessage = "The command should be 'deadline (task name) /by (deadline)'.";
                    throw new InvalidCommandFormatException(correctFormatMessage);
                }
                String[] DescriptionWithTime = inputWords[1].split("/by ", 2);
                Deadline newDeadlineTask = new Deadline(DescriptionWithTime[0], 'D', DescriptionWithTime[1]);
                tasksList.addToTasksList(newDeadlineTask);
                break;
            case "event":
                if (inputWords.length < 2) {
                    String correctFormatMessage = "The command should be 'event (task name) /by (event date)'.";
                    throw new InvalidCommandFormatException(correctFormatMessage);
                }
                DescriptionWithTime = inputWords[1].split("/at ", 2);
                Event newEvent = new Event(DescriptionWithTime[0], 'E', DescriptionWithTime[1]);
                tasksList.addToTasksList(newEvent);
                break;
            case "delete":
                if (inputWords.length < 2) {
                    String correctFormatMessage = "The command should be 'event (task name) /by (event date)'.";
                    throw new InvalidCommandFormatException(correctFormatMessage);
                }
                taskNumber = Integer.parseInt(inputWords[ 1 ]) - 1;
                tasksList.deleteTask(taskNumber);
                break;
            default:
                throw new EmptyArgumentException();
            }
        }
    }
}
