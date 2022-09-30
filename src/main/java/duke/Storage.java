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

// deals with loading tasks from the file and saving tasks in the file
public class Storage {
    public static final String FILE_PATH = "data/duke.txt";

    public static void addInputFileEvent(ArrayList<Task> tasks, String[] task) throws DukeException {
        Task t = new Event(task[2], task[3]);
        tasks.add(t);
        if (task[1].equals("1")) {
            t.setDone(t.isDone);
        }
    }

    public static void addInputFileDeadline(ArrayList<Task> tasks, String[] task) throws DukeException {
        Task t = new Deadline(task[2], task[3]);
        tasks.add(t);
        if (task[1].equals("1")) {
            t.setDone(t.isDone);
        }
    }

    public static void addInputFileTodo(ArrayList<Task> tasks, String[] task) throws DukeException {
        Task t = new Todo(task[2]);
        tasks.add(t);
        if (task[1].equals("1")) {
            t.setDone(true);
        }
    }

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

    public static void saveNewList(TaskList tasks) {
        try {
            // for (Task task : tasks) {
            //     // if task is Todo type, append to file T | 0/1 | task description
            //     String boolValue = Task.getBoolValue(task);
            //     if (task instanceof Todo) {
            //         appendToFile("T | " + boolValue + " | " + task.description + System.lineSeparator());
            //     }
            //     // if task is Deadline type, append to file D | 0/1 | task description | by: deadline
            //     if (task instanceof Deadline) {
            //         appendToFile("D | " + boolValue + " | " + task.description + " | " + ((Deadline) task).by + System.lineSeparator());
            //     }
            //     // if task is Event type, append to file E | 0/1 | task description | at: event time
            //     if (task instanceof Event) {
            //         appendToFile("E | " + boolValue + " | " + task.description + " | " + ((Event) task).at + System.lineSeparator());
            //     }
            // }
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
