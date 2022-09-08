package duke.taskmanager.tasks;

public class Todo extends Task {
    public Todo(String command, Character lastChar) {
        int DescStartIdx = command.indexOf(' ') + 1;
        String description;
        if (lastChar.equals(' ')) {
            description = command.substring(DescStartIdx);
        } else {
            int DescEndIdx = command.indexOf('/');
            description = command.substring(DescStartIdx, DescEndIdx);
        }
        setDescription(description);
        setDone(false);
    }
}
