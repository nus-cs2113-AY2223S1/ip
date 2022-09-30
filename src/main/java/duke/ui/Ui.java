package duke.ui;

import duke.DukeException;

import java.util.Scanner;

/**
 * Represents all interaction with the user.
 */
public class Ui {

    public final Scanner in = new Scanner(System.in);

    /**
     * Receives user's input through the command line.
     *
     * @returns the string that user have typed in the command line
     */
    public String userInput(){
        return in.nextLine();
    }


    /**
     * Print out the logo and brief introduction to greet the user.
     */
    public void greet() {
        String logo = "  ____                 _     \n" +
                " |  _ \\ ___  __ _  ___| |__  \n" +
                " | |_) / _ \\/ _` |/ __| '_ \\ \n" +
                " |  __/  __/ (_| | (__| | | |\n" +
                " |_|   \\___|\\__,_|\\___|_| |_|\n" +
                "                             ";

        String intro = "__________________________________________________ \n"
                + "  Hello! I'm Peach  \n"
                + "  What can I do for you? \n"
                + "__________________________________________________ \n";

        printOutputs(logo + "\n" + intro);
    }


    /**
     * Prints a dotted line to separate the sections.
     */
    public void printDashLine() {
        printOutputs("__________________________________________________ \n");
    }

    /**
     * Prints goodbye message.
     */
    public void printBye() {
        printOutputs("Bye. Hope to see you again soon! \n");
    }

    /**
     * Prints out statements in the command line.
     *
     * @param printStatement statement that the program wants to print out in the command line to interact with the
     * user.
     */
    public void printOutputs(String printStatement){
        System.out.println(printStatement);
    }
}
