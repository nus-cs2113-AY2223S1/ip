import java.util.ArrayList;

public class TaskManager {

    public ArrayList<Task> Tasks;
    public TaskManager() {
        this.Tasks = new ArrayList<Task>();
    }

    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String LINEBREAK = "____________________________________________________________";
    public void storeTasks(String text) {
        System.out.println(LINEBREAK);
        String[] result = text.split(" ");
        String[] result2 = text.split("/by ");
        String[] result3 = text.split("/at ");
        Todo newTodo = null;
        Deadline newDeadline = null;
        Event newEvent = null;
        if (result[0].equals("todo")) {
            newTodo = new Todo(result[1]);
            Tasks.add(newTodo);
        }
        else if (result[0].equals("deadline")) {
            newDeadline = new Deadline(result2[0].substring(9), result2[1]);
            Tasks.add(newDeadline);
        }
        else if (result[0].equals("event")) {
            newEvent = new Event(result3[0].substring(6), result3[1]);
            Tasks.add(newEvent);
        }
        System.out.println("I have added this task!");
        if (result[0].equals("todo")) {
            System.out.println(newTodo);
        }
        else if (result[0].equals("deadline")) {
            System.out.println(newDeadline);
        }
        else if (result[0].equals("event")) {
            System.out.println(newEvent);
        }
        System.out.println("Okay loser! You now have " + Tasks.size() + " in the list. Get to work!");
        System.out.println(LINEBREAK);
    }

    public void printTasks() {
        System.out.println(LINEBREAK);
        System.out.println("It just keeps piling up");
        int count = 1;
        for (Task i : Tasks) {
            System.out.println(count + "." + i);
            count++;
        }
        System.out.println(LINEBREAK);
    }

    public void markTasks(String text) {
        System.out.println(LINEBREAK);
        System.out.println("I've marked this task as done, now go do something else!:");
        String[] result = text.split(" ");
        Tasks.get(Integer.valueOf(result[1]) - 1).setDone();
        Tasks.get(Integer.valueOf(result[1]) - 1).setDone();
        System.out.println(Tasks.get(Integer.valueOf(result[1]) - 1));
        System.out.println(LINEBREAK);
    }

    public void unmarkTasks(String text) {
        System.out.println(LINEBREAK);
        System.out.println("I've marked this task as not done, get working!:");
        String[] result = text.split(" ");
        Tasks.get(Integer.valueOf(result[1]) - 1).setNotDone();
        System.out.println(Tasks.get(Integer.valueOf(result[1]) - 1));
        System.out.println(LINEBREAK);
    }
}


