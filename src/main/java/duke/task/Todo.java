package duke.task;

import duke.EmptyDescriptionException;
import duke.task.Task;

import java.io.FileWriter;
import java.io.IOException;

public class  Todo extends Task {
    private static String filePath = "test.txt";

    public Todo(String task) throws EmptyDescriptionException, IOException {
        // Call constructor of superclass and print ou task added
        super(task);
    }

    @Override
    public void printTask(int index) {
        //Overridden method to print [T] for todo task
        System.out.println((index + 1) + ". [T][" + getStatusIcon() + "] " + getTask());
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
