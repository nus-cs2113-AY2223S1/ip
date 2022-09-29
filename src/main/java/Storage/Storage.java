package Storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Parser.Parser;
import Tasks.Task;

public class Storage {
    public static final String DATA_FILE_PATH = "data.txt";

    public Storage() {
        File f = new File(DATA_FILE_PATH);
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                System.out.println("An error occurred while creating 'data.txt'");
            }
        }
    }

    /**
     * Loads a task list from the storage file if it contains a task list from the application's previous use.
     *
     * @param list The task list to which previous tasks are loaded onto.
     * @param filePath The file path of the storage file containing the user's previous task list.
     */
    public void populateInitialList(ArrayList<Task> list, String filePath) {
        File f = new File(filePath);
        Scanner s = getInitialListScanner(f);

        while (s.hasNext()) {
            String nextTask = s.next();
            String[] taskParameters = nextTask.split(" \\| ");

            new Parser().parseInitialList(list, taskParameters);
        }
    }

    /**
     * Initializes a scanner to read from a .txt file.
     *
     * @param f The .txt file to be read by the scanner.
     * @return Returns the scanner which will read from the given file.
     */
    private static Scanner getInitialListScanner(File f) {
        Scanner s = null;
        try {
            s = new Scanner(f);
            s.useDelimiter(System.lineSeparator());
        } catch (FileNotFoundException e) {
            System.out.println("'data.txt' not found");
        }
        return s;
    }
}
