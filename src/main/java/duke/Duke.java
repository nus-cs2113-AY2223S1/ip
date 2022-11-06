package duke;


/**
 * Represents a personal assistant chat-bot that helps a person to keep track of various things.
 * Start the application and interact with the user.
 */
public class Duke {


    public static final String DEFAULT_DUKE_PATH = "data/duke.txt";

    private TaskList taskList;
    private final Ui ui;
    private  Storage storage;
    private Command command;
    private final Parser parser;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
        command = new Command(storage, ui, taskList, parser);
    }

    public static void main(String[] args) {
        new Duke(DEFAULT_DUKE_PATH).run();
    }

    /**
     * Runs the application until the application terminates
     */
    private void run() {
        ui.showWelcomeMessage();
        while (true) {
            try {
                String input = ui.readCommand();
                String[] CommandTypeAndArguments = parser.splitCommandTypeAndArguments(input);
                String feedback = command.execute(CommandTypeAndArguments);
                ui.showFeedbackToUser(feedback);
            }  catch (InvalidCommandException | EmptyDescriptionException | DukeException e) {
                ui.showFeedbackToUser(e.getMessage());
            }
        }
    }

}
