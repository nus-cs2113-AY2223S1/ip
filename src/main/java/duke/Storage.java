package duke;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class contains methods to deal with any operations related to the storage of the tasks into a file.
 */
public class Storage {
    public static final String FILE_PATH = "data/duke.txt";

    /**
     * Adds a Todo task to the task list.
     * @param tasks The task list.
     * @param task The Event task to be added.
     * @throws DukeException If the task is not an Event task.
     */
    public static void addInputFileEvent(ArrayList<Task> tasks, String[] task) throws DukeException {
        Task t = new Event(task[2], task[3]);
        tasks.add(t);
        if (task[1].equals("1")) {
            t.setDone(t.isDone);
        }
    }

    /**
     * Adds a Deadline task to the task list.
     * @param tasks The task list.
     * @param task The Deadline task to be added.
     * @throws DukeException If the task is not a Deadline task.
     */
    public static void addInputFileDeadline(ArrayList<Task> tasks, String[] task) throws DukeException {
        Task t = new Deadline(task[2], task[3]);
        tasks.add(t);
        if (task[1].equals("1")) {
            t.setDone(t.isDone);
        }
    }

    /**
     * Adds a Todo task to the task list.
     * @param tasks The task list.
     * @param task The Todo task to be added.
     * @throws DukeException If the task is not a Todo task.
     */
    public static void addInputFileTodo(ArrayList<Task> tasks, String[] task) throws DukeException {
        Task t = new Todo(task[2]);
        tasks.add(t);
        if (task[1].equals("1")) {
            t.setDone(true);
        }
    }

    /**
     * Loads the tasks from the default input file and adds them to the task list.
     * If the file does not exist, it will create a new file.
     * If the tasks from the input file is invalid, it will throw DukeException and will not be added to task list.
     */
    public static void loadInputFile(ArrayList<Task> tasks) {
        try {
            File f = new File(FILE_PATH);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String[] task = s.nextLine().split(" \\| ");
                switch (task[0]) {
                case "T":
                    addInputFileTodo(tasks, task);
                    break;
                case "D":
                    addInputFileDeadline(tasks, task);
                    break;
                case "E":
                    addInputFileEvent(tasks, task);
                    break;
                default:
                    break;
                }
            }
            s.close();
        } catch (FileNotFoundException e) {
            createNewFile();
        } catch (DukeException e) {
            System.out.println("Task not added");
        }
    }

    /**
     * Creates a new file if the file does not exist.
     */
    public static void createNewFile() {
        File dir = new File("data");
        dir.mkdir();
        File f = new File(FILE_PATH);
        try {
            f.createNewFile();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public static void clearCurrentFile() {
        try {
            FileWriter fw = new FileWriter("data/duke.txt");
            fw.write("");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter("data/duke.txt", true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * Saves the tasks in the task list to the default input file.
     * @param tasks the task list
     */
    public static void saveNewList(TaskList tasks) {
        try {
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                String boolValue = Task.getBoolValue(task);
                if (task instanceof Todo) {
                    appendToFile("T | " + boolValue + " | " + task.description + System.lineSeparator());
                }
                if (task instanceof Deadline) {
                    appendToFile("D | " + boolValue + " | " + task.description + " | " + ((Deadline) task).by + System.lineSeparator());
                }
                if (task instanceof Event) {
                    appendToFile("E | " + boolValue + " | " + task.description + " | " + ((Event) task).at + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
