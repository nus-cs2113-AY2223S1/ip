package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import duke.command.FileManager;
import duke.command.InputManager;

public abstract class Duke {
    public static final String HORIZONTAL_LINE = "______________________________";

    private static void printIntroduction() {
        final String INTRODUCTION =
                "Hihi, my name is Jay!" + System.lineSeparator() + "What can I do for you today?";

        System.out.println(HORIZONTAL_LINE);
        System.out.println(INTRODUCTION);
        System.out.println(HORIZONTAL_LINE);
        System.out.println();
    }

    private static void printExit() {
        final String EXIT = "Goodbye! Hope to see you again!";

        System.out.println(HORIZONTAL_LINE);
        System.out.println(EXIT);
        System.out.println(HORIZONTAL_LINE);
        System.out.println();
    }

    public static void main(String[] args) {
        final String FILE_PATH = "ip/data/duke.txt";

        try {
            FileManager.readFile(FILE_PATH);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        printIntroduction();
        InputManager.readInputs();
        printExit();

        try {
            FileManager.writeFile(FILE_PATH);
        } catch (IOException e) {
            System.out.println("Unable to save");
        }
    }
}
