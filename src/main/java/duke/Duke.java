package duke;

import duke.command.Processor;
import duke.command.Response;
import duke.command.StringTools;

import java.util.Scanner;

public class Duke {
    public static final int MAX_NUMBER_OF_TASKS = 100;

    /* Possible type of exceptions:
        Wrong command
        Missing Arguments: description, time
        Too many arguments
        Wrong input
     */

    public static void main(String[] args) {

        // prints hello greeting
        Response.printGreetings("hello");

        Task[] tasks = new Task[MAX_NUMBER_OF_TASKS];
        Scanner in = new Scanner(System.in);

        boolean isDone = false;
        while (!isDone) {
            String input = in.nextLine();
            String[] splitInput = StringTools.splitString(input);
            String keyword = StringTools.returnKeyword(splitInput);
            if (keyword.equals("bye")) {
                isDone = true;
            }
            Processor.processInput(splitInput, keyword, tasks);
        }

    }
}