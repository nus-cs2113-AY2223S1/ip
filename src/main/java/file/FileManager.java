package file;

import task.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager {

    private static Path taskDataPath = Paths.get(System.getProperty("user.dir"), "data", "tasks.txt");

    private static Path dataDirPath = Paths.get(System.getProperty("user.dir"), "data");

    public static void writeToFile(Task task) throws IOException {
        FileWriter fw = new FileWriter(taskDataPath.toString(), true);
        String textToAdd = taskToText(task);
        fw.write(textToAdd);
        fw.close();
    }


    public static void startReading() {
        try {
            makeMissingDirectory();
            readFile();
        } catch (FileNotFoundException e) {
            System.out.println("File cannot be found");
        } catch (IOException e) {
            System.out.println("Some IO error has occurred");
        }
    }

    private static void readFile() throws FileNotFoundException {
        String filePath = taskDataPath.toString();
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String[] input = s.nextLine().split(" | ");
            switch (input[0]) {
            case ("T"):
                insertTodo(input);
                break;
            case ("D"):
                insertDeadline(input);
                break;
            case ("E"):
                insertEvent(input);
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

    private static void insertTodo(String[] input) {
        Todo todo = new Todo(input[2]);
        if (input[1].equals("1")) {
            todo.setDone(true);
        } else {
            todo.setDone(false);
        }
        TaskList.addTodo(todo);
    }

    private static void insertDeadline(String[] input) {
        Deadline deadline = new Deadline(input[2], input[3]);
        if (input[1].equals("1")) {
            deadline.setDone(true);
        } else {
            deadline.setDone(false);
        }
        TaskList.addDeadLine(deadline);
    }

    private static void insertEvent(String[] input) {
        Event event = new Event(input[2], input[3]);
        if (input[1].equals("1")) {
            event.setDone(true);
        } else {
            event.setDone(false);
        }
        TaskList.addEvent(event);
    }

    private static String taskToText(Task task) {
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
}
