package duke;

import duke.task.*;
import duke.command.*;

import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static final int MAX_NUMBER_OF_TASKS = 100;

    public static String truncateInput(String[] s) {
        String[] copy = Arrays.copyOfRange(s, 1, s.length);
        return String.join(" ", copy);
    }

    public static void main(String[] args) {
        // prints hello greeting
        Response.printGreetings("hello");

        String inputs;
        boolean systemState = true;
        Task[] tasks = new Task[MAX_NUMBER_OF_TASKS];
        Scanner in = new Scanner(System.in);

        while (systemState) {
            inputs = in.nextLine();
            String[] words = inputs.split(" ");
            String keyword = words[0].toLowerCase();
            // previous line fixes any problem of upper/lower case problems for the command keywords
            String remainingInput = truncateInput(words);
            // remainingInput is the input without the command keywords e.g. todo, list, mark...
            Response.printLines();
            Allocator.allocate(tasks, words, keyword, remainingInput);
            Response.printLines();
              // putting this switch statement into another function causes some variables to not be incremented

            if (keyword.equals("bye")) {
                systemState = false;
            }
        }
    }
}