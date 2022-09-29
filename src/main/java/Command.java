import java.util.ArrayList;

/**
 * Command Class.
 */
public class Command {

    /**
     * execute method prints out if command is unknown/invalid.
     */
    public void execute(ArrayList<String> types, ArrayList<String> marks, ArrayList<String> tasks, Ui ui, Storage storage) {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * program does not exit after this class is called.
     */
    boolean isExit() {
        return false;
    }
}
