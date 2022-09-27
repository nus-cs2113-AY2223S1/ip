package duke.commands;

import duke.ui.Ui;
import duke.TaskList;
import duke.task.Task;
import duke.Storage;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";

    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

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