package main.duke;
import main.duke.exception.DukeException;
import main.duke.task.Task;
import java.util.Scanner;

/** Controls the interaction between the user and the Duke task list */
public class Ui {

    private static Scanner scanner;
    private TaskList taskList;
    private static Parser parser;

    /** Creates an instance of UI with an intialized scanner and parser, given a task list */
    public Ui(TaskList taskList) {
        scanner = new Scanner(System.in);
        parser = new Parser();
        this.taskList = taskList;
    }

    /** Runs a loop to collect and parse user input until the user leaves */
    public void userInput() {
        while (!parseUserInput()) {

        }
    }

    /** Parse the user input to determine the right action (delete, mark, add, etc.)  */
    private boolean parseUserInput() {
        String input = scanner.nextLine();
        String inputType = input.toLowerCase();
        boolean shouldExit = false;

        switch (inputType) {
        case "bye":
            shouldExit = true;
            break;
        case "list":
            System.out.print(Utils.H_LINE);
            taskList.printList();
            System.out.println(Utils.H_LINE + "\n");
            break;
        default:
            //create patterns for checking both mark and unmark
            boolean matchesMark = parser.getMarkPattern().matcher(input).find();
            boolean matchesUnmark = parser.getUnmarkPattern().matcher(input).find();
            boolean matchesDelete = parser.getDeletePattern().matcher(input).find();
            //if the item is to be marked, unmarked, or deleted follow the correct steps to extract the index
            if (matchesMark || matchesUnmark) {
                taskList.markOrUnmark(matchesMark, input);
            } else if (matchesDelete) {
                taskList.deleteTask(input);
            } else if (input.startsWith("find")) {
                taskList.findTasksMatch(input.substring(5));

             //Otherwise, create a new task
            } else {
                try {
                    int listIndex = Utils.getListIndex();
                    if (listIndex > Utils.TASK_LIMIT) {
                        throw new DukeException("Adding this task exceeds the task limit of " + Utils.TASK_LIMIT);
                    }
                    Task task = taskList.createTask(input);
                    System.out.println(Utils.H_LINE + Utils.INDENT
                            + "Success!" + Utils.INDENT + "Added: " + (listIndex + 1) + ". " + task + Utils.H_LINE + "\n");
                    taskList.add(listIndex, task);
                    Utils.incrementListIndex();

                } catch (DukeException dukeException) {
                    System.out.println(Utils.H_LINE + Utils.INDENT
                            + dukeException.getMessage() + Utils.INDENT + "Task not added: "
                            + input + Utils.INDENT + "Please try again!" + Utils.H_LINE + "\n");
                }
            }
        }
        return shouldExit;
    }
}
