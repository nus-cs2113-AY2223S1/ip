package Duke.commands;

import Duke.data.Memory;
import Duke.data.TaskManager;
import Duke.data.exception.DukeException;
import Duke.data.exception.ExceptionMessage;
import Duke.data.tasks.Deadline;
import Duke.data.tasks.Event;
import Duke.data.tasks.Todo;
import java.time.LocalDate;

/**
 * Class to process and execute all command calls in programme
 */
public class Command {
    public final String COMMAND_LIST = "list";
    public final String COMMAND_MARK = "mark";
    public final String COMMAND_UNMARK = "unmark";
    public final String COMMAND_EVENT = "event";
    public final String COMMAND_TODO = "todo";
    public final String COMMAND_DEADLINE = "deadline";
    public final String COMMAND_DELETE = "delete";
    public final String COMMAND_FIND = "find";
    public final String COMMAND_DATE_SEARCH = "date";
    private final String commandWord;
    private final String userInput;

    /**
     * Constructor for Command class
     * @param commandWord is the string containing user command
     * @param userInput is the string containing information related to the command
     */
    public Command (String commandWord, String userInput) {
        this.commandWord = commandWord;
        this.userInput = userInput;
    }

    /**
     * This method executes the command based on the command word given when constructing the class
     * @param myTaskManager is the object which stores all tasks
     * @param myStorage is the object which handles the saving and loading of data
     */
    public void execute (TaskManager myTaskManager, Memory myStorage) {
        switch(commandWord){
        case COMMAND_LIST:
            try {
                myTaskManager.printList();
            } catch(DukeException e) {
                System.out.println(ExceptionMessage.EMPTY_HANDLER);
            }
            break;
        case COMMAND_MARK:
            try {
                myTaskManager.markDone(Integer.parseInt(userInput) - 1);
                myTaskManager.printList();
                myStorage.saveToFile(myTaskManager);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println();
            } catch(IndexOutOfBoundsException e) {
                System.out.println(ExceptionMessage.INPUT_INDEX_OUT_OF_BOUNDS + this.userInput);
            } catch(DukeException e) {
                System.out.println(ExceptionMessage.EMPTY_HANDLER);
            } catch(NumberFormatException e) {
                System.out.println(ExceptionMessage.EMPTY_MARK_INDEX);
            }
            break;
        case COMMAND_UNMARK:
            try {
                myTaskManager.unmarkDone(Integer.parseInt(userInput) - 1);
                myTaskManager.printList();
                myStorage.saveToFile(myTaskManager);
                System.out.println("Nice! I've marked this task as undone:");
                System.out.println();
            } catch(IndexOutOfBoundsException e) {
                System.out.println(ExceptionMessage.INPUT_INDEX_OUT_OF_BOUNDS + this.userInput);
            } catch(DukeException e) {
                System.out.println(ExceptionMessage.EMPTY_HANDLER);
            } catch(NumberFormatException e) {
                System.out.println(ExceptionMessage.EMPTY_MARK_INDEX);
            }
            break;
        case COMMAND_DEADLINE:
            try {
                myTaskManager.addTask(new Deadline(userInput));
                System.out.println("\t Added: ");
                myTaskManager.printTask(myTaskManager.getSize() - 1);
                myTaskManager.printSize();
                myStorage.saveToFile(myTaskManager);
            } catch(ArrayIndexOutOfBoundsException e) {
                System.out.println(ExceptionMessage.DEADLINE_INPUT_ERROR);
            }
            break;
        case COMMAND_EVENT:
            try {
                myTaskManager.addTask(new Event(userInput));
                System.out.println("\t Added: ");
                myTaskManager.printTask(myTaskManager.getSize() - 1);
                myTaskManager.printSize();
                myStorage.saveToFile(myTaskManager);
            } catch(ArrayIndexOutOfBoundsException e) {
                System.out.println(ExceptionMessage.EVENT_INPUT_ERROR);
            }
            break;
        case COMMAND_TODO:
            try {
                myTaskManager.addTask(new Todo(userInput));
                System.out.println("\t Added: ");
                myTaskManager.printTask(myTaskManager.getSize() - 1);
                myTaskManager.printSize();
                myStorage.saveToFile(myTaskManager);
            } catch(DukeException e) {
                System.out.println(ExceptionMessage.TODO_INPUT_ERROR);
            }
            break;
        case COMMAND_DELETE:
            try {
                myTaskManager.deleteTask(Integer.parseInt(userInput) - 1);
                myTaskManager.printSize();
                myStorage.saveToFile(myTaskManager);
            } catch(IndexOutOfBoundsException e) {
                System.out.println(ExceptionMessage.INPUT_INDEX_OUT_OF_BOUNDS + userInput);
            } catch(DukeException e) {
                System.out.println(ExceptionMessage.EMPTY_HANDLER);
            } catch(NumberFormatException e) {
                System.out.println(ExceptionMessage.EMPTY_MARK_INDEX);
            }
            break;
        case COMMAND_DATE_SEARCH:
            try {
                LocalDate searchDate = LocalDate.parse(userInput);
                myTaskManager.searchForDate(searchDate);
            } catch (java.time.format.DateTimeParseException e) {
                System.out.println(ExceptionMessage.DATE_PARSE_ERROR);
            }
            break;
        case COMMAND_FIND:
            myTaskManager.searchForWord(userInput);
            break;
        default:
            System.out.println(ExceptionMessage.UNKNOWN_INPUTS);
            break;
        }
    }


}