package duke;

import duke.exceptions.UnrecognizedCommandException;

import java.util.Scanner;

public class Ui {

    public static final String DUKE_LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String DEADLINE_SEPERATOR_ERROR_MESSAGE = "Incorrect format! Deadline must contain a task and its deadline separated by '/by'! Refer to 'help' for more details!";
    public static final String MISSING_DEADLINE_DESCRIPTION_ERROR_MESSAGE = "Incorrect format! The description of a deadline cannot be empty!";
    public static final String MISSING_DEADLINE_TIME_ERROR_MESSAGE = "Incorrect format! The deadline of a deadline cannot be empty!";
    public static final String MISSING_EVENT_DESCRIPTION_ERROR_MESSAGE = "Incorrect format! The description of an event cannot be empty!";
    public static final String EVENT_SEPERATOR_ERROR_MESSAGE = "Incorrect format! Event must contain a task and its time separated by '/at'! Refer to 'help' for more details!";
    public static final String MISSING_EVENT_TIME_ERROR_MESSAGE = "Incorrect format! The time of an event cannot be empty!";
    public static final String MISSING_TODO_DESCRIPTION_ERROR_MESSAGE = "Incorrect format! The description of a todo cannot be empty!";
    public static final String MISSING_TASK_NUMBER_ERROR_MESSAGE = "Incorrect format! The task number that is to be marked as done/not done cannot be empty!";

    public static final String HELP_MESSAGE
            = "\tlist of valid commands" +
            "\n\t----------------------" +
            "\n\n\ttodo " +
            "\n\t----" +
            "\n\trecord a generic task without a deadline; format: 'todo' [name of task]" +
            "\n\n\tdeadline " +
            "\n\t--------" +
            "\n\trecord a task with a specific deadline; format: 'deadline' [name of task] '/by' [deadline]" +
            "\n\n\tevent " +
            "\n\t-----" +
            "\n\trecord a task happening at a specific time; format: 'event' [name of event] '/at' [time]" +
            "\n\n\tmark " +
            "\n\t----" +
            "\n\tmark a specific task as done; format: 'mark' [number of task]" +
            "\n\n\tunmark" +
            "\n\t------" +
            "\n\tmark a specific task as not done; format: 'unmark' [number of task]" +
            "\n\n\tdelete" +
            "\n\t------" +
            "\n\tdelete a specific task from the list of tasks; format: 'delete' [number of task]" +
            "\n\n\tlist" +
            "\n\t----" +
            " \n\tlist out all tasks and their completion status" +
            "\n\n\tfind" +
            "\n\t----" +
            " \n\tshortlist tasks with a specific keyword; format 'find' [keyword]" +
            "\n\n\tbye" +
            "\n\t---" +
            " \n\texit the program";
    public static final String MISSING_FIND_KEYWORD_ERROR_MESSAGE = "Incorrect format! The search keyword for a find command cannot be empty!";
    public static final String INVALID_TASK_NUMBER_ERROR_MESSAGE = "Incorrect format! Please enter a valid integer number to mark a task as done or not done!";
    public static final String INVALID_TIME_FORMAT_ERROR_MESSAGE = "Incorrect format! The time entered is not a valid date or is not in the correct format (uuuu-MM-dd H:mm)!";

    Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public static void showErrorMessage(String error) {
        printLine();
        System.out.println("\t " + error);
        printLine();
    }

    public String acceptAndValidateInput() {
        boolean isInputValid = false;
        String input = "";
        String command = "";
        while (!isInputValid) {
            try {
                input = readLine();
                command = Parser.retrieveCommand(input);
                Parser.validateCommand(command);
                isInputValid = true;
            } catch (UnrecognizedCommandException e) {
                printLine();
                System.out.println("\t" + e.getMessage());
                printLine();
            }
        }
        return input;
    }

    public String readLine() {
        return scanner.nextLine().trim();

    }

    public void printHelpMessage() {
        System.out.println(HELP_MESSAGE);
    }

    public static void printLine() {
        System.out.print("\t");
        for (int i = 0; i < 60; i++) {
            System.out.print("\u2500");
        }
        System.out.println();
    }

    public void greet() {
        System.out.println("\nHello from\n" + Ui.DUKE_LOGO);
        printLine();
        System.out.println("\tHello! I'm Duke!");
        System.out.println("\tWhat can I do for you?");
        printLine();
    }

}
