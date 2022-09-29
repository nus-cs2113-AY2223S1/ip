package duke;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// deals with loading tasks from the file and saving tasks in the file
public class Storage {
    private static final String FILE_PATH = "data/duke.txt";

    public static ArrayList<Task> loadInputFile() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(FILE_PATH);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String[] task = s.nextLine().split(" \\| ");
            switch (task[0]) {
            case "T":
                try {
                    Storage.addInputFileTodo(tasks, task);
                } catch (DukeException e) {
                    System.out.println("Todo not added");
                }
                break;
            case "D":
                try {
                    addInputFileDeadline(tasks, task);
                } catch (DukeException e) {
                    System.out.println("Deadline not added");
                }
                break;
            case "E":
                try {
                    addInputFileEvent(tasks, task);
                } catch (DukeException e) {
                    System.out.println("Event not added");
                }
                break;
            default:
                break;
            }
        }
        return tasks;
    } 

    public static void createNewFile() {
        File dir = new File("data");
        dir.mkdir();
        File f = new File("data/duke.txt");
        try {
            f.createNewFile();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public static void addInputFileEvent(ArrayList<Task> tasks, String[] task) throws DukeException, FileNotFoundException {
        tasks.add(new Event(task[2], task[3]));
        if (task[1].equals("1")) {
            tasks.get(tasks.size() - 1).setDone(tasks.get(tasks.size() - 1).isDone);
        }
    }

    public static void addInputFileDeadline(ArrayList<Task> tasks, String[] task) throws DukeException, FileNotFoundException {
        tasks.add(new Deadline(task[2], task[3]));
        if (task[1].equals("1")) {
            tasks.get(tasks.size() - 1).setDone(tasks.get(tasks.size() - 1).isDone);
        }
    }

    public static void addInputFileTodo(ArrayList<Task> tasks, String[] task) throws DukeException, FileNotFoundException {
        tasks.add(new Todo(task[2]));
        if (task[1].equals("1")) {
            tasks.get(tasks.size() - 1).setDone(tasks.get(tasks.size() - 1).isDone);
        }
    }

    public static void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter("data/duke.txt", true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    public static void saveNewList(ArrayList<Task> tasks) {
        try {
            for (Task task : tasks) {
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

    public static void clearCurrentFile() {
        try {
            FileWriter fw = new FileWriter("data/duke.txt");
            fw.write("");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
