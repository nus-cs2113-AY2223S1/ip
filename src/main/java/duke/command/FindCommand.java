package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {
    protected Ui ui = new Ui();
    protected ArrayList<Task> matchedTasks = new ArrayList<>();
    protected final int TASK_DETAIL = 1;
    protected int numOfTask = 0;
    protected String detail;
    protected boolean isDetailFound = false;
    public FindCommand(String[] splitCommand) {
        this.splitCommand = splitCommand;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> assignments = tasks.getAssignments();
        updateCountFromTasks(tasks);
        iterateTasks(assignments);
        getMatchDetails();
    }

    public void getMatchDetails() {
        numOfTask = matchedTasks.size();
        if(numOfTask == NO_TASK) {
            ui.showEmptyMatchList();
        } else {
            ui.showMatchListMessage();
            ui.showList(matchedTasks, numberOrder, numOfTask);
        }
    }

    private void iterateTasks(ArrayList<Task> assignments) {
        detail = splitCommand[TASK_DETAIL];
        for (int i = 0; i < countTask; i++) {
            isDetailFound = assignments.get(i).getDescription().contains(detail);
            addMatchTasks(assignments, i);
        }
    }

    private void addMatchTasks(ArrayList<Task> assignments, int i) {
        if (isDetailFound) {
            matchedTasks.add(assignments.get(i));
        }
    }
}
