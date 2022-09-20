package duke.task;

import duke.exception.EmptyDescriptionException;
import duke.exception.NoSpecficTimeException;
import duke.exception.NoSpecificDeadlineException;

import java.io.FileWriter;
import java.io.IOException;

public class Event extends Task {
    private static String filePath = "test.txt";

    public Event(String task) throws EmptyDescriptionException, NoSpecificDeadlineException, NoSpecficTimeException {
        // Call constructor for superclass and change formatting to brackets
        super(task.replace("/at", "(at:") + ")");
    }




}
