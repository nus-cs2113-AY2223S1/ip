package duke;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import duke.Duke;
import duke.Tasks.*;

public class Storage
{
    public static void saveTasks () throws IOException {
        FileWriter fw = new FileWriter(Duke.DATA_STORAGE_PATH.toString());
        for (int i = 0; i < (Duke.taskList).size(); i++){
            String task = Duke.taskList.get(i).toString();
            fw.write(task + "\n");
        }
        fw.close();

    }

    public static void loadTasks () throws FileNotFoundException {
        File f = new File(Duke.DATA_STORAGE_PATH.toString());
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

}
