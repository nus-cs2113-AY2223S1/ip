import java.util.ArrayList;
import java.util.List;


public class ModifyList {
    private final List<Task> tasks = new ArrayList<Task>();


    public static String lineSeparator() {
        return "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
    }

    private void handleTask(String taskDetails, Task task) {
        tasks.add(task);
        System.out.println(lineSeparator() +
                "Noted. Following task has been added: " + '\n' + taskDetails + "\n" +
                "Total tasks in list: " + tasks.size() + '\n' +
                lineSeparator());
    }

    public void task(String taskType, String details){
        String[] separateDetails;
        String description;
        String time;
        switch (taskType) {
            case "todo":
                Todo task = new Todo(details);
                handleTask(task.getDescriptionAndStatus(), task);

            case "deadline":
                separateDetails = details.split("/");
                description = separateDetails[0];
                time = separateDetails[1];
                time = new StringBuilder(time).insert(time.indexOf(' '), ":").toString(); // add colon
                Deadline deadline = new Deadline(description, time);
                handleTask(deadline.getDescriptionAndStatus(), deadline);
            case "event":

                separateDetails = details.split("/");
                description = separateDetails[0];
                time = separateDetails[1];
                time = new StringBuilder(time).insert(time.indexOf(' '), ":").toString(); // add colon
                Event event = new Event(description, time);
                handleTask(event.getDescriptionAndStatus(), event);
        }
    }

    public void list() {
        int itemNumber = 1;
        System.out.println(lineSeparator() + "Here are your list of tasks:\n");
        for (Task task : tasks) {
            System.out.println(itemNumber + "." + task.getDescriptionAndStatus() + "\n");
            itemNumber++;
        }
        System.out.println(lineSeparator());
    }
    public void mark(int index) {
            Task task = tasks.get(index - 1);
            task.setDone(true);
            tasks.set(index - 1, task);
            System.out.println(lineSeparator() +
                    "The following task been marked as completed:\n" +
                    tasks.get(index - 1).getDescriptionAndStatus() + "\n" +
                    lineSeparator());
    }
    public void unmark(int index) {
            Task task = tasks.get(index - 1);
            task.setDone(false);
            tasks.set(index - 1, task);
            System.out.println(lineSeparator() +
                    "The following task been marked as not done yet:\n" +
                    tasks.get(index - 1).getDescriptionAndStatus() + "\n" +
                    lineSeparator());
    }
}
