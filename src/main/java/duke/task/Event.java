package duke.task;

import duke.EmptyDescriptionException;
import duke.NoSpecficTimeException;
import duke.NoSpecificDeadlineException;
import duke.task.Task;

import java.io.FileWriter;
import java.io.IOException;

public class Event extends Task {
    private static String filePath = "test.txt";

    public Event(String task) throws EmptyDescriptionException, NoSpecificDeadlineException, NoSpecficTimeException {
        // Call constructor for superclass and change formatting to brackets
        super(task.replace("/at", "(at:") + ")");
    }

    @Override
    public void printTask(int index) {
        // Overridden method to print [E] for event type of task
        System.out.println((index + 1) + ". [E][" + getStatusIcon() + "] " + getTask());
    }

    private static void appendToFile(String text) throws IOException {
        try {
            FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
            fw.write(text + System.lineSeparator());
            fw.close();
        } catch (IOException exception) {
            System.out.println("Error writing to file.");
        }
    }
}
