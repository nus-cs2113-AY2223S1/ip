package duke.storage;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.lang.Exception;
import duke.data.tag.TaskList;
import java.nio.file.Files;
import java.util.List;
import java.util.ArrayList;
import duke.data.task.Task;
import duke.storage.TaskListDecoder.DecodeException;

public class StorageFile {
    public static final String STORAGE_DIRECTORY = "./storage";
    public static final String STORAGE_FILE = "./storage/savedTaskList.txt";
    public static final String MESSAGE_LOADED = "Storage File loaded";
    
    
    public final Path path;
    
    public StorageFile(){
        path = Paths.get(STORAGE_FILE);
    }


    public void save(TaskList taskList) {
        try {
            List<String> encodedList = TaskListEncoder.encodeList(taskList);
            Files.write(path, encodedList);
        } catch (IOException e) {
            //TODO:
            System.out.print("Cannot save");
        }
    }
    public TaskList load() throws StorageException {
        TaskList list = new TaskList();
        try{
            ArrayList<Task> decodedList = TaskListDecoder.decodeFile(STORAGE_FILE);
            list.data = decodedList;
            return list;
        } catch (IOException e){
            throw new StorageException("Error loading" + path);
        } catch (DecodeException e){
            throw new StorageException("Error parsing" + path);
        }

    }




    public static class StorageException extends Exception {
        public StorageException(String message){
            super(message);
        }
    }
}
