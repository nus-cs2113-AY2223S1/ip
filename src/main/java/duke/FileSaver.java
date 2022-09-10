package duke;

import duke.task.Task;
import duke.task.TaskManager;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class FileSaver{
    private static final String FILE_DIRECTORY = "src/main/java/data";
    private static final String FILE_PATH = "src/main/java/data/duke.txt";

    public static TaskManager readFile() throws IOException {
        return readFile(FILE_PATH, FILE_DIRECTORY);
    }

    public static TaskManager readFile(String filePath, String fileDirectory) throws IOException {
        File fileDir = new File(fileDirectory);
        if (!fileDir.exists()) {
            fileDir.mkdir();
        }
        File file  = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }
        Scanner myScanner = new Scanner(file);
        return readTask(myScanner);
    }

    public static TaskManager readTask(Scanner myScanner) {
        TaskManager reader = new TaskManager();
        int taskCount = 0;
        while (myScanner.hasNext() && taskCount < 100) {
            String output = myScanner.nextLine();
            String inputTask = output.substring(0, output.length() - 2);
            String done = output.substring(output.length() - 1);
            reader.handleInput(inputTask);
            taskCount++;
            if (done.equals("X")) {
                reader.markAsDone("mark " + taskCount);
            }
        }
        return reader;
    }

    public static void writeToFile(ArrayList<Task> myTasks) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        for (int i=0; i < myTasks.size(); ++i) {
            fw.append(myTasks.get(i).toOutputFileFormat() + "\n");
        }
        fw.close();
    }

    public static void updateFile(ArrayList<Task> myTasks) {
        try{
            writeToFile(myTasks);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}