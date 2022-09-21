package duke.storage;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.lang.Exception;
import java.nio.file.Files;
import java.util.List;
import java.util.ArrayList;
import java.io.File;

import duke.data.tag.TaskList; 
import duke.data.task.Task;
import duke.storage.TaskListDecoder.DecodeException;


/** Represent the middleman class between the main and the encoding, decoding */
public class StorageFile {
    private static final String STORAGE_FILE = "./storage/savedTaskList.txt";
    private final Path path;
    static final String STORAGE_DIRECTORY = "./storage";
    public static final String MESSAGE_LOADED = "Storage File loaded";
    private static final String ERROR_SAVING = "Error saving to ";
    private static final String ERROR_LOADING = "Error loading to ";
    private static final String ERROR_PARSING = "Error parsing ";
    private static final String FILE_CREATED = "\nStorage directory and file created";


    
    public StorageFile(){
        path = Paths.get(STORAGE_FILE);
    }


    public void save(TaskList taskList) throws StorageException {
        try {
            List<String> encodedList = TaskListEncoder.encodeList(taskList);
            Files.write(path, encodedList);
        } catch (IOException e){ // Create file if there is no file
            File newfile = new File(STORAGE_DIRECTORY); 
            newfile.mkdir();
            save(taskList);
            throw new StorageException(ERROR_SAVING + path + FILE_CREATED);
        }
    }
    public TaskList load() throws StorageException {
        TaskList list = new TaskList();
        try{
            ArrayList<Task> decodedList = TaskListDecoder.decodeFile(STORAGE_FILE);
            list.data = decodedList;
            return list;
        } catch (IOException e){
            throw new StorageException(ERROR_LOADING + path);
        } catch (DecodeException e){
            throw new StorageException(ERROR_PARSING + path);
        }
    }




    public static class StorageException extends Exception {
        public StorageException(String message){
            super(message);
        }
    }
}
