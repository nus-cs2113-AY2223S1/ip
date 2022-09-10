package duke.task;

import duke.EmptyDescriptionException;
import duke.NoSpecficTimeException;
import duke.NoSpecificDeadlineException;
import duke.task.Task;

import java.io.FileWriter;
import java.io.IOException;

public class Deadline extends Task {
    private static String filePath = "test.txt";

    public Deadline(String task) throws EmptyDescriptionException, NoSpecificDeadlineException, NoSpecficTimeException {
     // Call constructor of super class and change formatting to braces
        super(task.replace("/by", "(by:") + ")");

    }

    @Override
    public void printTask(int index) {
        // Overridden method to print deadlines with [D]
        System.out.println((index + 1) + ". [D][" + getStatusIcon() + "] " + getTask());
    }

}
