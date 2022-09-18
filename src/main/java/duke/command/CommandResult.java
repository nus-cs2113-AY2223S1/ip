package duke.command;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import duke.data.Messages;
import duke.data.TaskList;
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
        } else if (messageTop.equals(Messages.DIVIDER_LIST)) { /* Case of a List command */
            return IntStream.range(0, target.size())
                    .mapToObj(i -> {
                        return (i + Messages.OFFSET) + "." + target.get(i).toString();
                    })
                    .toArray(String[]::new);
        } else {
            return target.stream().map(Task::toString).toArray(String[]::new);
        }
    }


    public String messageTop = "";
    public String messageBottom = "";
    public List<Task> target = null;

}
