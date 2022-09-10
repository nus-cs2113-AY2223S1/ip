package duke;

import duke.task.Task;
import duke.task.TaskManager;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class FileSaver{
    private static final String FILE_DIRECTORY = "data";
    private static final String FILE_PATH = "src/main/java/data/duke.txt";

    public static TaskManager readFile() throws IOException {
        return readFile(FILE_PATH);
    }

    public static TaskManager readFile(String filePath) throws IOException {
        File file  = new File(filePath);
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
            System.out.println(done);
            reader.handleInput(inputTask);
            taskCount++;
            if (done.equals("X")) {
                reader.markAsDone("mark " + taskCount);
            }
        }
        return reader;
    }

    public static void writeToFile(Task[] myTasks, int taskCount) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        for (int i=0; i < taskCount; ++i) {
            fw.append(myTasks[i].toOutputFileFormat() + "\n");
        }
        fw.close();
    }

    public static void updateFile(Task[] myTasks, int taskCount) {
        try{
            writeToFile(myTasks, taskCount);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}