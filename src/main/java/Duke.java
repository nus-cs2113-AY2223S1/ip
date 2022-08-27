public class Duke {
    /** Command list to check against */
    private static final String INPUT_TERMINATE = "bye";
    private static final String INPUT_LIST = "list";
    private static final String INPUT_MARK = "mark";
    private static final String INPUT_UNMARK = "unmark";

    /** ToDoList class that contains all items */
    private static final ToDoList toDoList = new ToDoList();

    public static void main(String[] args) {
        greet();
        programBody();
        exit();
    }

    /**
     * Body of the program. Runs in a loop until terminated.
     */
    public static void programBody() {
        while (true) {
            String input = UserInterface.getInput();
            if (isTerminatingInput(input)) {
                break;
            } else if (isListInput(input)) {
                UserInterface.print(toDoList.toString());
            } else if (isMarkInput(input)) {
                UserInterface.print("Marked task \""
                        + toDoList.getTextOfItem(markItem(input) - 1)
                        + "\" as done.");
            } else if (isUnmarkInput(input)) {
                UserInterface.print("Marked task \""
                        + toDoList.getTextOfItem(unmarkItem(input) - 1)
                        + "\" as not yet done.");
            } else {
                UserInterface.print("Added \""
                        + toDoList.getTextOfItem(toDoList.addItem(input))
                        + "\" to your list.");
            }
        }
    }

    /**
     * Checks if the keyword {@link Duke#INPUT_TERMINATE} is present at front of input string
     *
     * @param input input string to check
     * @return boolean, true or false
     */
    private static boolean isTerminatingInput(String input) {
        return input.equalsIgnoreCase(INPUT_TERMINATE);
    }

    /**
     * Checks if the keyword {@link Duke#INPUT_LIST} is present at front of input string
     *
     * @param input input string to check
     * @return boolean, true or false
     */
    private static boolean isListInput(String input) {
        return input.equalsIgnoreCase(INPUT_LIST);
    }

    /**
     * Checks if the keyword {@link Duke#INPUT_MARK} is present at front of input string
     *
     * @param input input string to check
     * @return boolean, true or false
     */
    private static boolean isMarkInput(String input) {
        return stringContains(input, INPUT_MARK);
    }

    /**
     * Marks item in To-Do list as done.
     * <b>NOTE: Operates on 1-based indexing logic</b>, but converts it
     * to 0-based indexing for {@link ToDoList} class
     *
     * @param input input string to find index
     * @return index of item <b>(1-based index)</b>
     */
    private static int markItem(String input) {
        int itemIndex = Integer.parseInt(input.split(" ")[1]) - 1;
        toDoList.markItem(itemIndex);
        return itemIndex + 1;
    }

    /**
     * Checks if the keyword {@link Duke#INPUT_UNMARK} is present at front of input string
     *
     * @param input input string to check
     * @return boolean, true or false
     */
    private static boolean isUnmarkInput(String input) {
        return stringContains(input, INPUT_UNMARK);
    }

    /**
     * Removes mark on item in To-Do list, marking it as undone.
     * <b>NOTE: Operates on 1-based indexing logic</b>, but converts it
     * to 0-based indexing for {@link ToDoList} class
     *
     * @param input input string to find index
     * @return index of item <b>(1-based index)</b>
     */
    private static int unmarkItem(String input) {
        int itemIndex = Integer.parseInt(input.split(" ")[1]) - 1;
        toDoList.unmarkItem(itemIndex);
        return itemIndex + 1;
    }

    /**
     * Prints a greeting for when the program is run
     */
    private static void greet() {
        UserInterface.print("Good morning!\nWhat would you like to do today?");
    }

    /**
     * Prints a goodbye message for when the program is terminated via user commands
     */
    private static void exit() {
        UserInterface.print("Alright, see you around then!");
    }

    /**
     * Check if the given input contains a substring at the start
     *
     * @param input     input to be checked
     * @param substring substring to be checked if it is contained in input
     * @return boolean true or false
     */
    private static boolean stringContains(String input, String substring) {
        return input.split(" ")[0].equalsIgnoreCase(substring);
    }
}
