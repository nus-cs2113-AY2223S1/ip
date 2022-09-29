package duke;

public class Parser {
    private TaskList tasks;
    private Ui ui;

    public Parser(Ui ui, TaskList tasks) {
        this.ui = ui;
        this.tasks = tasks;
    }
    public void parse(String lineInput) {
        String[] inputWords = lineInput.split("\\s+");
        String action = inputWords[0];
        if (action.equalsIgnoreCase("list")) {
            ui.showTaskList(tasks.getTaskList());
        } else if (action.equalsIgnoreCase("mark")) {
            tasks.markTask(inputWords);
        } else if (action.equalsIgnoreCase("unmark")) {
            tasks.unmarkTask(inputWords);
        } else if (action.equalsIgnoreCase("todo")) {
            tasks.addToDo(inputWords);
        } else if (action.equalsIgnoreCase("event")) {
            tasks.addEvent(inputWords);
        } else if (action.equalsIgnoreCase("deadline")) {
            tasks.addDeadline(inputWords);
        } else if (action.equalsIgnoreCase("delete")) {
            tasks.deleteTask(inputWords);
        } else {
            ui.showInvalidCommand();
        }
    }
}
