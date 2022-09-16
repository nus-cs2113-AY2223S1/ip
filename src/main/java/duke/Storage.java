package duke;

import duke.task.Task;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Storage{
    private static final String FILE_DIRECTORY = "data";
    private static final String FILE_PATH = "data/duke.txt";

    public Storage() {
    }

    public static TaskList readFile() throws IOException {
        return readFile(FILE_PATH, FILE_DIRECTORY);
    }

    public static TaskList readFile(String filePath, String fileDirectory) throws IOException {
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

    public static TaskList readTask(Scanner myScanner) {
        TaskList reader = new TaskList();
        int taskCount = 0;
        while (myScanner.hasNext() && taskCount < 100) {
            String output = myScanner.nextLine();
            String inputTask = output.substring(0, output.length() - 2);
            String done = output.substring(output.length() - 1);
            Parser.handleInput(inputTask, reader);
            taskCount++;
            if (done.equals("X")) {
                reader.markAsDone("mark " + taskCount);
            }
        }
        System.out.println(Ui.DASH);
        System.out.println("Previously saved task: ");
        reader.print();
        System.out.println(Ui.DASH);
        System.out.println(" ");
        return reader;
    }

    public static void writeToFile(ArrayList<Task> myTasks) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        for (Task myTask : myTasks) {
            fw.append(myTask.toOutputFileFormat()).append("\n");
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