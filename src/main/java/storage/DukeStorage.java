package storage;

import dukeExceptionsPackage.FileDoesNotExistException;
import dukeTasksPackage.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DukeStorage {
    public static final String FILE_PATH = "C:\\Users\\cwxky\\projects\\cs2113-git\\data\\duke.txt";
    public final Path path;
    public TaskList tasks;


    /**
     * Constructor for Duke Storage
     * @param path path of file
     */
    public DukeStorage(Path path) {
        this.path = Paths.get(FILE_PATH);
    }

    /**
     * Create a task list
     * @param tasks task list
     */
    public void initaliseTaskList(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * saves the task list data into the text file
     * @param tasks task list
     */
    public void saveTasks(TaskList tasks) {
        try {
            List<String> encodedTasks = encodeTaskList.encode(tasks);
            Files.write(path, encodedTasks);
        } catch (IOException e) {
            System.out.println("Something went wrong. Duke is unable to save the tasks.");
        }
    }

    /**
     * load the data from the text file into Task List and return sit.
     * @return an empty Task List if the file does not exist.
     */
    public TaskList loadTextFile() throws FileDoesNotExistException {
        if (!Files.exists(path) || !Files.isRegularFile(path)) {
            return new TaskList();
        }
        try {
            Scanner scanner = new Scanner(path);
            TaskList tasks = new TaskList();
            while (scanner.hasNext()) {
                String input = scanner.nextLine();
                String[] words = input.split("\\|", 0);
                if (words[0].equals("T ")) {
                    Todo td = new Todo(words[2]);
                    td.status = words[1].charAt(0);
                    tasks.addTask(td);
                } else if (words[0].equals("D ")) {
                    Deadline dl = new Deadline(words[2], words[3]);
                    dl.status = words[1].charAt(0);
                    tasks.addTask(dl);
                } else if (words[0].equals("E ")) {
                    Event ev = new Event(words[2], words[3]);
                    ev.status = words[1].charAt(0);
                    tasks.addTask(ev);
                }
            }
            return tasks;
        } catch (IOException e) {
            throw new FileDoesNotExistException("cannot load");
        }
    }

    /**
     * To clear all saved inputs
     * @throws IOException
     */
    public  void clearFile() throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH, false);
        PrintWriter pw = new PrintWriter(fw, false);
        pw.flush();
        pw.close();
        fw.close();
    }
}
