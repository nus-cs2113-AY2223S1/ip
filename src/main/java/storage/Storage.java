package storage;

import task.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Storage {

    private static Path taskDataPath = Paths.get(System.getProperty("user.dir"), "data", "tasks.txt");
    private static Path dataDirPath = Paths.get(System.getProperty("user.dir"), "data");
    private ArrayList<Task> tasks = new ArrayList<>();

    public void writeToFile(Task task) throws IOException {
        FileWriter fw = new FileWriter(taskDataPath.toString(), true);
        String textToAdd = taskToText(task);
        fw.write(textToAdd);
        fw.close();
    }


    public ArrayList<Task> startReading() {
        try {
            makeMissingDirectory();
            readFile();
        } catch (FileNotFoundException e) {
            System.out.println("File cannot be found");
        } catch (IOException e) {
            System.out.println("Some IO error has occurred");
        }
        return tasks;
    }

    private void readFile() throws FileNotFoundException {
        String filePath = taskDataPath.toString();
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String[] input = s.nextLine().split(" \\| ");
            switch (input[0]) {
            case ("T"):
                insertTodo(input, tasks);
                break;
            case ("D"):
                insertDeadline(input, tasks);
                break;
            case ("E"):
                insertEvent(input, tasks);
                break;
            }
        }
    }

    private static void makeMissingDirectory() throws IOException {
        File dir = new File(dataDirPath.toString());
        if (!dir.exists()) {
            dir.mkdir();
        } else {
            return;
        }
        FileWriter fw = new FileWriter(taskDataPath.toString());
    }

    private void insertTodo(String[] input, ArrayList<Task> tasks) {
        Todo todo = new Todo(input[2]);
        if (input[1].equals("1")) {
            todo.setDone(true);
        } else {
            todo.setDone(false);
        }
        tasks.add(todo);
    }

    private void insertDeadline(String[] input, ArrayList<Task> tasks) {
        Deadline deadline = new Deadline(input[2], input[3]);
        if (input[1].equals("1")) {
            deadline.setDone(true);
        } else {
            deadline.setDone(false);
        }
        tasks.add(deadline);
    }

    private void insertEvent(String[] input, ArrayList<Task> tasks) {
        Event event = new Event(input[2], input[3]);
        if (input[1].equals("1")) {
            event.setDone(true);
        } else {
            event.setDone(false);
        }
        tasks.add(event);
    }

    private String taskToText(Task task) {
        if (task.getClass() == Todo.class) {
            return "T | " + task.getStatusInNumber() + " | " + task.getDescription() + "\n";
        } else if (task.getClass() == Deadline.class) {
            return "D | " + task.getStatusInNumber() + " | "
                    + task.getDescription() + " | " + ((Deadline) task).getBy() + "\n";
        } else {
            return "E | " + task.getStatusInNumber() + " | "
                    + task.getDescription() + " | " + ((Event) task).getAt() + "\n";
        }
    }

    public void deleteContent() throws IOException {
        new FileWriter(taskDataPath.toString()).close();
    }
}
