package duke.command;

import java.util.Scanner;
import duke.Duke;
import duke.exception.DukeException;
import duke.task.TaskList;

public abstract class Ui {
    public static final String EXIT_PHRASE = "bye";
    public static final String LIST_PHRASE = "list";
    public static final String MARK_PHRASE = "mark";
    public static final String UNMARK_PHRASE = "unmark";
    public static final String TODO_PHRASE = "todo";
    public static final String DEADLINE_PHRASE = "deadline";
    public static final String BY_PHRASE = "/by";
    public static final String EVENT_PHRASE = "event";
    public static final String AT_PHRASE = "/at";
    public static final String DELETE_PHRASE = "delete";

    /**
     * Reads the user input. Keeps reading input until the exit phrase is given.
     */
    public static void readInputs() {
        Scanner in = new Scanner(System.in);
        String input;

        while (true) {
            input = in.nextLine();

            // handle exit
            if (input.equals(EXIT_PHRASE)) {
                break;
            }

            System.out.println(Duke.HORIZONTAL_LINE);

            try {
                handleInput(input);
            } catch (DukeException e) {
                System.out.println("Bad Input :(");
            }

            System.out.println(Duke.HORIZONTAL_LINE + System.lineSeparator());
        }

        in.close();
    }

    /**
     * Handles the user input
     * 
     * @param input the current line of user input
     * @throws DukeException if the user input is invalid and cannot be handled
     */
    public static void handleInput(String input) throws DukeException {
        // handle list, mark, unmark, add
        if (input.equals(LIST_PHRASE)) {
            TaskList.printList();
        } else if (input.startsWith(MARK_PHRASE)) {
            TaskList.markTask(input);
        } else if (input.startsWith(UNMARK_PHRASE)) {
            TaskList.unmarkTask(input);
        } else if (input.startsWith(TODO_PHRASE)) {
            TaskList.addTask(TODO_PHRASE, input);
        } else if (input.startsWith(DEADLINE_PHRASE)) {
            TaskList.addTask(DEADLINE_PHRASE, input);
        } else if (input.startsWith(EVENT_PHRASE)) {
            TaskList.addTask(EVENT_PHRASE, input);
        } else if (input.startsWith(DELETE_PHRASE)) {
            TaskList.deleteTask(input);
        } else {
            throw new DukeException();
        }
    }
}
