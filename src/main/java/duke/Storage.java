package duke;

import duke.task.Task;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class to write to and read from the text files
 * Saves the files in the right folder in the right name
 */
public class Storage{
    private static final String FILE_DIRECTORY = "data";
    private static final String FILE_PATH = "data/duke.txt";

    public Storage() {
    }

    /**
     * Method to read file
     * @return links to the second read file method below
     * @throws IOException
     */
    public static TaskList readFile() throws IOException {
        return readFile(FILE_PATH, FILE_DIRECTORY);
    }

    /**
     * Method to read file
     * @param filePath the file path
     * @param fileDirectory the file directory
     * @return a readTask method to go over each line in the input text file
     * @throws IOException if error in file path or file directory
     */
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

    /**
     * Method to read each line in the input text file
     * @param myScanner the scanner reading the file
     * @return a task manager containing the relevant classes
     */
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

    /**
     * Method to write to the output file
     * @param myTasks the list of tasks
     * @throws IOException if error in output file
     */
    public static void writeToFile(ArrayList<Task> myTasks) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        for (Task myTask : myTasks) {
            fw.append(myTask.toOutputFileFormat()).append("\n");
        }
        fw.close();
    }

    /**
     * Method to update the output when there are changes
     * @param myTasks the list of tasks
     */
    public static void updateFile(ArrayList<Task> myTasks) {
        try{
            writeToFile(myTasks);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}