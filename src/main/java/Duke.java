import java.util.Scanner;

public class Duke {
    static TaskManager taskManager = new TaskManager();
    static boolean isProgramEnd = false;

    public static void main(String[] args) {
        Util.showWelcomeMessage();
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
