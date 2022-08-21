import java.util.Arrays;

public class Storage {
    private static final Task[] list = new Task[120];
    private static int size = 0;
//    private static final boolean[] isDone = new boolean[120];

//    public Storage(){
//    }

    public static void add(Task item) {
        list[size] = item;
        size++;
        System.out.format("added: %s%n", item);
    }

    public static void listAll() {
        for(int i = 0; i < size; i++) {
            System.out.format("%d.[%s] %s%n", i+1, list[i].getStatusIcon(), list[i]);
        }
    }

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
    public void execute(String cmd) {
        if(cmd.toLowerCase().equals("list")) {
            listAll();
        } else if(cmd.toLowerCase().indexOf("mark") == 0 || cmd.toLowerCase().indexOf("unmark") == 0) {
            toggle(cmd);
        } else {
            add(new Task(cmd));
        }
    }
}
