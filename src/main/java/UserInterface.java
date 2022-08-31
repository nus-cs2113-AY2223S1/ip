import java.util.Scanner;

public class UserInterface {
    public String EXIT = " Bye! Hope to see you again soon :)";

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
    private String inputs;
    private String[] splitInputs;

    public UserInterface() {
        scanner = new Scanner(System.in);
        myTaskManager = new TaskManager();
    }

    public void runProgram() {
        inputs = scanner.nextLine();
        while(!(inputs.equalsIgnoreCase(COMMAND_BYE))) {
            System.out.print(LINEBREAK);
            splitInputs = inputs.split(" ");
            if(splitInputs[0].equalsIgnoreCase(COMMAND_LIST)) {
                if(myTaskManager.getSize() == 0) {
                    System.out.println("The list is empty and there is nothing to print :(");
                }
                else {
                    myTaskManager.printList();
                }
            } else if(splitInputs[0].equalsIgnoreCase(COMMAND_MARK)){
                if(splitInputs.length == 1) {
                    System.out.println("No index given to mark");
                } else if(Integer.parseInt(splitInputs[1]) > myTaskManager.getSize()) {
                    System.out.println("There is no task with index " + splitInputs[1]);
                }
                else {
                    myTaskManager.markDone(Integer.parseInt(splitInputs[1]) - 1);
                    myTaskManager.printList();
                }
            } else if(splitInputs[0].equalsIgnoreCase(COMMAND_UNMARK)){
                if(splitInputs.length == 1) {
                    System.out.println("No index given to unmark");
                } else if(Integer.parseInt(splitInputs[1]) > myTaskManager.getSize()) {
                    System.out.println("There is no task with index " + splitInputs[1]);
                }
                else {
                    myTaskManager.unmarkDone(Integer.parseInt(splitInputs[1]) - 1);
                    myTaskManager.printList();
                }
            } else if(splitInputs[0].equalsIgnoreCase(COMMAND_TODO)) {
                myTaskManager.addTasks(new Todo(splitInputs));
                System.out.println("\t Added: ");
                myTaskManager.printTask(myTaskManager.getSize() - 1);
                myTaskManager.printSize();
            } else if(splitInputs[0].equalsIgnoreCase(COMMAND_EVENT)) {
                myTaskManager.addTasks(new Event(splitInputs));
                System.out.println("\t Added: ");
                myTaskManager.printTask(myTaskManager.getSize() - 1);
                myTaskManager.printSize();
            } else if(splitInputs[0].equalsIgnoreCase(COMMAND_DEADLINE)) {
                myTaskManager.addTasks(new Deadline(splitInputs));
                System.out.println("\t Added: ");
                myTaskManager.printTask(myTaskManager.getSize() - 1);
                myTaskManager.printSize();
            }
            else {
                System.out.println("Sorry I do not understand your command :(");
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
