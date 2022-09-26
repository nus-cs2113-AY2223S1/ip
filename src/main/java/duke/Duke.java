package duke;

import duke.commands.Command;

public class Duke {

    private Ui ui;
    private TaskList taskList;
    private Storage storage;
    private static final int NORMAL_EXIT_VALUE = 0;

    public Duke() {
        ui = new Ui();
        taskList = new TaskList();
        storage = new Storage();
        storage.loadData(taskList);
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            String input = ui.acceptAndValidateInput();
            Command command = Parser.parse(input);
            if (command != null) {
                command.execute(taskList, ui, storage);
                isExit = command.isExit();
            }
        }
        exit(0);
    }

    public static void exit(int status) {
        Ui.printLine();
        if (status == NORMAL_EXIT_VALUE) {
            System.out.println("\t" + "Bye. Hope to see you again soon!");
        } else {
            System.out.println("Terminating due to errors!");
        }
        Ui.printLine();
        System.exit(status);
    }
}
