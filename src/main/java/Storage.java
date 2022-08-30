import java.util.Arrays;

public class Storage {
    private static final Task[] list = new Task[120];
    private static int size = 0;

    /**
     * Add item to storage
     */
    public static void add(Task item) {
        list[size] = item;
        size++;
        System.out.format("added: %s%n", item);
    }

    /**
     * List all items in storage
     */
    public static void listAll() {
        for(int i = 0; i < size; i++) {
            System.out.format("%d.[%s] %s%n", i+1, list[i].getStatusIcon(), list[i]);
        }
    }

    /**
     * Toggle is used to change the check mark of each item in storage
     */
    public static void toggle(String cmd) {
        String[] cmds = cmd.split(" ");
        if(Integer.parseInt(cmds[1]) > size) {
            System.out.format("There are only %d tasks now~%n", size);
            return;
        }
        if(cmds[0].toLowerCase().equals("mark")) {
            list[Integer.parseInt(cmds[1])-1].setIsDone(true);
        } else if (cmds[0].toLowerCase().equals("unmark")){
            list[Integer.parseInt(cmds[1])-1].setIsDone(false);
        }
        listAll();
    }

    /**
     * Execute choose to modify storage depending on command
     */
    public void execute(String cmd) {
        String[] words = cmd.split(" ");
        if(cmd.toLowerCase().equals("list")) {
            listAll();
        } else if(words.length == 2 && words[0].equals("mark") || words[0].equals("unmark")) {
            toggle(cmd);
        } else {
            add(new Task(cmd));
        }
    }
}
