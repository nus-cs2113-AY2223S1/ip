package duke.storage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskManager;

public class Storage {
    private static final String FILE_DIRECTORY = "src/main/java/duke/data";
    private static final String FILE_PATH = "src/main/java/duke/data/duke.txt";
    private static int tasksCount = 0;

    public static int getTasksCount() {
        return tasksCount;
    }
    public static ArrayList<Task> assembleTasks(Scanner fileScanner) {
        ArrayList<Task> tasks = new ArrayList<Task>();
        while (fileScanner.hasNext()) {
            String[] text = fileScanner.nextLine().split(" \\| ");
            Task curr = new Task("");
            String type = text[0];
            if (type.equals("T")) {
                curr = new Todo(text[2]);
                if (text[1].equals("1")) {
                    curr.markTaskAsDone();
                }
            } else if (type.equals("D")) {
                curr = new Deadline(text[2], text[3]);
                if (text[1].equals("1")) {
                    curr.markTaskAsDone();
                }
            } else if (type.equals("E")) {
                curr = new Event(text[2], text[3]);
                if (text[1].equals("1")) {
                    curr.markTaskAsDone();
                }
            }
            tasks.add(curr);
            tasksCount++;
        }
        return tasks;
    }

    public static TaskManager loadFile() throws IOException {
        File directory = new File(FILE_DIRECTORY);
        if(!directory.exists()){
            directory.mkdir();
        }
        File file = new File(FILE_PATH);
        if(!file.exists()) {
            file.createNewFile();
        }
        Scanner fileScanner = new Scanner(file);
        ArrayList<Task> tasks =  assembleTasks(fileScanner);
        int tasksCount = getTasksCount();
        return new TaskManager(tasks, tasksCount);

    }

    public static void saveFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        for(int i = 0; i < tasks.size(); i++){
            fw.write(tasks.get(i).formattedInformation() + "\n");
        }
        fw.close();
    }
}