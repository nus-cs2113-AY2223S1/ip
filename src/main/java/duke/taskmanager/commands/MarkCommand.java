package duke.taskmanager.commands;

import duke.UI;
import duke.exceptions.TaskOutOfBoundsException;
import duke.taskmanager.Storage;
import duke.taskmanager.TaskList;

public class MarkCommand extends Command {
    public MarkCommand(String userInput, String firstWord) {
        super(userInput, firstWord);
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        boolean isMarked = false;
        switch (firstWord) {
        case "mark":
            isMarked = true;
            break;
        case "unmark":
            isMarked = false;
            break;
        }
        mark(tasks,ui,userInput,isMarked);
    }

    private void mark(TaskList tasks, UI ui, String userInput, boolean done) {
        try {
            int startIdx = userInput.substring(0, userInput.indexOf(' ') + 1).length();
            int pos = Integer.parseInt(userInput.substring(startIdx));
            if (pos > tasks.size() || pos < 1) {
                throw new TaskOutOfBoundsException();
            }
            ui.printMark(tasks.get(pos), done);
        } catch (NumberFormatException e) {
            ui.printNumberFormatException();
        } catch (TaskOutOfBoundsException e) {
            ui.printTaskOutOfBoundsException();
        }
    }
}
