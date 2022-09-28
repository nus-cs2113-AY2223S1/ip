package duke.ui;

import static duke.common.Constants.GREET_MESSAGE;
import static duke.common.Constants.LOGO;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {

    private final Scanner in;
    private final PrintStream out;

    /**
     * Creates a new Ui instance and specifies the streams it should receive input from and provide output to.
     *
     * @param in InputStream that will provide input from the user.
     * @param out Print where output will be written.
     */
    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Default constructor for Ui that reads input from {@link System#in} and outputs to {@link System#out}.
     */
    public Ui() {
        this(System.in, System.out);
    }

    /**
     * Outputs the provided string followed by a line separator.
     *
     * @param line Line to print
     */
    public void printLine(Object line) {
        out.println(line);
    }

    /**
     * Prints an empty line.
     */
    public void printLine() {
        out.println();
    }

    /**
     * Returns the next command that is input by the user.
     *
     * @return User command
     */
    public String getNextCommand() {
        return in.nextLine();
    }

    /**
     * Outputs the logo and welcome message.
     */
    public void printWelcomeMessage() {
        printLine(LOGO);
        printLine();
        printLine(GREET_MESSAGE);
        printLine();
    }
}
