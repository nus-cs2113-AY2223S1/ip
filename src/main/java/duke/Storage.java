package duke;

import duke.task.*;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;

import java.util.ArrayList;

public class Storage {
    public static final String DEFAULT_STORAGE_FILEPATH = "duke.txt";
    public static String path = "";

    public Storage() {
        this(DEFAULT_STORAGE_FILEPATH);
    }

    public Storage(String filePath) {
        path = filePath;
    }


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
            File file_name = new File(path);
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

    public static TaskList loadTask() {
        TaskList taskList = new TaskList();
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            BufferedReader in = new BufferedReader(new FileReader(path));
            String line;
            while ((line = in.readLine()) != null) {
                String[] items = line.split(" ");
                Task task;
                switch (items[0]) {
                case "D":
                    task = new Deadline(items[2], items[3]);
                    if (items[1].equals("M")) {
                        task.setMarked(true);
                    }
                    tasks.add(task);
                    break;
                case "E":
                    task = new Event(items[2], items[3]);
                    if (items[1].equals("M")) {
                        task.setMarked(true);
                    }
                    tasks.add(task);
                    break;
                case "O":
                    task = new Todo(items[2]);
                    if (items[1].equals("M")) {
                        task.setMarked(true);
                    }
                    tasks.add(task);
                    break;
                case "T":
                    task = new Task(items[2]);
                    if (items[1].equals("M")) {
                        task.setMarked(true);
                    }
                    tasks.add(task);
                    break;
                }
            }
            taskList.setTaskList(tasks);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return taskList;
    }
}
