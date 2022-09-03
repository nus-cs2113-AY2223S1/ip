package duke;

import duke.error.ErrorHandler;
import duke.error.exceptions.CustomException;
import duke.error.exceptions.ListEmptyException;
import duke.error.exceptions.NotRecognizedException;
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
    private static final TaskList TASK_LIST = new TaskList();

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
                    UserInterface.print("Marked task \""
                            + TASK_LIST.getTextOfItem(markItem(input) - 1)
                            + "\" as done.");
                } else if (InputValidator.isUnmarkInput(input)) {
                    UserInterface.print("Marked task \""
                            + TASK_LIST.getTextOfItem(unmarkItem(input) - 1)
                            + "\" as not yet done.");
                } else if (InputValidator.isAddInput(input)) {
                    UserInterface.print("Added \""
                            + TASK_LIST.getTextOfItem(addItem(input))
                            + "\" to your list.\nThere " + (TASK_LIST.getItemCount() == 1 ? "is" : "are")
                            + " now " + TASK_LIST.getItemCount() + " task"
                            + (TASK_LIST.getItemCount() == 1 ? "" : "s") + ".");
                } else {
                    throw new NotRecognizedException(input);
                }
            } catch (CustomException e) {
                ErrorHandler.printErrorMessage(e);
            }
        }
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
        if (InputValidator.stringContains(input, COMMAND_DEADLINE)) {
            return TASK_LIST.addItem(new DeadlineTask(input.split(" ", 2)[1]));
        } else if (InputValidator.stringContains(input, COMMAND_EVENT)) {
            return TASK_LIST.addItem(new EventTask(input.split(" ", 2)[1]));
        } else {
            return TASK_LIST.addItem(new ToDoTask(input.split(" ", 2)[1]));
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
    private static int markItem(String input) {
        int itemIndex = Integer.parseInt(input.split(" ")[1]) - 1;
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
    private static int unmarkItem(String input) {
        int itemIndex = Integer.parseInt(input.split(" ")[1]) - 1;
        TASK_LIST.unmarkItem(itemIndex);
        return itemIndex + 1;
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
