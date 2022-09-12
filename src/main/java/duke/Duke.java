package duke;

import duke.command.*;
import duke.task.Task;

import java.util.Scanner;

public class Duke {
    public static final int MAX_NUMBER_OF_TASKS = 100;

    public static void main(String[] args) {

        // prints hello greeting
        GenericPrint.printGreetings("hello");

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
            GenericPrint.printLines();
            ExceptionHandler.handleTooManyArguments(splitInput, keyword, tasks);
            GenericPrint.printLines();
        }
    }
}