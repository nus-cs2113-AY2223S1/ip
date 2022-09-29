import java.util.ArrayList;


public class unmark extends Command {
    String fullCommand;

    /**
     * constructor for unmark.
     */
    unmark(String fullCommand) {
        this.fullCommand = fullCommand;
    }
    /**
     * execute method prints out task that has been marked and updates the task list accordingly.
     */
    public void execute(ArrayList<String> type, ArrayList<String> marks, ArrayList<String> tasks, Ui ui, Storage storage) {
        System.out.println("OK, I've marked this task as not done yet:");
        String strNum = fullCommand.substring(7, fullCommand.length());
        Integer num = Integer.parseInt(strNum);
        marks.set(num - 1, "[ ]");  // To Modify
        System.out.println(marks.get(num - 1) + " " + tasks.get(num - 1));
    }
    /**
     * program does not exit after this class is called.
     */
    boolean isExit() {
        return false;
    }
}
