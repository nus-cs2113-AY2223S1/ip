import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class WriteToFile {

    protected static void appendToFile(Path filePath, Task[] list) throws IOException {
        File f = new File(filePath + "/duke.txt");
        FileWriter fw = new FileWriter(f, true); // create a FileWriter in append mode

        for (Task task : list) {
            if (task != null) {
                fw.write(task.fileString() + String.format("%n"));
            } else {
                break;
            }
        }
        fw.close();
    }

    protected static void createFile(Path filePath) throws IOException {
        File f = new File(filePath + "/duke.txt");
        FileWriter fw = new FileWriter(f);
        fw.close();
    }
}
