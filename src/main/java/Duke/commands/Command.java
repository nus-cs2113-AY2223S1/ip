package Duke.commands;

import Duke.data.Memory;
import Duke.data.TaskManager;
import Duke.data.exception.DukeException;
import Duke.data.exception.ExceptionMessage;
import Duke.data.tasks.Deadline;
import Duke.data.tasks.Event;
import Duke.data.tasks.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;


/**
 * Class to handle all command calls in programme
 * Takes in two params, command and arguments to carry out command calls
 * Contains boolean isExit, to control when to end the programme
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

    public Command (String commandWord, String userInput) {
        this.commandWord = commandWord;
        this.userInput = userInput;
    }

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
                myTaskManager.addTasks(new Deadline(userInput));
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
                myTaskManager.addTasks(new Event(userInput));
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
                myTaskManager.addTasks(new Todo(userInput));
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
                myTaskManager.deleteTasks(Integer.parseInt(userInput) - 1);
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