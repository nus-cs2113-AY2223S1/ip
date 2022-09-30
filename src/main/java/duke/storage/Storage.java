package duke.storage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import duke.tasks.TaskList;

import duke.tasks.*;


/**
 * Stores all the tasks that the user has inputted previously.
 *
 */

public class Storage
{
    public static final java.nio.file.Path DATA_STORAGE_PATH = java.nio.file.Paths.get(System.getProperty("user.dir"), "src", "main", "java", "duke", "data","data.txt");


    /**
     * Creates a text file to store all the tasks that user has inputted.
     */
    public static void createDataFile (){
        if (!(java.nio.file.Files.exists(DATA_STORAGE_PATH))){
            File file = new File (DATA_STORAGE_PATH.toString());
            file.getParentFile().mkdirs();
            System.out.println("Data Storage File successfully created \n");
        }
    }


    /**
     * Saves all the tasks in the TaskList permanently in a text file so that user can access the next time.
     *
     * @throws IOException if the file cannot be manipulated.
     */
    public static void saveTasks () throws IOException {
        FileWriter fw = new FileWriter(DATA_STORAGE_PATH.toString());
        for (int i = 0; i < (TaskList.taskList).size(); i++){
            String task = TaskList.taskList.get(i).toString();
            fw.write(task + "\n");
        }
        fw.close();

    }


    /**
     * Loads the previously saved data into the TaskList so that user can access and update.
     * Read the text file and classify the type of the task : todo/deadline/event
     * Populate the TaskList with the classified tasks.
     *
     * @throws FileNotFoundException if the file does not exist.
     *
     */
    public static void loadTasks () throws FileNotFoundException {
        createDataFile();
        File f = new File(DATA_STORAGE_PATH.toString());
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String data = s.nextLine();
            if (data.contains("[T]")){
                Task t = new Todo (data.substring(data.indexOf("[T]")+ 6));
                TaskList.taskList.add(t);
                if (data.contains("[X]")){
                    t.markAsDone();
                }
            }else if (data.contains("[D]")){
                String deadline_description = data.substring(data.indexOf("[D]") + 6, data.indexOf("(by"));
                String deadline = data.substring(data.indexOf("(by") + 5, data.length() - 1);
                Task t = new Deadline (deadline_description, deadline);
                TaskList.taskList.add(t);
                if (data.contains("[X]")){
                    t.markAsDone();
                }
            }else if (data.contains("[E]")){
                String event_description = data.substring(data.indexOf("[E]") + 6, data.indexOf("(at"));
                String time = data.substring(data.indexOf("(at") + 5, data.length() - 1);
                Task t = new Deadline (event_description, time);
                TaskList.taskList.add(t);
                if (data.contains("[X]")){
                    t.markAsDone();
                }
            }
        }
        s.close();
    }

}
