import java.util.ArrayList;

public class list extends Command {
    String fullCommand;
    list(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    public void execute(ArrayList<String> type, ArrayList<String> marks, ArrayList<String> tasks, Ui ui, Storage storage) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i < storage.getCount(); i++) {
            System.out.println(i + ". " + type.get(i - 1) + marks.get(i - 1) + " " + tasks.get(i - 1));
        }
    }
    boolean isExit() {
        return false;
    }

}
