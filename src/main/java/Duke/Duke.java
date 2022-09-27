package Duke;

import java.io.FileNotFoundException;


public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList taskList;
    private String filePath = "data/duke.txt";

    public Duke() {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList();
            storage.getFileContents(filePath, taskList.list);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            taskList = new TaskList();
        }
    }

    public void run() {
        Ui.sayHi();
        while (!taskList.isLast) {
            try {
                String userInput = Ui.readInputs();
                String commandType = Parser.commandType(userInput);
                String commandActual = Parser.commandActual(userInput);
                taskList.executeTask(commandType, commandActual, filePath);
            } catch (IllegalCommand e) {
                ui.showError();
            } finally {
                ui.drawLine();
            }
        }
        Ui.sayBye();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
