package Duke;
import java.util.Scanner;

public class UserInterface {
    public String EXIT = " Bye! Hope to see you again soon :)\n";

    public String LOGO = "Hello from\n ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
    public String GREETING = " Hello! I'm Duke\n What can I do for you?\n";
    public String LINEBREAK = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
    public String COMMAND_LIST = "list";
    public String COMMAND_MARK = "mark";
    public String COMMAND_UNMARK = "unmark";
    public String COMMAND_EVENT = "event";
    public String COMMAND_TODO = "todo";
    public String COMMAND_DEADLINE = "deadline";
    public String COMMAND_BYE = "bye";

    private TaskManager myTaskManager;
    private Scanner scanner;
    private String[] splitInputs;

    public UserInterface() {
        scanner = new Scanner(System.in);
        myTaskManager = new TaskManager();
    }

    public void runProgram() {
        String inputs;
        inputs = scanner.nextLine();
        while(!(inputs.equalsIgnoreCase(COMMAND_BYE))) {
            System.out.print(LINEBREAK);
            splitInputs = inputs.split(" ");
            if(splitInputs[0].equalsIgnoreCase(COMMAND_LIST)) {
                try {
                    myTaskManager.printList();
                } catch(DukeException e) {
                    System.out.println(ExceptionHandler.EMPTY_HANDLER);
                }
            } else if(splitInputs[0].equalsIgnoreCase(COMMAND_MARK)){
                try {
                    myTaskManager.markDone(Integer.parseInt(splitInputs[1]) - 1);
                    myTaskManager.printList();
                } catch(ArrayIndexOutOfBoundsException e) {
                    if(splitInputs.length == 1) {
                        System.out.println(ExceptionHandler.EMPTY_MARK_INDEX);
                    } else {
                        System.out.println(ExceptionHandler.MARK_INDEX_OUT_OF_BOUNDS + splitInputs[1]);
                    }
                } catch(DukeException e) {
                    System.out.println(ExceptionHandler.EMPTY_HANDLER);
                }
            } else if(splitInputs[0].equalsIgnoreCase(COMMAND_UNMARK)){
                try {
                    myTaskManager.unmarkDone(Integer.parseInt(splitInputs[1]) - 1);
                    myTaskManager.printList();
                } catch(ArrayIndexOutOfBoundsException e) {
                    if(splitInputs.length == 1) {
                        System.out.println(ExceptionHandler.EMPTY_MARK_INDEX);
                    } else {
                        System.out.println(ExceptionHandler.MARK_INDEX_OUT_OF_BOUNDS + splitInputs[1]);
                    }
                } catch(DukeException e) {
                    System.out.println(ExceptionHandler.EMPTY_HANDLER);
                }
            } else if(splitInputs[0].equalsIgnoreCase(COMMAND_TODO)) {
                try {
                    myTaskManager.addTasks(new Todo(splitInputs));
                    System.out.println("\t Added: ");
                    myTaskManager.printTask(myTaskManager.getSize() - 1);
                    myTaskManager.printSize();
                } catch(ArrayIndexOutOfBoundsException e) {
                    System.out.println(ExceptionHandler.TODO_INPUT_ERROR);
                }
            } else if(splitInputs[0].equalsIgnoreCase(COMMAND_EVENT)) {
                try {
                    myTaskManager.addTasks(new Event(splitInputs));
                    System.out.println("\t Added: ");
                    myTaskManager.printTask(myTaskManager.getSize() - 1);
                    myTaskManager.printSize();
                } catch(DukeException e) {
                    System.out.println(ExceptionHandler.EVENT_INPUT_ERROR);
                }
            } else if(splitInputs[0].equalsIgnoreCase(COMMAND_DEADLINE)) {
                try {
                    myTaskManager.addTasks(new Deadline(splitInputs));
                    System.out.println("\t Added: ");
                    myTaskManager.printTask(myTaskManager.getSize() - 1);
                    myTaskManager.printSize();
                } catch(DukeException e) {
                    System.out.println(ExceptionHandler.DEADLINE_INPUT_ERROR);
                }
            }
            else {
                System.out.println(ExceptionHandler.UNKNOWN_INPUTS);
            }
            System.out.println(LINEBREAK);
            inputs = scanner.nextLine();
        }
    }

    public void giveGreeting() {
        System.out.println(LOGO + GREETING + LINEBREAK);
    }

    public void giveFarewell() {
        System.out.println(EXIT + LINEBREAK);
    }
}
