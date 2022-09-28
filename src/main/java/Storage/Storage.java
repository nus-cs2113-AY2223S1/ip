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

    public void populateInitialList(ArrayList<Task> list, String filePath) {
        File f = new File(filePath);
        Scanner s = getInitialListScanner(f);

        while (s.hasNext()) {
            String nextTask = s.next();
            String[] taskParameters = nextTask.split(" \\| ");

            new Parser().parseInitialList(list, taskParameters);
        }
    }

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
