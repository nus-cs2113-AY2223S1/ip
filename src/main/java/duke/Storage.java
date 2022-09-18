package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private static String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }
    public static String getFilePath() {
        return filePath;
    }

    public static void saveFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, false);
        fw.write(textToAdd);
        fw.close();
    }
    public static String loadFile() throws DukeException, IOException {
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