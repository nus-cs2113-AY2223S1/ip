import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Storage{
    private final File file;
    private final Ui ui = new Ui();
    private final Parser parse = new Parser();
    public Storage(String filePath) {
        file = new File(filePath);
    }

    /**
     * Creates the initial task list when first booting up the Duke bot
     * Throws an error if the file is not found and creates a new task list if the file is empty
     * @return the task list after loading the data from the data file
     */
    public List<Task> loadFileData(){
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            if (file.length() != 0) {
                System.out.println("Loading existing file data...\n");
                Scanner s = new Scanner(file);
                while (s.hasNext()) {
                    String task = s.nextLine();
                    tasks.add(parse.taskFromFile(task));
                }
                return tasks;
            }
            else {
                System.out.println("No existing file data...creating empty list\n");
                return null;
            }
        } catch (FileNotFoundException error) {
            ui.missingFile();
            return null;
        } catch (Error error) {
            System.out.println(error.getMessage());
            System.exit(0);
            return null;
        }
    }

    /**
     * Get tasks from the task list and write them in a different format to be displayed
     * in the data file
     * @param tasklist list of tasks to get the tasks from
     */
    public void writeToFile(TaskList tasklist) {
        List<Task> tasks = tasklist.getTaskList();
        try {
            FileWriter fw = new FileWriter(file);
            for (Task task : tasks) {
                fw.write(task.fileFormat() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException error) {
            ui.missingFile();
        }
    }

    /**
     * Adding a new task to on a new line of the data file
     * @param textToAppend task details in specific file format to be added to the data file
     */
    public void appendToFile(String textToAppend) {
        try {
            FileWriter fw = new FileWriter(file, true);
            fw.write(textToAppend + System.lineSeparator());
            fw.close();
        } catch (IOException error) {
            ui.missingFile();
        }
    }
}
