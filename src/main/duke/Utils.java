package main.duke;

import main.duke.exception.DukeException;

public class Utils {
    public static final String INDENT = "\n    ";
    public static final String H_LINE = INDENT +
            "------------------------------------------------";
    public static final int TASK_LIMIT = 100;
    private static int listIndex = 0;
    public static final String FOLDER_PATH = "./data";
    public static final String FILE_PATH = "./data/tasks.txt";

    /* Print introduction to Duke */
    public static void introduction() {
        String logo = "     ____        _        \n"
                + "    |  _ \\ _   _| | _____ \n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|\n";
        String introText = "\n    Hello! I'm Duke\n    What can I do for you?";
        String introduction = logo + H_LINE + introText + H_LINE;
        System.out.println(introduction);
    }

    /* Print a goodbye message from the Duke */
    public static void goodBye() {
        String goodByeText = "    Bye. Hope to see you again soon!" + H_LINE;
        System.out.print(goodByeText);
    }

    public static int getListIndex() {
        return listIndex;
    }

    public static void incrementListIndex() {
        listIndex++;
    }
    public static void decrementListIndex() {
        listIndex--;
    }

    public static int findNextLetter(String word, String input) throws DukeException {
        if (word.length() == input.length()) {
            throw new DukeException("You only wrote " + word + "! Please follow the correct format.");
        }
        int index = word.length();
        while (input.charAt(index) == ' ') {
            index++;
            if (index >= input.length()) {
                throw new DukeException("You only wrote spaces after " + word + "! Please follow the correct format.");
            }
        }
        return index;
    }
}
