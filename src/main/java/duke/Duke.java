package duke;

import duke.error.ErrorHandler;
import duke.error.exceptions.*;
import duke.input.InputValidator;
import duke.ui.UserInterface;
import duke.tasks.tasktypes.DeadlineTask;
import duke.tasks.tasktypes.EventTask;
import duke.tasks.tasktypes.ToDoTask;
import duke.tasks.TaskList;

public class Duke {
    /* Command list to check against */
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_TERMINATE = "bye";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_MARK = "mark";
    public static final String COMMAND_UNMARK = "unmark";

    /** TaskList class that contains all items */
    public static final TaskList TASK_LIST = new TaskList();

    public static void main(String[] args) {
        greet();
        programBody();
        exit();
    }

    /**
     * Body of the program. Runs in a loop until terminated.
     */
    public static void programBody() {
        while (true) {
            String input = UserInterface.getInput();
            try {
                if (InputValidator.isTerminatingInput(input)) {
                    break;
                } else if (InputValidator.isListInput(input)) {
                    printList();
                } else if (InputValidator.isMarkInput(input)) {
                    markAndConfirm(input);
                } else if (InputValidator.isUnmarkInput(input)) {
                    unmarkAndConfirm(input);
                } else if (InputValidator.isAddInput(input)) {
                    addAndConfirm(input);
                } else {
                    throw new NotRecognizedException(input);
                }
            } catch (CustomException e) {
                ErrorHandler.printErrorMessage(e);
            }
        }
    }

    private static void markAndConfirm(String input) throws
            NotANumberException, ItemNotFoundException, NoStateChangeException {
        UserInterface.print("Marked task \""
                + TASK_LIST.getTextOfItem(markItem(input) - 1)
                + "\" as done.");
    }

    private static void unmarkAndConfirm(String input) throws
            NotANumberException, ItemNotFoundException, NoStateChangeException {
        UserInterface.print("Marked task \""
                + TASK_LIST.getTextOfItem(unmarkItem(input) - 1)
                + "\" as not yet done.");
    }

    /**
     * Adds item to {@link Duke#TASK_LIST} and prints confirmation.
     *
     * @param input input string
     */
    private static void addAndConfirm(String input) {
        UserInterface.print("Added \""
                + TASK_LIST.getTextOfItem(addItem(input))
                + "\" to your list.\nThere " + (TASK_LIST.getItemCount() == 1 ? "is" : "are")
                + " now " + TASK_LIST.getItemCount() + " task"
                + (TASK_LIST.getItemCount() == 1 ? "" : "s") + ".");
    }

    /**
     * Print all tasks stored in {@link Duke#TASK_LIST}
     *
     * @throws ListEmptyException Exception thrown if list is empty
     */
    private static void printList() throws ListEmptyException {
        if (TASK_LIST.getItemCount() <= 0) {
            throw new ListEmptyException();
        }
        UserInterface.print(TASK_LIST.toString());
    }

    /**
     * Add type of item, depending on command. Defaults to {@link ToDoTask} item type.
     *
     * @param input input string to check for command words
     * @return <b>0-based</b> index of added item.
     */
    private static int addItem(String input) {
        String task = input.split(" ", 2)[1];
        if (InputValidator.stringContains(input, COMMAND_DEADLINE)) {
            return TASK_LIST.addItem(new DeadlineTask(task));
        } else if (InputValidator.stringContains(input, COMMAND_EVENT)) {
            return TASK_LIST.addItem(new EventTask(task));
        } else {
            return TASK_LIST.addItem(new ToDoTask(task));
        }
    }

    /**
     * Marks item in To-Do list as done.
     * <br><b>NOTE: Operates on 1-based indexing logic</b>, but converts it
     * to 0-based indexing for {@link TaskList} class
     *
     * @param input input string to find index
     * @return index of item <b>(1-based index)</b>
     */
    private static int markItem(String input) throws NotANumberException, ItemNotFoundException,
            NoStateChangeException {
        int itemIndex = extractNumber(input) - 1;
        TASK_LIST.markItem(itemIndex);
        return itemIndex + 1;
    }

    /**
     * Removes mark on item in To-Do list, marking it as undone.
     * <br><b>NOTE: Operates on 1-based indexing logic</b>, but converts it
     * to 0-based indexing for {@link TaskList} class
     *
     * @param input input string to find index
     * @return index of item <b>(1-based index)</b>
     */
    private static int unmarkItem(String input) throws NotANumberException, ItemNotFoundException,
            NoStateChangeException {
        int itemIndex = extractNumber(input) - 1;
        TASK_LIST.unmarkItem(itemIndex);
        return itemIndex + 1;
    }

    private static int extractNumber(String input) throws NotANumberException {
        String number = input.split(" ")[1];
        String command = input.split(" ")[0];
        if (InputValidator.isNumber(number)) {
            return Integer.parseInt(number);
        } else {
            throw new NotANumberException(command);
        }
    }

    /**
     * Prints a greeting for when the program is run
     */
    private static void greet() {
        UserInterface.print("Good morning!\nWhat would you like to do today?");
    }

    /**
     * Prints a goodbye message for when the program is terminated via user commands
     */
    private static void exit() {
        UserInterface.print("Alright, see you around then!");
    }
}
