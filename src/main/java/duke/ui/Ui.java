package duke.ui;

import static duke.common.Constants.GREET_MESSAGE;
import static duke.common.Constants.LOGO;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {

    private final Scanner in;
    private final PrintStream out;

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public Ui() {
        this(System.in, System.out);
    }

    public void printLine(Object line) {
        out.println(line);
    }

    public void printLine() {
        out.println();
    }

    public String getNextCommand() {
        return in.nextLine();
    }

    public void printWelcomeMessage() {
        printLine(LOGO);
        printLine();
        printLine(GREET_MESSAGE);
        printLine();
    }
}
