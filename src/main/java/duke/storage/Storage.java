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

/**
 * Class for loading previously saved user data from the local disk (if any) and saving all new modifications made to that data.
 */
public class Storage {
    private String file_directory;
    private static String file_path;
    private static int tasksCount = 0;

    /**
     * Constructs an instance of the Storage class. It holds a pre-determined file directory
     * and file path to retrieve data from and send data to.
     */
    public Storage() {
        this.file_directory = "./data";
        this.file_path = "./data/duke.txt";
    }

    public static int getTasksCount() {
        return tasksCount;
    }

    /**
     * Reads each task stored in the user's local disk and processes it to correctly set its type and done status.
     * The tasks read are then organised and assembled into an ArrayList for further downstream usage.
     *
     * @param fileScanner Instance of the Scanner class responsible for reading the user's saved tasks.
     * @return An ArrayList of the tasks retrieved from the user's local disk.
     */
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

    /**
     * Loads the user's saved task list from the file directory and path as defined in the constructor.
     * If the file directory cannot be found, a directory with the corresponding name is created.
     * If the file cannot be found, a file with the corresponding name is created.
     *
     * @return The task manager constructed from the user's saved tasks.
     * @throws IOException If an I/O error occurred in creating a new file or the source file could not be found.
     */
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

    /**
     * Saves the user's current list of tasks to the local disk in order for it to be updated with the latest changes.
     *
     * @param tasks The ArrayList containerisation of the tasks in the user's current list of tasks.
     * @throws IOException If an I/O error occurred in opening or writing to the file.
     */
    public static void saveFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(file_path);
        for(int i = 0; i < tasks.size(); i++){
            fw.write(tasks.get(i).formattedInformation() + "\n");
        }
        fw.close();
    }
}