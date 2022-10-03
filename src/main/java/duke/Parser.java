package duke;

/**
 * Represents object to parse user input and decide on the appropriate action to be taken.
 * Requires class attributes TaskList and Ui to carry out commands stated by the User.
 */
public class Parser {
    private TaskList tasks;
    private Ui ui;

    public Parser(Ui ui, TaskList tasks) {
        this.ui = ui;
        this.tasks = tasks;
    }

    /**
     * Parses through the user input and decides on suitable action to take based on the first word of the user input.
     *
     * @param  lineInput String of user input obtained from the terminal
     */
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
        } else if (action.equalsIgnoreCase("find")) {
            tasks.findTasks(inputWords);
        } else {
            ui.showInvalidCommand();
        }
    }
}
