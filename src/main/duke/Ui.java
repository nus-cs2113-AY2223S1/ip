package main.duke;

import main.duke.exception.DukeException;
import main.duke.task.Task;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Ui {

    private static Scanner scanner;
    private TaskList taskList;
    private static Parser parser;

    public Ui(TaskList taskList) {
        scanner = new Scanner(System.in);
        parser = new Parser();
        this.taskList = taskList;
    }

    public void userInput() {
        while (!parseUserInput()) {

        }
    }

    /* Determine whether the user wants to create a task, exit, list, or mark */
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
            //if the item is to be marked or unmarked, follow the correct steps to extract the index
            if (matchesMark || matchesUnmark) {
                taskList.markOrUnmark(matchesMark, input);
            } else if (matchesDelete) {
                taskList.deleteTask(input);

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
