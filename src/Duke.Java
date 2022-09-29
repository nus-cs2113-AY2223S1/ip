
public class Duke{
    private final Ui ui;
    private final TaskList taskList;
    private final Storage storage;
    private final Parser parse;
    public static final String BYE = "bye";
    public static final String DEFAULT_STORAGE_FILEPATH = "./data.txt";
    public static final int TASK = 0;
    private Duke() {
        ui = new Ui();
        ui.greet();
        parse = new Parser();
        storage = new Storage(DEFAULT_STORAGE_FILEPATH);
        taskList = new TaskList(storage.loadFileData());
        ui.promptMessage();
    }
    private void run() {
        String input = ui.userCommand().trim();
        while (!input.equals(BYE)) {
            String[] get = input.split(" ", 2);
            if (get.length == 1) {
                parse.singleCommand(get[TASK].toLowerCase(), taskList, ui);
                ui.promptMessage();
            }
            else {
                parse.multipleCommands(get, taskList, ui, storage);
                ui.promptMessage();
            }
            input = ui.userCommand().trim();

        }
        ui.exit();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
