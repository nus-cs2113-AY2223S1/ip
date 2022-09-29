import java.util.Scanner;

/**
 * The entrance of the program
 */
public class Duke {
    static TaskManager taskManager = new TaskManager();
    static Storage record = new Storage();
    static boolean isProgramEnd = false;

    public static void main(String[] args) {
        Ui.showWelcomeMessage();
        record.restoreFromDisk(taskManager);
        handleUserInput();
    }

    private static void handleUserInput() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String inputText = scanner.nextLine();
            while (inputText.trim().isEmpty())
                inputText = scanner.nextLine();
            try {
                isProgramEnd = taskManager.handleTask(inputText);
            } catch (DukeException e) {
                e.handleError();
            }
            if (isProgramEnd) {
                System.exit(0);
            }
        }
    }
}
