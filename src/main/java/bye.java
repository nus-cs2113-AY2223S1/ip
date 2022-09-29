import java.util.ArrayList;

/**
 * Bye Class extend from Command Class.
 */
public class bye extends Command {
    String fullCommand;


    /**
     * constructor for bye command.
     */
    bye(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * execute method prints bye message to user.
     */
    public void execute(ArrayList<String> type, ArrayList<String> marks, ArrayList<String> tasks, Ui ui, Storage storage) {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * program exit after this class is called.
     */
    boolean isExit() {
        return true;
    }

}
