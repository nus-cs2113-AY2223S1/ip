package main.java;

/**
 * The Duke class is runs the overall program and is the main class that is run upon startup.
 */
public class Duke {
    /**
     * Main method that runs all of the methods and creates the objects used
     * @param args args
     */
    public static void main(String[] args) {
        Storage storage = new Storage();
        TaskList tasks = new TaskList(storage.fileToList());
        Ui ui = new Ui();
        ui.intro();
        ui.parseInput(tasks);
    }
}
