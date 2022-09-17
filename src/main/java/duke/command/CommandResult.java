package duke.command;


import java.util.List;

import duke.data.task.Task;

public class CommandResult {
    public CommandResult(String top) {
        this.messageTop = top;
    }

    public CommandResult(String top, List<Task> target, String bottom) {
        this.messageTop = top;
        this.messageBottom = bottom;
        this.target = target;
    }

    public String[] printTarget() {
        if (target == null) {
            return new String[]{};
        } else {
            return target.stream().map(Task::toString).toArray(String[]::new);
        }
    }

    public String messageTop = "";
    public String messageBottom = "";
    public List<Task> target = null;

}
