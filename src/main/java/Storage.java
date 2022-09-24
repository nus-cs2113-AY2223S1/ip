import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Storage {
    private static final String DUKE_DUMP_FILE = "duke.txt";

    private static String getItemType(Task task) {
        if (task instanceof Deadline) {
            return "D";
        }
        if (task instanceof Event) {
            return "E";
        }
        if (task instanceof Todo) {
            return "O";
        }
        return "T";
    }

    private static String getItemDescription(Task task) {
        return task.getTaskDescription();
    }

    private static String getItemMark(Task task) {
        if (task.getTaskMark()) {
            return "M";
        }
        else {
            return "U";
        }
    }

    private static String getItemTime(Task task) {
        if (task instanceof Deadline) {
            return ((Deadline) task).getBy();
        }
        if (task instanceof Event) {
            return ((Event) task).getDuration();
        }
        return "";
    }


    public static void dumpTask(Task task) {
        try {
            File file_name = new File(DUKE_DUMP_FILE);
            file_name.createNewFile();
            BufferedWriter out = new BufferedWriter(new FileWriter(file_name));

            String type = getItemType(task);
            String description = getItemDescription(task);
            String isMarked = getItemMark(task);
            String time = getItemTime(task);
            String item = type + " "
                        + description + " "
                        + isMarked + " "
                        + time;

            out.write(item + "\r\n");
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
