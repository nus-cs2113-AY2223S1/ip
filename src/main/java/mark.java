import java.util.ArrayList;

/**
 * mark class extend from Command Class.
 */
public class mark extends Command {
    String fullCommand;

    /**
     * mark constructor.
     */
    mark(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * execute method prints out task that has been marked and updates the task list accordingly.
     */
    public void execute(ArrayList<String> type, ArrayList<String> marks, ArrayList<String> tasks, Ui ui, Storage storage) {
        System.out.println("Nice! I've marked this task as done:");
        String strNum = fullCommand.substring(5, fullCommand.length());
        Integer num = Integer.parseInt(strNum);
        marks.set(num - 1, "[X]"); // To Modify
        System.out.println(marks.get(num - 1) + " " + tasks.get(num - 1));
    }
    /**
     * program does not exit after this class is called.
     */
    boolean isExit() {
        return false;
    }
}
