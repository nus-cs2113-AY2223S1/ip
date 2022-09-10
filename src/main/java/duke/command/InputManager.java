package duke.command;

import java.util.Scanner;
import duke.Duke;
import duke.exception.DukeException;
import duke.task.TaskManager;

public abstract class InputManager {
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
    }

    public static void handleInput(String input) throws DukeException {
        // handle list, mark, unmark, add
        if (input.equals(LIST_PHRASE)) {
            TaskManager.printList();
        } else if (input.startsWith(MARK_PHRASE)) {
            TaskManager.markTask(input);
        } else if (input.startsWith(UNMARK_PHRASE)) {
            TaskManager.unmarkTask(input);
        } else if (input.startsWith(TODO_PHRASE)) {
            TaskManager.addTask(TODO_PHRASE, input);
        } else if (input.startsWith(DEADLINE_PHRASE)) {
            TaskManager.addTask(DEADLINE_PHRASE, input);
        } else if (input.startsWith(EVENT_PHRASE)) {
            TaskManager.addTask(EVENT_PHRASE, input);
        } else if (input.startsWith(DELETE_PHRASE)) {
            TaskManager.deleteTask(input);
        } else {
            throw new DukeException();
        }
    }
}
