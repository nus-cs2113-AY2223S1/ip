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

    /**
     * Loads duke.txt
     * Throws error if file is not found
     * @return Scanned input from duke.txt
     * @throws FileNotFoundException if there is no duke.txt
     */
    public Scanner load() throws FileNotFoundException {
        try {
            File data = new File(filePath);
            Scanner fileReader = new Scanner(data);
            return fileReader;
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        }
    }

    /**
     * Formats tasks into a common format to be saved in duke.txt
     * @param task task from tasks array
     * @return formatted text
     */
    public static String formatText(Task task) {
        String type = TaskList.findTaskType(task);
        String date = task.getDate();
        String name = task.getName();
        String text = type + FILE_SEPARATOR + name + FILE_SEPARATOR + date
                + System.lineSeparator();
        return text;
    }

    /**
     * Saves all tasks in tasks array into duke.txt
     */
    public static void save() {
        try {
            String text = "";
            for (Task task : TaskList.tasks) {
                text += formatText(task);
            }
            FileWriter fw = new FileWriter(filePath);
            fw.write(text);
            fw.close();
        } catch (IOException e) {
            System.out.println ("The file does not exist yet!");
        }
    }
}
