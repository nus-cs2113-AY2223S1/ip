import java.util.ArrayList;

/**
 * delete class extend from command class.
 */
public class delete extends Command {
    String fullCommand;
    /**
     * constructor for delete.
     */
    delete(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * execute method prints deleted task and removes it from task list.
     */
    public void execute(ArrayList<String> type, ArrayList<String> marks, ArrayList<String> tasks, Ui ui, Storage storage) {
        System.out.println("Noted. I've removed this task:");

        // Modify text according to deadline
        String strNum = fullCommand.substring(7, fullCommand.length());
        Integer num = Integer.valueOf(strNum);

        System.out.println("  " + type.get(num - 1) + marks.get(num - 1) + " " + tasks.get(num - 1));

        tasks.remove(num - 1);
        type.remove(num - 1);
        marks.remove(num - 1);
        Integer count = storage.getCount();
        storage.updateCount(count-1);

        Integer actCount = storage.getCount() - 1;
        System.out.println("Now you have " + actCount + " tasks in the list.");
    }
    /**
     * program does not exit after this class is called.
     */
    boolean isExit() {
        return false;
    }
}
