package duke.ui;

import duke.common.Messages;
import duke.data.task.Task;
import duke.command.CommandResult;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.io.PrintStream;
import java.util.Scanner;


public class TextUi {

    /** Offset 0 index to 1 */
    public static final int DISPLAYED_INDEX_OFFSET = 1;
    /** Format of indexed list item */
    private static final String MESSAGE_INDEXED_LIST_ITEM = "\t%1$d. %2$s";

    public final Scanner input;
    public final PrintStream output;

    public TextUi() {
        this.input = new Scanner(System.in);
        this.output = System.out;
    }

    /** User Prompt */
    public static final String UI_PROMPT = ">>> ";

    public String getUserCommand() {
        output.print(UI_PROMPT);
        String userInput = input.nextLine();
        while (shouldIgnore(userInput)) {
            userInput = input.nextLine();
        }
        return userInput;
    }
    
    /** Ignore if empty line */
    private boolean shouldIgnore(String userInput) {
        return userInput.trim().isEmpty();
    }
    
    public void showToUser(String... messages) {
        for (String m : messages) {
            output.println(m);
        }
    }
    
    public void showToUserDivider(String... messages) {
        output.println(Messages.DIVIDER);
        showToUser(messages);
        output.println(Messages.DIVIDER);
    }


    public void showWelcomeMessage() {
        this.showToUserDivider(Messages.WELCOME);
    }

    public void showExitMessage() {
        this.showToUser(Messages.EXIT);
    }

    public void showUnknownMessage() {
        this.showToUserDivider(Messages.COMMAND_LISTS);
    }


    /** Show the result of the command to the user list of tasks involved and the message */
    public void showResultToUser(CommandResult result) {
        final Optional<List<? extends Task>> resultTasks = result.getTarget();
        showToUser(result.getMessage());
        if(resultTasks.isPresent()){
            showTaskListView(resultTasks.get());
        }
        output.println(Messages.DIVIDER);
    }

    /** Show a list of Tasks to the user, formatted as an indexed list */
    private void showTaskListView(List<? extends Task> tasks){
        List<String> taskToString = new ArrayList<>();
        for (Task task : tasks){
            taskToString.add(task.toString());
        }
        showToUserAsIndexedList(taskToString);
    }
    

    /** Shows a list of strings to the user, formatted as an indexed list. */
    private void showToUserAsIndexedList(List<String> list) {
        showToUser(getIndexedListForViewing(list));
    }

    /** Formats a list of strings as a viewable indexed list. */
    private static String getIndexedListForViewing(List<String> listItems) {
        final StringBuilder formatted = new StringBuilder();
        int displayIndex = 0 + DISPLAYED_INDEX_OFFSET;
        for (String listItem : listItems) {
            formatted.append(getIndexedListItem(displayIndex, listItem)).append("\n");
            displayIndex++;
        }
        return formatted.toString();
    }
    /**
     * Formats a string as a viewable indexed list item.
     *
     * @param visibleIndex visible index for this listing
     */
    private static String getIndexedListItem(int visibleIndex, String listItem) {
        return String.format(MESSAGE_INDEXED_LIST_ITEM, visibleIndex, listItem);
    }
}
