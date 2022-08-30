import java.util.Arrays;

public class Storage {
    private static final Task[] list = new Task[120];
    private static int size = 0;

    private static int TODO_LENGTH = 5;
    private static int DEADLINE_LENGTH = 9;
    private static int EVENT_LENGTH = 6;

    /**
     * Add item to storage
     */
    public static void add(Task item) {
        list[size] = item;
        size++;
        System.out.format("added: %s%n%d items on list%n", item, size);
    }

    /**
     * List all items in storage
     */
    public static void listAll() {
        for (int i = 0; i < size; i++) {
            System.out.format("%d.%s%n", i + 1, list[i]);
        }
    }

    /**
     * Toggle is used to change the check mark of each item in storage
     */
    public static void toggle(String cmd) {
        String[] cmds = cmd.split(" ");
        if (Integer.parseInt(cmds[1]) > size) {
            System.out.format("There are only %d tasks now~%n", size);
            return;
        }
        if (cmds[0].toLowerCase().equals("mark")) {
            list[Integer.parseInt(cmds[1]) - 1].setIsDone(true);
        } else if (cmds[0].toLowerCase().equals("unmark")) {
            list[Integer.parseInt(cmds[1]) - 1].setIsDone(false);
        }
        listAll();
    }

    /**
     * Execute choose to modify storage depending on command
     */
    public void execute(String cmd) {
        String[] words = cmd.split(" ");
        switch (words[0]) {
        case "list":
            listAll();
            break;
        case "mark":
            toggle(cmd);
            break;
        case "unmark":
            toggle(cmd);
            break;
        case "todo":
            add(new Todo(cmd.substring(TODO_LENGTH)));
            break;
        case "deadline":
            add(new Deadline(cmd.substring(DEADLINE_LENGTH)));
            break;
        case "event":
            add(new Event(cmd.substring(EVENT_LENGTH)));
            break;
        case "":
            System.out.println("Nothing happened QaQ");
            break;
        default:
            add(new Task(cmd));
        }
    }
}
