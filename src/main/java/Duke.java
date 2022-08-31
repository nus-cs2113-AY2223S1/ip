import java.util.Scanner;

public class Duke {
    public static final String COMMAND_UNMARKED = "unmark";
    public static final String COMMAND_MARKED = "mark";
    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_LIST = "list";
    public static void main(String[] args) {
        TaskList list = new TaskList();
        Scanner in = new Scanner(System.in);
        list.start();
        while (in.hasNextLine()) {
            String input = in.nextLine();
            if (input.equals(COMMAND_BYE)) {
                list.end();
                break;
            } else if (input.equals(COMMAND_LIST)) {
                list.printList();
            } else if (input.contains(COMMAND_MARKED) || input.contains(COMMAND_UNMARKED)) {
                list.markStatus(input);
            } else {
                list.addTask(input);
            }
        }
    }
}
