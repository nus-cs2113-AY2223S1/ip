package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DukeFile {
    private String file = "data/duke.txt";

    public String getFile() {
        return file;
    }
    public static void saveFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, false);
        fw.write(textToAdd);
        fw.close();
    }
    public static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }
    public static String loadFile(String filePath) throws IOException {
        File f = new File(filePath);
        File dir = new File("data/");
        if (!dir.isDirectory()) {
            dir.mkdir();
        }
        if (!f.exists()) {
            f.createNewFile();
        }
        Scanner s = new Scanner(f);
        String taskList = "";
        while(s.hasNext()) {
            taskList += s.nextLine() + "/";    //backslash to represent end of line
        }
        return taskList;
    }

}