public class Duke {

    private UI ui;
    private List list;
    private static final String EXIT_PREFIX = "bye";
    private boolean isActive = true;

    public Duke() {
        ui = new UI();
        list = new List();
    }

    public void exit() {
        isActive = false;
    }

    public void run() {
        ui.greet();
        while (isActive) {
            String input = ui.getUserInput();
            Command command = CommandManager.manageCommand(input);
            if (command != null) {
                command.execute(list, ui);
            } else {
                ui.unrecognizable();
            }

            if (input.startsWith(EXIT_PREFIX)) {
                exit();
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
