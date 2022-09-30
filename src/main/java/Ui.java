
/**
 * Ui class, has the primary user output that main class prints out
 * and is used to call the parse class in order to start analyzing
 * the user input
 */
public class Ui {
    /**
     * Prints out the logo of duke and prompts the user to input something
     */
    public static void intro(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you");
    }

    /**
     * Starts parsing the user input one the task list is designated from the
     * pre-existing file
      * @param tasks task list, either empty or inputted from file
     */
    public static void parseInput(TaskList tasks){
        Parser parser = new Parser(tasks);
        parser.parse();
    }
}
