package duke.task;

import duke.exception.EmptyDescriptionException;
import duke.exception.NoSpecficTimeException;
import duke.exception.NoSpecificDeadlineException;

public class Deadline extends Task {
    private static String filePath = "test.txt";

    public Deadline(String task) throws EmptyDescriptionException, NoSpecificDeadlineException, NoSpecficTimeException {
     // Call constructor of super class and change formatting to braces
        super(task.replace("/by", "(by:") + ")");

    }

}
