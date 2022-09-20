package duke.storage;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

import duke.data.task.Deadline;
import duke.data.task.Event;
import duke.data.task.Task;
import duke.data.task.Todo;

import java.io.File;
import java.util.Scanner;

public class TaskListDecoder {
    private static final String PARSE_LIMITER = "\\|";

    public TaskListDecoder() {

    }

    public static ArrayList<? extends Task> decodeFile(Path path) throws DecodeException, IOException {
        ArrayList<Task> taskListData = new ArrayList<>();
        File file = new File(path.toString());
        Scanner fileRead = new Scanner(file);
        while (fileRead.hasNext()) {
            taskListData.add(decodeLine(fileRead.nextLine()));
        }
        fileRead.close();
        return taskListData;
    }

    // TODO:
    private static Task decodeLine(String line) throws DecodeException{
        try{
            Task toAdd;
            String[] parsed = line.split(PARSE_LIMITER);
            String commandWord = parsed[0];
            boolean isDone = Boolean.valueOf(parsed[1]);
            String description = parsed[2];       
            switch(commandWord.toLowerCase()){
            case Todo.TYPE_TODO:
                toAdd = new Todo(description);
                toAdd.setIsDone(isDone);
                return toAdd;
            case Event.TYPE_EVENT:
                toAdd = new Event(description, parsed[3]);
                toAdd.setIsDone(isDone);
                return toAdd;
            case Deadline.TYPE_DEADLINE:
                toAdd = new Event(description, parsed[3]);
                toAdd.setIsDone(isDone);
                return toAdd;
            default: 
                throw new DecodeException();
            }
        } catch (Exception e) {
        throw new DecodeException();
        }
    }

    public static class DecodeException extends Exception {
    }
}
