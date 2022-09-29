import java.util.ArrayList;

/**
 * list class extend from Command Class.
 */
public class list extends Command {
    String fullCommand;
    /**
     * constructor for list.
     */
    list(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * execute method prints out all the tasks.
     */
    public void execute(ArrayList<String> type, ArrayList<String> marks, ArrayList<String> tasks, Ui ui, Storage storage) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i < storage.getCount(); i++) {
            System.out.println(i + ". " + type.get(i - 1) + marks.get(i - 1) + " " + tasks.get(i - 1));
        }
    }

    /**
     * program does not exit after this class is called.
     */
    boolean isExit() {
        return false;
    }

}
