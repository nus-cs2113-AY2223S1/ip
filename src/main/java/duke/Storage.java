package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Deals with loading tasks from the specified file and saving tasks in the specified file
 */
public class Storage {
    private static String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }
    public static String getFilePath() {
        return filePath;
    }

    /**
     * Clears content in the file and writes new text into the file.
     * @param filePath Specified file path.
     * @param textToAdd Text to write into the file.
     * @throws IOException If input/output operations fail or are interrupted.
     */
    public static void saveFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, false);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Returns a string which contains the content of the file.
     * Lines are separated by '/'.
     * Creates a new directory if specified directory does not exist.
     * Creates a new file if specified file does not exist.
     * @return fileContent Content of the file.
     * @throws IOException If input/output operations fail or are interrupted.
     */
    public static String loadFile() throws IOException {
        File f = new File(filePath);
        String[] words = filePath.split("/");
        String dirName = words[0] + '/';
        File dir = new File(dirName);
        if (!dir.isDirectory()) {
            dir.mkdir();
        }
        if (!f.exists()) {
            f.createNewFile();
        }
        Scanner s = new Scanner(f);
        String fileContent = "";
        while(s.hasNext()) {
            fileContent += s.nextLine() + "/";    //backslash to represent end of line
        }
        return fileContent;
    }
}