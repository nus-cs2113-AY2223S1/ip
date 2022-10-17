package duke.parser;

import duke.DukeException;
import duke.tasks.TaskList;
import duke.ui.Ui;

import java.util.Arrays;


/**
 * Interpret user's inputs to command Duke to perform its actions.
 *
 */
public class Parser {
    public static final String BY = "/by";
    public static final String TODO = "todo";
    public static final String EVENT = "event";
    public static final String LIST = "list";
    public static final String BYE = "bye";
    public static final String DEADLINE = "deadline";
    public static final String AT = "/at";
    public static final String DELETE = "delete";
    public static final String MARK_UNDONE = "unmark";
    public static final String MARK_DONE = "mark";
    public static final String FIND = "find";

    public static final String INVALID_TODO_COMMAND = "Invalid todo command... Please try again";
    public static final String INVALID_DEADLINE_COMMAND = "Invalid deadline command... Please try again";
    public static final String INVALID_EVENT_COMMAND = "Invalid event command... Please try again";
    public static final String INVALID_FIND_COMMAND = "Invalid find command... Please try again";
    public static final String INVALID_MARK_COMMAND = "Invalid mark command... Please try again";
    public static final String INVALID_UNMARK_COMMAND = "Invalid unmark command... Please try again";
    public static final String INVALID_DELETE_COMMAND = "Invalid delete command... Please try again";
    public static final String INVALID_DESCRIPTION_IN_COMMAND = "Please provide valid description for the task!";
    public static final String INVALID_INDEX_INPUT = "Index needs to be an integer and please remove trailing spaces!";
    public static final String INDEX_OUT_OF_BOUNDS = "Index out of bounds!";
    public static final String TWO_OR_MORE_CONSECUTIVE_WHITE_SPACES = "Invalid description name! There are " +
            "two or more consecutive white spaces";
    final String[] invalidDescriptions = { "", " ", "[]\\[;]" };

    public static Ui ui = new Ui ();

    public static TaskList tasks = new TaskList();


    /**
     * Checks if the user input contains the keywords for commands.
     * Invoke correspoding functions based on the keywords.
     *
     * @param input user input containing the command keyword.
     *
     */
    public void parse(String input) throws DukeException {
        String command = input.split(" ")[0];
        String arguments = input.substring(command.length()).trim();
        switch (command) {
        case LIST:
            tasks.listTasks(TaskList.taskList);
            break;
        case MARK_DONE:
            prepMarkDone(input);
            break;
        case MARK_UNDONE:
            prepMarkUnDone(input);
            break;
        case TODO:
            prepToDo(input);
            break;
        case DEADLINE:
            prepDeadline(input);
            break;
        case EVENT:
            prepEvent(input);
            break;
        case DELETE:
            prepDelete(input);
            break;
        case FIND:
            prepFind(input);
            break;
        case BYE:
            ui.printBye();
            break;
        default:
            ui.printOutputs("Oops... cannot recognize the input command!");
        }

    }

    /**
     * Extract the index of the task from the user's input.
     * Checks if the index is within the bounds.
     * Parse the index into another function to mark the task at the given index as not done.
     *
     * @param input user input containing the index of task.
     * @throws DukeException if the index is out of bounds.
     *
     *
     */

    public void prepMarkDone(String input) throws DukeException {
        try {
            int taskNumber = Integer.parseInt(input.substring(input.indexOf(MARK_DONE) + MARK_DONE.length() + 1));
            tasks.markDone(TaskList.taskList, taskNumber);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException(INVALID_MARK_COMMAND);
        } catch (IndexOutOfBoundsException e){
            throw new DukeException(INDEX_OUT_OF_BOUNDS);
        } catch (NumberFormatException e){
            throw new DukeException(INVALID_INDEX_INPUT);
        }
    }

    /**
     * Extract the index of the task from the user's input.
     * Checks if the index is within the bounds.
     * Parse the index into another function to mark the task at the given index as not done.
     *
     * @param input user input containing the index of task.
     * @throws DukeException if the index is out of bounds.
     *
     *
     */


    public void prepMarkUnDone(String input) throws DukeException {
        try {
            int taskNumber = Integer.parseInt(input.substring(input.indexOf(MARK_UNDONE) + MARK_UNDONE.length() + 1));
            tasks.markUnDone(TaskList.taskList, taskNumber);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException(INVALID_UNMARK_COMMAND);
        } catch (IndexOutOfBoundsException e){
            System.out.println (INDEX_OUT_OF_BOUNDS);
        } catch (NumberFormatException e){
            throw new DukeException(INVALID_INDEX_INPUT);
        }
    }


    /**
     * Extract the task description from the user's input.
     * Checks if the description is empty.
     * Parse the description into another function to add the task to the tasks list.
     *
     * @param input user input containing the task description.
     * @throws DukeException if the description is empty.
     *
     *
     */

    public void prepToDo(String input) throws DukeException {
        try {
            String description = input.substring(TODO.length() + 1);
            if (description.contains("  ")){
                throw new DukeException(TWO_OR_MORE_CONSECUTIVE_WHITE_SPACES);
            }
            if (Arrays.asList(invalidDescriptions).contains(description)) {
                throw new DukeException(INVALID_DESCRIPTION_IN_COMMAND);
            }
            tasks.addTodo(TaskList.taskList, description);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException(INVALID_TODO_COMMAND);
        }

    }


    /**
     * Breaks down user's input into the name of the task (description) and its deadline.
     * Checks that the task description and deadline strings are not empty.
     * Parse the broken down arguments into another function to perform the addition of this deadline task to the
     * tasks list.
     *
     * @param input user input containing the name of the task and the deadline of the task.
     * @throws DukeException if the task name is empty or the deadline is empty
     *
     */

    public void prepDeadline(String input) throws DukeException {
        try{
            String description = input.substring(DEADLINE.length(), input.indexOf(BY));
            if (description.contains("  ")){
                throw new DukeException(TWO_OR_MORE_CONSECUTIVE_WHITE_SPACES);
            }
            if (Arrays.asList(invalidDescriptions).contains(description)) {
                throw new DukeException(INVALID_DESCRIPTION_IN_COMMAND);
            }
            String deadline = input.substring(input.indexOf(BY) + BY.length() + 1);
            tasks.addDeadline(TaskList.taskList, description, deadline);
        } catch (StringIndexOutOfBoundsException e){
            throw new DukeException(INVALID_DEADLINE_COMMAND);
        }
    }

    /**
     * Breaks down user's input into the name of the task (description) and the time of the event.
     * Checks that the task description and time strings are not empty.
     * Parse the broken down arguments into another function to perform the addition of this event task to the
     * tasks list.
     *
     * @param input user input containing the name of the task and the time of the event.
     * @throws DukeException if the task name is empty or the time is empty
     *
     */
    public void prepEvent(String input) throws DukeException {
        try{
            String description = input.substring(EVENT.length(), input.indexOf(AT));
            if (description.contains("  ")){
                throw new DukeException(TWO_OR_MORE_CONSECUTIVE_WHITE_SPACES);
            }
            if (Arrays.asList(invalidDescriptions).contains(description)) {
                throw new DukeException(INVALID_DESCRIPTION_IN_COMMAND);
            }
            String time = input.substring(input.indexOf(AT) + AT.length() + 1);
            tasks.addEvent(TaskList.taskList, description, time);
        } catch (StringIndexOutOfBoundsException e){
            throw new DukeException(INVALID_EVENT_COMMAND);
        }

    }

    /**
     * Extract the index of the task from the user's input.
     * Checks if the index is within the bounds.
     * Parse the index into another function to perform the removal of the task at the given index from the tasks list.
     *
     * @param input user input containing the index of task.
     * @throws DukeException if the index is out of bounds.
     *
     *
     */
    public void prepDelete(String input) throws DukeException {
        try {
            int taskNumber = Integer.parseInt(input.substring(input.indexOf(DELETE) + DELETE.length() + 1));
            tasks.deleteTask(TaskList.taskList, taskNumber);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException(INVALID_DELETE_COMMAND);
        } catch (IndexOutOfBoundsException e){
            throw new DukeException(INDEX_OUT_OF_BOUNDS);
        } catch (NumberFormatException e){
            throw new DukeException(INVALID_INDEX_INPUT);
        }
    }

    /**
     * Extract the keyword to be searched from the user's input.
     * Checks if the keyword is not empty.
     * Parse the keyword into another function to perform the search of all applicable tasks from the tasks list.
     *
     * @param input user input containing the keyword.
     * @throws DukeException if the keyword is empty.
     *
     *
     */
    public void prepFind(String input) throws DukeException {
        try {
            String keyword = input.substring(FIND.length() + 1);
            if (keyword.contains("  ")) {
                throw new DukeException(TWO_OR_MORE_CONSECUTIVE_WHITE_SPACES);
            }
            if (Arrays.asList(invalidDescriptions).contains(keyword)) {
                throw new DukeException(INVALID_DESCRIPTION_IN_COMMAND);
            }
            tasks.findTasks(TaskList.taskList, keyword);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException(INVALID_FIND_COMMAND);
        }

    }

}
