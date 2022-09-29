package duke.command;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import duke.exception.DukeException;
import duke.exception.InvalidInputException;
import duke.exception.InvalidOutputException;
import duke.task.Task;
import duke.task.TaskList;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private static String FILE_DIRECTORY = "./data";
    private static String FILE_PATH = "./data/duke.txt";

    /**
     * Loads the file at the given file path
     */
    public static void loadFile() {
        try {
            readFile();
        } catch (FileNotFoundException e) {
            handleFileNotFound();
        }
    }

    private static void handleFileNotFound() {
        System.out.println("File not found");

        try {
            createFile();
        } catch (IOException ex) {
            System.out.println("Unable to create file");
        }
    }

    private static void createFile() throws IOException {
        File directory = new File(FILE_DIRECTORY);

        if (!directory.exists()) {
            directory.mkdir();
        }

        File file = new File(FILE_PATH);

        file.createNewFile();

        System.out.println("New file created");
    }

    /**
     * Saves the file to the given file path
     */
    public static void saveFile() {
        try {
            writeFile(FILE_PATH);
        } catch (IOException e) {
            System.out.println("Unable to save");
        }
    }

    /**
     * Reads the file line by line from the given file path
     * 
     * @param filePath the file path provided
     * @throws FileNotFoundException if the file path is invalid
     */
    private static void readFile() throws FileNotFoundException {
        final String READING_FILE_START = "Reading file inputs...";
        final String READING_FILE_END = "Done reading file inputs!";

        File file = new File(FILE_PATH);

        Scanner scanner = new Scanner(file);

        System.out.println(READING_FILE_START);

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            executeLine(line);
        }

        System.out.println(READING_FILE_END);

        scanner.close();
    }

    /**
     * Writes the file line by line to the given file path
     * 
     * @param filePath the file path provided
     * @throws IOException if unable to write to given file path
     */
    private static void writeFile(String filePath) throws IOException {
        final String WRITING_FILE_START = "Writing file outputs...";
        final String WRITING_FILE_END = "Done writing file outputs!";

        System.out.println(WRITING_FILE_START);

        writeFirstLine(filePath);
        writeRemainingLines(filePath);

        System.out.println(WRITING_FILE_END);
    }

    private static void executeLine(String line) {
        String[] fileData;

        try {
            fileData = Parser.parseFileInputs(line);

            String inputTask = fileData[0];
            String inputMark = fileData[1];

            Ui.handleInput(inputTask);
            Ui.handleInput(inputMark);
        } catch (InvalidInputException e) {
            System.out.println("Invalid input line :(");
            return;
        } catch (DukeException e) {
            System.out.println("Bad file input :(");
        }
    }

    private static void writeFirstLine(String filePath) throws IOException {
        ArrayList<Task> tasks = TaskList.getTasks();

        FileWriter fw = new FileWriter(filePath);
        try {
            fw.write(Parser.parseFileOutputs(tasks.get(0).toString()) + System.lineSeparator());
        } catch (InvalidOutputException e) {
            System.out.println("Invalid output line");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Nothing in list!");
        }
        fw.close();
    }

    private static void writeRemainingLines(String filePath) throws IOException {
        ArrayList<Task> tasks = TaskList.getTasks();

        FileWriter fw = new FileWriter(filePath, true);
        for (int i = 1; i < Task.getTaskCount(); i += 1) {
            try {
                fw.write(Parser.parseFileOutputs(tasks.get(i).toString()) + System.lineSeparator());
            } catch (InvalidOutputException e) {
                System.out.println("Invalid output line");
            }
        }
        fw.close();
    }
}
