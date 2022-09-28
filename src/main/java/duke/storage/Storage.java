package duke.storage;

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

public class Storage {
    public static void loadInputFile(ArrayList<Task> tasks) throws FileNotFoundException {
        File f = new File("data/duke.txt");
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

    public static void addInputFileEvent(ArrayList<Task> tasks, String[] task) throws DukeException {
        tasks.add(new Event(task[2], task[3]));
        if (task[1].equals("1")) {
            tasks.get(tasks.size() - 1).setDone(tasks.get(tasks.size() - 1).isDone);
        }
    }

    public static void addInputFileDeadline(ArrayList<Task> tasks, String[] task) throws DukeException {
        tasks.add(new Deadline(task[2], task[3]));
        if (task[1].equals("1")) {
            tasks.get(tasks.size() - 1).setDone(tasks.get(tasks.size() - 1).isDone);
        }
    }

    public static void addInputFileTodo(ArrayList<Task> tasks, String[] task) throws DukeException {
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
                // if task is Todo type, append to file T | 0/1 | task description
                String boolValue = Task.getBoolValue(task);
                if (task instanceof Todo) {
                    appendToFile("T | " + boolValue + " | " + task.description + System.lineSeparator());
                }
                // if task is Deadline type, append to file D | 0/1 | task description | by: deadline
                if (task instanceof Deadline) {
                    appendToFile("D | " + boolValue + " | " + task.description + " | " + ((Deadline) task).by + System.lineSeparator());
                }
                // if task is Event type, append to file E | 0/1 | task description | at: event time
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
