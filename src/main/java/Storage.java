import java.util.Arrays;

/**
 * Storage class serve as a storage of all tasks and execute command
 * on the list of tasks
 */
public class Storage {
    private static final String IDLE_MESSAGE = "Nothing happened QaQ";
    private static final int TODO_LENGTH = 5;
    private static final int DEADLINE_LENGTH = 9;
    private static final int EVENT_LENGTH = 6;
    private static final String COMMAND_SEPARATOR = " ";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_NULL = "";
    private static final int MAXIMUM_LIST_SIZE = 120;
    private static final Task[] list = new Task[MAXIMUM_LIST_SIZE];
    private static int size = 0;

    /**
     * Add item to list of tasks
     *
     * @param item
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
     * toggleMarkStatus changes the check mark of each item in storage
     *
     * @param cmd COMMAND_MARK or COMMAND_UNMARK
     */
    public static void toggleMarkStatus(String cmd) {
        String[] cmds = cmd.split(COMMAND_SEPARATOR);
        if (Integer.parseInt(cmds[1]) > size) {
            System.out.format("There are only %d tasks now~%n", size);
            return;
        }
        if (cmds[0].toLowerCase().equals(COMMAND_MARK)) {
            list[Integer.parseInt(cmds[1]) - 1].setIsDone(true);
        } else if (cmds[0].toLowerCase().equals(COMMAND_UNMARK)) {
            list[Integer.parseInt(cmds[1]) - 1].setIsDone(false);
        }
        listAll();
    }

    /**
     * Execute choose to modify storage depending on command
     *
     * @param cmd input command: COMMAND_*
     */
    public void execute(String cmd) {
        String[] words = cmd.split(COMMAND_SEPARATOR);
        switch (words[0]) {
        case COMMAND_LIST:
            listAll();
            break;
        case COMMAND_MARK:
            toggleMarkStatus(cmd);
            break;
        case COMMAND_UNMARK:
            toggleMarkStatus(cmd);
            break;
        case COMMAND_TODO:
            add(new Todo(cmd.substring(TODO_LENGTH)));
            break;
        case COMMAND_DEADLINE:
            add(new Deadline(cmd.substring(DEADLINE_LENGTH)));
            break;
        case COMMAND_EVENT:
            add(new Event(cmd.substring(EVENT_LENGTH)));
            break;
        case COMMAND_NULL:
            System.out.println(IDLE_MESSAGE);
            break;
        default:
            add(new Task(cmd));
        }
    }

}
