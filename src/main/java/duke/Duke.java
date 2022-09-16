package duke;

/**
 * TO DO:
 * Update EXPECTED.TXT
 */

import duke.command.Command;
import duke.command.ExecutedCommand;
import duke.tasks.*;
import duke.parser.Parser;
import duke.ui.UI;

import java.util.Scanner;

public class Duke {

    /**
     * Greeting art
     */
    public static final String greetingArt = "placeholder for greeting art\n";

    public static final String goodbyeArt =
            "        .--'''''''''--.\n" +
                    "     .'      .---.      '.\n" +
                    "    /    .-----------.    \\\n" +
                    "   /        .-----.        \\\n" +
                    "   |       .-.   .-.       |\n" +
                    "   |      /   \\ /   \\      |\n" +
                    "    \\    | .-. | .-. |    /\n" +
                    "     '-._| | | | | | |_.-'\n" +
                    "         | '-' | '-' |\n" +
                    "          \\___/ \\___/\n" +
                    "       _.-'  /   \\  `-._\n" +
                    "     .' _.--|     |--._ '.\n" +
                    "     ' _...-|     |-..._ '\n" +
                    "            |     |\n" +
                    "            '.___.'\n" +
                    "              | |\n" +
                    "             _| |_\n" +
                    "            /\\( )/\\\n" +
                    "           /  ` '  \\";

    /**
     * Greeting and goodbye message constants
     */
    public static final String greetingMessage = "______________________________________\n" +
            "Hello! I'm Handsome!\n" +
            greetingArt +
            "How can I help you?\n" +
            "______________________________________\n";
    public static final String goodbyeMessage = "Bye. Hope to see you again soon!\n" + goodbyeArt;

    /**
     * Commands
     */
    public static final String BYE = "bye";


    /**
     * Helper function to format message to be printed
     */
    public static final String DIVIDER = "______________________________________";

    public static void printFormattedMessage(String message) {
        System.out.println(DIVIDER);
        System.out.println(message);
        System.out.println(DIVIDER);

    }

    /**
     * Prints greeting message
     */

    public static void printGreetingMessage() {
        System.out.println(greetingMessage);
    }

    /**
     * Prints exit message
     */
    public static void printGoodbyeMessage() {
        System.out.println(goodbyeMessage);
    }

    /**
     * New parser test
     */

    public static ExecutedCommand executeCommand(Command command) {
        try {
            return command.execute();
        } catch (Exception e) {
            return new ExecutedCommand(e.getMessage());
        }
    }

    public static void main(String[] args) {

        // Print greeting message
        UI.printGreeting();

        // Initialise instance of duke.tasks.TaskList
        TaskList taskList = new TaskList();

        System.out.println("Please enter your taskList command: (send 'bye' to exit)");
        Scanner in = new Scanner(System.in);


        /**
         while (true) {
         String userInput = in.nextLine();

         TaskList.parseInput(userInput);

         String[] parsedLine = userInput.split(" ", 2);

         if (parsedLine[0].equals(BYE)) {
         break;
         }
         } */

        while (true) {
            String userInput = in.nextLine();
            Command command = Parser.parsedCommand(taskList, userInput);

            ExecutedCommand executedCommand = executeCommand(command);

            printFormattedMessage(executedCommand.getExecutionMessage());

            if (userInput.equals(BYE)) {
                break;
            }
        }
        UI.printGoodbye();
    }

}

