package Duke.ui;
import Duke.Parser;
import Duke.commands.Command;
import Duke.data.TaskManager;
import Duke.data.Memory;
import java.util.Scanner;

/**
 * Class to run commands at interface level and start the program
 */
public class UserInterface {
    public final String EXIT = " Bye! Hope to see you again soon :)\n";

    public final String LOGO = "Hello from\n ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
    public final String GREETING = " Hello! I'm Duke\n What can I do for you?\n";
    public final String LINEBREAK = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
    public final String COMMAND_BYE = "bye";
    private final TaskManager myTaskManager;
    private final Scanner scanner;
    private final Memory myStorage;

    /**
     * constructor for the UserInterface class
     * @param filePath is the path to the file where we will be loading and saving data from
     */
    public UserInterface(String filePath) {
        scanner = new Scanner(System.in);
        myTaskManager = new TaskManager();
        myStorage = new Memory(filePath);
    }

    /**
     * This method starts the program by loading data from the text file and repeatedly reading inputs
     * from user to process until user key in the keyword to exit program.
     */
    public void runProgram() {
        String input;
        myStorage.loadFromFile(myTaskManager);
        input = scanner.nextLine();
        while(!(input.equalsIgnoreCase(COMMAND_BYE))) {
            System.out.print(LINEBREAK);
            Command command = Parser.parse(input);
            command.execute(myTaskManager, myStorage);
            System.out.println(LINEBREAK);
            input = scanner.nextLine();
        }
    }

    /**
     * This method prints the welcome message
     */
    public void giveGreeting() {
        System.out.println(LOGO + GREETING + LINEBREAK);
    }

    /**
     * This method prints the exiting message
     */
    public void giveFarewell() {
        System.out.println(EXIT + LINEBREAK);
    }
}
