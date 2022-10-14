import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {

    /**
     * Makes sure that the directory in which the file will be stored
     * exists
     * @param path
     */
    public static void createDirectories(Path path) {
        try{
            Files.createDirectories(path);
        }catch (FileAlreadyExistsException ignored){} catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Either create an empty arraylist to where tasks will be written
     * or return an arraylist that contains all the tasks from the previous session
     * @param path
     * @return Arraylist of tasks that was read in from the file
     * @throws IOException
     */
    public static ArrayList<Task> readInPreviousTasks(Path path) throws IOException {
        ArrayList<Task> todoList;

        try{
            todoList = ReadFromFile.addFileContents(path);
            System.out.println("I found some tasks saved, I have added them to the current session");

        } catch (FileNotFoundException | DukeExceptions e){
            System.out.println("File not found, creating one");
            WriteToFile.createFile(path);
            todoList = new ArrayList<>();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return todoList;
    }

    /**
     * Writes the final todoList to file
     * @param path
     * @param todolist
     * @throws IOException
     */
    public static void writeToFile(Path path, ArrayList<Task> todolist) throws IOException {
        WriteToFile.appendToFile(path, TaskList.todoList);
    }
}
