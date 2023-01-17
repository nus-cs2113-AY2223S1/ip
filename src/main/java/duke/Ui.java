package duke;

import java.util.Scanner;

/**
 * Represents all communications with user
 */
public class Ui {

    public Ui() {
        greet();
    }

    static Scanner in = new Scanner(System.in);

    /**
     * Prints line separator
     */
    public void line() {
        System.out.println("------------------------------");
    }

    /**
     * Prints welcome message
     */
    public void greet() {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        line();
        System.out.println("Hi! I'm Slave Kai, Zhou Zhou's 256IQ bot\nHow can I help you?");
        line();
    }

    /**
     * Reads user input from terminal
     *
     * @return user input
     */
    public String input() {
        return in.nextLine();
    }

    /**
     * Prints output to terminal
     *
     * @param output Varargs output for printing
     */
    public void output(String... output) {
        for (String line : output) {
            System.out.println(line);
        }
    }

}
