package duke.commands;

import duke.ui.Ui;
import duke.TaskList;
import duke.task.Task;
import duke.Storage;

/**
 * Finds task(s) with matching keyword from the task list.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";

    private final String keyword;

    /**
     * Constructs constructor for Find command which stores the keyword to be matched.
     *
     * @param keyword Keyword to be matched.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Checks for matching task descriptions with keyword when task list is non-empty and prints matching results.
     * Otherwise, ends function if list is empty.
     *
     * @param taskList Used to access taskList information.
     * @param ui Used to print matching result or empty list messages.
     * @param storage Used to update task information in duke.txt.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        int currentListSize = taskList.getCurrentListSize();
        boolean hasNoTask = (currentListSize == 0);
        if (hasNoTask) {
            ui.showEmptyListMessage();
            return;
        }

        StringBuilder matchingTaskDescriptions = new StringBuilder();
        int printIndex = 1;
        for (Task task : taskList.getTaskList()) {
            boolean hasKeyword = task.getDescription().contains(keyword);
            if (hasKeyword) {
                String taskDescription = printIndex + "." + task;
                matchingTaskDescriptions.append(taskDescription).append(System.lineSeparator());
                printIndex++;
            }
        }
        ui.printMatchingTaskMessage(matchingTaskDescriptions.toString().trim(), (printIndex - 1));
    }
}