package duke.command;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import duke.exception.DukeException;
import duke.exception.InvalidInputException;
import duke.exception.InvalidOutputException;
import duke.task.Task;
import duke.task.TaskManager;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
    public static void readFile(String filePath) throws FileNotFoundException {
        final String READING_FILE_START = "Reading file inputs...";
        final String READING_FILE_END = "Done reading file inputs!";

        File file = new File(filePath);

        Scanner scanner = new Scanner(file);

        System.out.println(READING_FILE_START);

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            executeLine(line);
        }

        System.out.println(READING_FILE_END);

        scanner.close();
    }

    public static void writeFile(String filePath) throws IOException {
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
        } catch (InvalidInputException e) {
            System.out.println("Invalid input line :(");
            return;
        }

        String inputTask = fileData[0];
        String inputMark = fileData[1];

        try {
            InputManager.handleInput(inputTask);
            InputManager.handleInput(inputMark);
        } catch (DukeException e) {
            System.out.println("Bad file input :(");
        }
    }

    private static void writeFirstLine(String filePath) throws IOException {
        ArrayList<Task> tasks = TaskManager.getTasks();

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
        ArrayList<Task> tasks = TaskManager.getTasks();

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
