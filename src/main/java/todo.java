import java.util.ArrayList;

public class todo extends Command {
    String fullCommand;
    todo(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    public void execute(ArrayList<String> type, ArrayList<String> marks, ArrayList<String> tasks, Ui ui, Storage storage) {
        if (fullCommand.equals("todo") || fullCommand.equals("todo ")) {
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
        } else {
            System.out.println("Got it. I've added this task");
            String editedCommand = fullCommand.substring(5, fullCommand.length());
            System.out.println("  [T][ ] " + editedCommand);
            System.out.println("Now you have " + storage.getCount() + " tasks in the list.");
            tasks.add(editedCommand);
            type.add("[T]");
            marks.add("[ ]");
            Integer count = storage.getCount();
            storage.updateCount(count+1);
        }
    }
    boolean isExit() {
        return false;
    }
}
