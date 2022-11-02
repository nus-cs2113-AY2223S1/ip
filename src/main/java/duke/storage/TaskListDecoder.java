package duke.storage;

import java.io.IOException;
import java.util.ArrayList;

import duke.data.task.Deadline;
import duke.data.task.Event;
import duke.data.task.Task;
import duke.data.task.Todo;

import java.io.File;
import java.util.Scanner;

/**
 * Represent the class that handles decoding task from a line in the file and add Task to TaskList
 */

public class TaskListDecoder {
    private static final String PARSE_LIMITER = " \\| ";

    /**
     * Read the file path and add Task based on a line in the file
     *
     * @param path path to file
     * @return TaskList to be initiated
     */

    public static ArrayList<Task> decodeFile(String path) throws DecodeException, IOException {
        ArrayList<Task> taskListData = new ArrayList<>();
        File file = new File(path);
        Scanner fileRead = new Scanner(file);
        while (fileRead.hasNextLine()) {
            taskListData.add(decodeLine(fileRead.nextLine()));
        }
        fileRead.close();
        return taskListData;
    }

    private static Task decodeLine(String line) throws DecodeException {
        try {
            Task toAdd;
            String[] parsed = line.split(PARSE_LIMITER);
            String commandWord = parsed[0].trim();
            boolean isDone = Boolean.valueOf(parsed[1]);
            String description = parsed[2];
            switch (commandWord) {
            case Todo.TYPE_TODO:
                toAdd = new Todo(isDone, description);
                return toAdd;
            case Event.TYPE_EVENT:
                toAdd = new Event(isDone, description, parsed[3]);
                return toAdd;
            case Deadline.TYPE_DEADLINE:
                toAdd = new Deadline(isDone, description, parsed[3]);
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
