import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Storage class serve as a storage of all tasks and execute command
 * on the list of tasks
 */

public class Storage implements FormatChecker, FileIO {
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
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_NULL = "";
    private static ArrayList<Task> tasks = new ArrayList<>();

    public void loadSave() {
        try {
            tasks = FileIO.loadFile();
        } catch (CorruptedDataFileException e) {
            System.out.println("The saved file is corrupted.");
        } catch (FileNotFoundException e) {
            System.out.println("The saved file is not found");
        }
        System.out.println("Saved file is successfully loaded!");
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    public void writeSave() {
        try {
            FileIO.writeFile(tasks);
        } catch (IOException e) {
            System.out.format("IO exception occurs:%n%s", e.getMessage());
        }
    }

    /**
     * Add item to list of tasks
     *
     * @param item tasks in the list
     */
    public static void add(Task item) {
        tasks.add(item);
        System.out.format("added: %s%n%d items on list%n", item, tasks.size());
    }

    /**
     * List all items in storage
     */
    public static void listAll() {
        int i = 1;
        for (Task task : tasks) {
            System.out.format("%d.%s%n", i++, task);
        }
    }

    /**
     * toggleMarkStatus changes the check mark of each item in storage
     *
     * @param cmd COMMAND_MARK or COMMAND_UNMARK
     */
    public static void toggleMarkStatus(String cmd) {
        String[] cmds = cmd.split(COMMAND_SEPARATOR);

        try {
            String command = cmds[0];
            int index = Integer.parseInt(cmds[1]) - 1;
            FormatChecker.checkExcessArgument(cmd);
            if (command.equalsIgnoreCase(COMMAND_MARK)) {
                tasks.get(index).setIsDone(true);
            } else if (command.equalsIgnoreCase(COMMAND_UNMARK)) {
                tasks.get(index).setIsDone(false);
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException | ExcessArgumentException e) {
            System.out.format("Exception: Wrong command Format%n" +
                    "Try the command in correct format: mark <index of task>%n");
        } catch (IndexOutOfBoundsException e) {
            System.out.format("Exception: Unable to mark task %n" +
                    "There are only %d tasks now~%n", tasks.size());
        }

        listAll();
    }

    public static void deleteTask(String cmd) {
        String[] cmds = cmd.split(COMMAND_SEPARATOR);

        try {
            String indexString = cmds[1];
            int index = Integer.parseInt(indexString) - 1;
            FormatChecker.checkExcessArgument(cmd);
            System.out.format("The following task is deleted:%n%s%n", tasks.get(index));
            tasks.remove(index);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException | ExcessArgumentException e) {
            System.out.format("Exception: Wrong command Format%n" +
                    "Try the command in correct format: delete <index of task>%n");
        } catch (IndexOutOfBoundsException e) {
            System.out.format("Exception: Unable to delete task %n" +
                    "There are only %d tasks now~%n", tasks.size());
        }

    }

    /**
     * Execute choose to modify storage depending on command
     *
     * @param cmd input command: COMMAND_*
     */
    public void execute(String cmd) throws UnknownCommandException, NullCommandException {
        String[] words = cmd.split(COMMAND_SEPARATOR);
        switch (words[0]) {
        case COMMAND_LIST:
            listAll();
            break;
        case COMMAND_MARK:
        case COMMAND_UNMARK:
            toggleMarkStatus(cmd);
            break;
        case COMMAND_TODO:
            try {
                if (cmd.length() == TODO_LENGTH - 1) {
                    throw new WrongCommandFormatException();
                }
                add(new Todo(cmd.substring(TODO_LENGTH)));
            } catch (WrongCommandFormatException e) {
                System.out.format("Exception: Wrong Command Format%n" +
                        "Try the correct command format: " +
                        "todo <description>%n");
            }
            break;
        case COMMAND_DEADLINE:
            try {
                if (cmd.length() == DEADLINE_LENGTH - 1) {
                    throw new WrongCommandFormatException();
                }
                add(new Deadline(cmd.substring(DEADLINE_LENGTH)));
            } catch (WrongCommandFormatException e) {
                System.out.format("Exception: Wrong Command Format%n" +
                        "Try the correct command format: " +
                        "deadline <description> /by: <time>%n");
            }
            break;
        case COMMAND_EVENT:
            try {
                if (cmd.length() == EVENT_LENGTH - 1) {
                    throw new WrongCommandFormatException();
                }
                add(new Event(cmd.substring(EVENT_LENGTH)));
            } catch (WrongCommandFormatException e) {
                System.out.format("Exception: Wrong Command Format%n" +
                        "Try the correct command format: " +
                        "event <description> /at: <time>%n");
            }
            break;
        case COMMAND_DELETE:
            deleteTask(cmd);
            break;
        case COMMAND_NULL:
            throw new NullCommandException();
        default:
            throw new UnknownCommandException();
        }

        writeSave();
    }

}
