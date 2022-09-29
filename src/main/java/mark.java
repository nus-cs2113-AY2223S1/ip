import java.util.ArrayList;

public class mark extends Command {
    String fullCommand;
    mark(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    public void execute(ArrayList<String> type, ArrayList<String> marks, ArrayList<String> tasks, Ui ui, Storage storage) {
        System.out.println("Nice! I've marked this task as done:");
        String strNum = fullCommand.substring(5, fullCommand.length());
        Integer num = Integer.parseInt(strNum);
        marks.set(num - 1, "[X]"); // To Modify
        System.out.println(marks.get(num - 1) + " " + tasks.get(num - 1));
    }
    boolean isExit() {
        return false;
    }
}
