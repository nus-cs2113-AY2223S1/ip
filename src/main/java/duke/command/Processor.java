package duke.command;

import java.util.Scanner;
import duke.task.Task;

public class Processor {

    public static void processInput(String[] splitInput, String keyword, Task[] tasks) {

        Response.printLines();
        String description = StringTools.returnDescription(splitInput, keyword);
        String time = StringTools.returnTime(splitInput, keyword);
        int taskNumber = Task.getTaskNumber();
        Allocator.allocateForResponse(tasks, keyword, description, time, taskNumber);
        Response.printLines();

    }
}
