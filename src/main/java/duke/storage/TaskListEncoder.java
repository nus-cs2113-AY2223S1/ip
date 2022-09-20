package duke.storage;

import duke.data.task.Task;
import duke.data.tag.TaskList;
import java.util.List;
import java.util.ArrayList;

public class TaskListEncoder {
    public static final String SEPARATOR = " | ";

    public static List<String> encodeList(TaskList list){
        final List<String> encodedTasks = new ArrayList<>();
        list.data.stream().forEach(task -> encodedTasks.add(encodeTask(task)));
        return encodedTasks;
    }

    private static String encodeTask(Task task){
        final StringBuilder encodedTaskBuilder = new StringBuilder();
        
        encodedTaskBuilder.append(task.getType());
        encodedTaskBuilder.append(SEPARATOR);
        encodedTaskBuilder.append(task.isDone()? "true" : "false");
        encodedTaskBuilder.append(SEPARATOR);
        encodedTaskBuilder.append(task.description.getData());
        encodedTaskBuilder.append(SEPARATOR);
        encodedTaskBuilder.append(task.date.isValid() ? task.date.getData() : "");
        return encodedTaskBuilder.toString();
    }
}
