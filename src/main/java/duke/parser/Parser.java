package duke.parser;

import duke.DukeException;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;


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

    public static Ui ui = new Ui ();

    public static TaskList tasks = new TaskList();


    /**
     * Checks if the user input contains the keywords for commands.
     * Invoke correspoding functions based on the keywords.
     *
     * @param input user input containing the command keyword.
     *
     */
    public void parse (String input){
        String command = input.split (" ", 2) [0];
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
            ui.printOutputs("Oops... cannot recognize the input command !");
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

    public void prepMarkDone (String input){
        try {
            int taskNumber = Integer.parseInt(input.substring(input.indexOf(MARK_DONE) + MARK_DONE.length() +1));
            tasks.markDone(TaskList.taskList, taskNumber);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println ("Invalid mark done command!");
        } catch (IndexOutOfBoundsException e){
            System.out.println ("Index out of bounds!");
        } catch (NumberFormatException e){
            System.out.println ("Invalid mark done command!");
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


    public void prepMarkUnDone (String input){
        try {
            int taskNumber = Integer.parseInt(input.substring(input.indexOf(MARK_UNDONE) + MARK_UNDONE.length() +1));
            tasks.markUnDone(TaskList.taskList, taskNumber);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println ("Invalid mark undone command!");
        } catch (IndexOutOfBoundsException e){
            System.out.println ("Index out of bounds!");
        } catch (NumberFormatException e){
            System.out.println ("Invalid mark undone command!");
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

    public void prepToDo(String input){
        try {
            String task = input.substring(TODO.length() + 1);
            tasks.addTodo(TaskList.taskList, task);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println ("Invalid todo input!");
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

    public void prepDeadline (String input){
        try{
            String task = input.substring(DEADLINE.length(), input.indexOf(BY));
            String deadline = input.substring(input.indexOf(BY) + BY.length() + 1);
            tasks.addDeadline(TaskList.taskList, task, deadline);
        } catch (StringIndexOutOfBoundsException e){
            System.out.println ("Invalid deadline input!");
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
    public void prepEvent (String input){
        try{
            String task = input.substring(EVENT.length(), input.indexOf(AT));
            String time = input.substring(input.indexOf(AT) + AT.length() + 1);
            tasks.addEvent(TaskList.taskList, task, time);
        } catch (StringIndexOutOfBoundsException e){
            System.out.println ("Invalid event input!");
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
    public void prepDelete (String input){
        try {
            int taskNumber = Integer.parseInt(input.substring(input.indexOf(DELETE) + DELETE.length() +1));
            tasks.deleteTask (TaskList.taskList, taskNumber);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println ("Invalid input for deletion!");
        } catch (IndexOutOfBoundsException e){
            System.out.println ("Index out of bounds!");
        } catch (NumberFormatException e){
            System.out.println ("Invalid input for deletion!");
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
    public void prepFind (String input){
        try {
            String keyword = input.substring(FIND.length() + 1);
            tasks.findTasks (TaskList.taskList, keyword);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println ("Invalid input for finding!");
        }

    }

}
