package duke.main;
import duke.tasks.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    static String filePath;
    static String FILE_SEPARATOR = "-";

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public Scanner load() throws FileNotFoundException {
        try {
            File data = new File(filePath);
            Scanner fileReader = new Scanner(data);
            return fileReader;
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        }
    }

    public static String formatText(Task task, String text) {
        String type = TaskList.findTaskType(task);
        String date = task.getDate();
        String name = task.getName();
        text += type + FILE_SEPARATOR + name + FILE_SEPARATOR + date
                + System.lineSeparator();
        return text;
    }

    public static void save() {
        try {
            ArrayList<Task> tasks = TaskList.tasks;
            String text = "";
            for (Task task : tasks) {
                text += formatText(task, text);
            }
            FileWriter fw = new FileWriter(filePath);
            fw.write(text);
            fw.close();
        } catch (IOException e) {
            System.out.println ("The file does not exist yet!");
        }
    }
}
