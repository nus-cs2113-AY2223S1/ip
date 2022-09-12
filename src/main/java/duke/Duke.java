package duke;

import duke.command.Allocator;
import duke.command.ExceptionHandler;
import duke.command.Response;
import duke.command.StringTools;
import duke.task.Task;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static final int MAX_NUMBER_OF_TASKS = 100;

    /*  Not done yet!
    https://rollbar.com/blog/most-common-java-exceptions/
        Possible type of exceptions:
        Wrong command [NoSuchMethodException]
        Missing Arguments: description, time
        Too many arguments - all minus todo, deadline, event
        Wrong parameters - mark, unmark, [ArrayIndexOutOfBoundsException]
                         + [IllegalArgumentException]/[IllegalArgumentException]
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

            Response.printLines();
            ExceptionHandler.handleTooManyArguments(splitInput, keyword, tasks);
            Response.printLines();
        }

    }
}