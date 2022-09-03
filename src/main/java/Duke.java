import java.util.Scanner;

public class Duke {
    public static String[] splitInput(String userInput) {
        String[] split = userInput.split(" ", 2);
        if (split.length == 1) {
            return new String[]{split[0], ""};
        }
        return split;
    }

    public static void executeInstruction(Menu dukeMenu, String instruction, String inputValue) throws DukeException {
        switch (instruction) {
        case "list":
            dukeMenu.list();
            break;
        case "mark":
            dukeMenu.mark(inputValue);
            break;
        case "unmark":
            dukeMenu.unmark(inputValue);
            break;
        case "bye":
            dukeMenu.quit();
            break;
        case "todo":
            // Fallthrough
        case "deadline":
            // Fallthrough
        case "event":
            dukeMenu.addTask(instruction, inputValue);
            break;
        default:
            throw new InvalidCommandException();
        }
    }

    public static void safeExecuteInstruction(Menu dukeMenu, String instruction, String inputValue) {
        try {
            executeInstruction(dukeMenu, instruction, inputValue);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Menu dukeMenu = new Menu();
        String userInput = "", instruction = "", inputValue = "";
        Scanner in = new Scanner(System.in);

        dukeMenu.greet();
        while (!instruction.equals("bye")) {
            userInput = in.nextLine();
            String[] splits = splitInput(userInput);
            instruction = splits[0];
            inputValue = splits[1];
            safeExecuteInstruction(dukeMenu, instruction, inputValue);
        }
    }
}
