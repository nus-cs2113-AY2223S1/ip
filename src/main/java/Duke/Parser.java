package Duke;

public class Parser {
    public static String commandType (String command) {
        String[] arr = command.split(" ", 2);
        return arr[0];
    }

    //if command is more than 1 word
    public static String commandActual (String command) {
        String[] arr = command.split(" ", 2);
        if (arr.length > 1) {
            return arr[1];
        }
        return null;
    }

    public static Boolean isLast (String commandType) {
        return commandType == "bye" ? true : false;
    }

}
