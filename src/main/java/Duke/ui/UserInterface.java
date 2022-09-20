package Duke.ui;
import Duke.data.TaskManager;
import Duke.data.exception.DukeException;
import Duke.data.exception.ExceptionMessage;
import Duke.data.tasks.Deadline;
import Duke.data.tasks.Event;
import Duke.data.tasks.Todo;
import java.util.Scanner;
public class UserInterface {
    public final String EXIT = " Bye! Hope to see you again soon :)\n";

    public final String LOGO = "Hello from\n ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
    public final String GREETING = " Hello! I'm Duke\n What can I do for you?\n";
    public final String LINEBREAK = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
    public final String COMMAND_LIST = "list";
    public final String COMMAND_MARK = "mark";
    public final String COMMAND_UNMARK = "unmark";
    public final String COMMAND_EVENT = "event";
    public final String COMMAND_TODO = "todo";
    public final String COMMAND_DEADLINE = "deadline";
    public final String COMMAND_DELETE = "delete";
    public final String COMMAND_BYE = "bye";

    private TaskManager myTaskManager;
    private Scanner scanner;
    private String[] splitInputs;

    public UserInterface() {
        scanner = new Scanner(System.in);
        myTaskManager = new TaskManager();
    }

    public void runProgram() {
        String input;
        String commmandWord;
        input = scanner.nextLine();
        while(!(input.equalsIgnoreCase(COMMAND_BYE))) {
            System.out.print(LINEBREAK);
            splitInputs = input.split(" ", 2);
            commmandWord = splitInputs[0].toLowerCase();
            switch(commmandWord){
            case COMMAND_LIST:
                try {
                    myTaskManager.printList();
                } catch(DukeException e) {
                    System.out.println(ExceptionMessage.EMPTY_HANDLER);
                }
                break;
            case COMMAND_MARK:
                try {
                    myTaskManager.markDone(Integer.parseInt(splitInputs[1]) - 1);
                    myTaskManager.printList();
                } catch(IndexOutOfBoundsException e) {
                    if(splitInputs.length == 1) {
                        System.out.println(ExceptionMessage.EMPTY_MARK_INDEX);
                    } else {
                        System.out.println(ExceptionMessage.MARK_INDEX_OUT_OF_BOUNDS + splitInputs[1]);
                    }
                } catch(DukeException e) {
                    System.out.println(ExceptionMessage.EMPTY_HANDLER);
                }
                break;
            case COMMAND_UNMARK:
                try {
                    myTaskManager.unmarkDone(Integer.parseInt(splitInputs[1]) - 1);
                    myTaskManager.printList();
                } catch(IndexOutOfBoundsException e) {
                    if(splitInputs.length == 1) {
                        System.out.println(ExceptionMessage.EMPTY_MARK_INDEX);
                    } else {
                        System.out.println(ExceptionMessage.MARK_INDEX_OUT_OF_BOUNDS + splitInputs[1]);
                    }
                } catch(DukeException e) {
                    System.out.println(ExceptionMessage.EMPTY_HANDLER);
                }
            case COMMAND_DEADLINE:
                try {
                    myTaskManager.addTasks(new Deadline(splitInputs));
                    System.out.println("\t Added: ");
                    myTaskManager.printTask(myTaskManager.getSize() - 1);
                    myTaskManager.printSize();
                } catch(DukeException e) {
                    System.out.println(ExceptionMessage.DEADLINE_INPUT_ERROR);
                }
                break;
            case COMMAND_EVENT:
                try {
                    myTaskManager.addTasks(new Event(splitInputs));
                    System.out.println("\t Added: ");
                    myTaskManager.printTask(myTaskManager.getSize() - 1);
                    myTaskManager.printSize();
                } catch(DukeException e) {
                    System.out.println(ExceptionMessage.EVENT_INPUT_ERROR);
                }
                break;
            case COMMAND_TODO:
                try {
                    myTaskManager.addTasks(new Todo(splitInputs));
                    System.out.println("\t Added: ");
                    myTaskManager.printTask(myTaskManager.getSize() - 1);
                    myTaskManager.printSize();
                } catch(IndexOutOfBoundsException e) {
                    System.out.println(ExceptionMessage.TODO_INPUT_ERROR);
                }
                break;
            case COMMAND_DELETE:
                try {
                    System.out.println("\t Deleted: ");
                    myTaskManager.printTask(Integer.parseInt(splitInputs[1]) - 1);
                    myTaskManager.deleteTasks(Integer.parseInt(splitInputs[1]) - 1);
                    myTaskManager.printSize();
                } catch(IndexOutOfBoundsException e) {
                    if(splitInputs.length == 1) {
                        System.out.println(ExceptionMessage.EMPTY_MARK_INDEX);
                    } else {
                        System.out.println(ExceptionMessage.MARK_INDEX_OUT_OF_BOUNDS + splitInputs[1]);
                    }
                } catch(DukeException e) {
                    System.out.println(ExceptionMessage.EMPTY_HANDLER);
                }
                break;
            default:
                System.out.println(ExceptionMessage.UNKNOWN_INPUTS);
                break;
            }
            System.out.println(LINEBREAK);
            input = scanner.nextLine();
        }
    }

    public void giveGreeting() {
        System.out.println(LOGO + GREETING + LINEBREAK);
    }

    public void giveFarewell() {
        System.out.println(EXIT + LINEBREAK);
    }
}
