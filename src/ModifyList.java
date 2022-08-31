import java.util.ArrayList;
import java.util.List;


public class ModifyList {
    private final List<Task> tasks = new ArrayList<Task>();


    public static String lineSeparator() {
        return "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
    }

    private void printTask(String task) {
        System.out.println(lineSeparator() +
                "Noted. Following task has been added: " + '\n' + task + "\n" +
                "Total tasks in list: " + tasks.size() + '\n' +
                lineSeparator());
    }

    public void todo(String details) {
        Todo task = new Todo(details);
        tasks.add(task);
        printTask(task.getDescriptionAndStatus());
    }

    public void deadline(String details) {
        String[] separateDetails = details.split("/");
        String description = separateDetails[0];
        String time = separateDetails[1];
        time = new StringBuilder(time).insert(time.indexOf(' '), ":").toString(); // add colon
        Deadline deadline = new Deadline(description, time);
        tasks.add(deadline);
        printTask(deadline.getDescriptionAndStatus());
    }

    public void event(String details) {
        String[] separateDetails = details.split("/");
        String description = separateDetails[0];
        String time = separateDetails[1];
        time = new StringBuilder(time).insert(time.indexOf(' '), ":").toString(); // add colon
        Event event = new Event(description, time);
        tasks.add(event);
        printTask(event.getDescriptionAndStatus());
    }

    public void list() {
        int itemNumber = 1;
        System.out.println(lineSeparator() + "Here are your tasks:\n");
        for (Task task : tasks) {
            System.out.println(itemNumber + "." + task.getDescriptionAndStatus() + "\n");
            itemNumber++;
        }
        System.out.println(lineSeparator());
    }
    public void mark(int index) {
        if (index > tasks.size() || tasks.size() == 0 || index < 0) {
            System.out.println("Sorry, there is no such task. Please input a correct number");
        } else {
            Task task = tasks.get(index - 1);
            task.setDone(true);
            tasks.set(index - 1, task);
            System.out.println(lineSeparator() +
                    "The following task been marked as completed:\n" +
                    tasks.get(index - 1).getDescriptionAndStatus() + "\n" +
                    lineSeparator());
        }
    }
    public void unmark(int index) {
        if (index > tasks.size() || tasks.size() == 0 || index < 0) {
            System.out.println("Sorry, there is no such task. Please input a correct number");
        } else {
            Task task = tasks.get(index - 1);
            task.setDone(false);
            tasks.set(index - 1, task);
            System.out.println(lineSeparator() +
                    "The following task been marked as not done yet:\n" +
                    tasks.get(index - 1).getDescriptionAndStatus() + "\n" +
                    lineSeparator());
        }
    }
}
