/**
 * Used for interacting with user input
 */
public class Ui {
    final String LINES = "____________________________________________________________";
    final String GREETING = "Hello, my name is Duke! \nWhat can I do for you?";
    final String BYE = "\tBye. Hope to see you soon!";

    /**
     * Print out greeting
     */
    public void greet() {
        System.out.println(GREETING);
    }

    /**
     * prints out bye format
     */
    public void sayBye() {
        printLines();
        System.out.println(BYE);
        printLines();
    }

    /**
     * prints out the lines
     */
    public void printLines() {
        System.out.println(LINES);
    }

    /**
     * prints out exception
     */
    public void printUiException () {
        printLines();
        System.out.println("\t Not sure what you meant, try again");
        printLines();
    }
}
