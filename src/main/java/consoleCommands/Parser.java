package consoleCommands;
public class Parser {
    public Command Parse (String input) {
        String[] tempInput = input.split(" ", 2);
        if (tempInput.length == 2) {
            String command = tempInput[0];
            String arguments = tempInput[1];
            return new Command(command, arguments);
        }
        return new Command(input, "");
    }
}