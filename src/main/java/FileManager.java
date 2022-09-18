import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import dukeTasksPackage.Deadline;
import dukeTasksPackage.Event;
import dukeTasksPackage.Task;
import dukeTasksPackage.Todo;
public class FileManager {
    public static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }
    public static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }
    public static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }
    public static void clearFile(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath, false);
        PrintWriter pw = new PrintWriter(fw, false);
        pw.flush();
        pw.close();
        fw.close();
    }
    public static void loadFile(String filePath, ArrayList<Task> taskList, ArrayList<String> taskOverrideList) throws IOException {
        File textFile = new File(filePath);
        Scanner scan = new Scanner(textFile);
        int i = 0;
        while (scan.hasNextLine()) {
            String input = scan.nextLine();
            taskOverrideList.add(i, input);
            String[] inputArray = input.split("\\|", 0);
            String taskType = inputArray[0];
            char checkChar = inputArray[1].charAt(0);
            String description = inputArray[2];
            if (taskType.equals("D")) {
                String additionalInfo = inputArray[3];
                Deadline d = new Deadline(description, additionalInfo);
                d.status = checkChar;
                taskList.add(i, d);
            } else if (taskType.equals("E")) {
                String additionalInfo = inputArray[3];
                Event e = new Event(description, additionalInfo);
                e.status = checkChar;
                taskList.add(i, e);
            } else {
                Todo t = new Todo(description);
                t.status = checkChar;
                taskList.add(i, t);
            }
            i++;
        }
    }
}
