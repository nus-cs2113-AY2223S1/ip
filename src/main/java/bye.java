import java.util.ArrayList;

public class bye extends Command {
    String fullCommand;
    bye(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    public void execute(ArrayList<String> type, ArrayList<String> marks, ArrayList<String> tasks, Ui ui, Storage storage) {
        System.out.println("Bye. Hope to see you again soon!");
    }
    boolean isExit() {
        return true;
    }

}
