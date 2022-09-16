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
import duke.task.TaskList;

public class Storage {
    private String file_directory;
    private static String file_path;
    private static int tasksCount = 0;

    public Storage() {
        this.file_directory = "./data";
        this.file_path = "./data/duke.txt";
    }

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

    public TaskList loadFile() throws IOException {
        File directory = new File(file_directory);
        if(!directory.exists()){
            directory.mkdir();
        }
        File file = new File(file_path);
        if(!file.exists()) {
            file.createNewFile();
        }
        Scanner fileScanner = new Scanner(file);
        ArrayList<Task> tasks =  assembleTasks(fileScanner);
        int tasksCount = getTasksCount();
        return new TaskList(tasks, tasksCount);

    }

    public static void saveFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(file_path);
        for(int i = 0; i < tasks.size(); i++){
            fw.write(tasks.get(i).formattedInformation() + "\n");
        }
        fw.close();
    }
}