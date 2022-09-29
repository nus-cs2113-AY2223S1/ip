public class Parser {

    public static String parse(String command) {
        String[] items = command.split(" ");
        return items[0];
    }

    public static String parseDescription(String command) {
        return command.substring(command.indexOf(' ') + 1);
    }

    public static String parseDeadline(String command) {
        return command.substring(0, command.indexOf('/')) + "(by: " +command.substring(command.indexOf('/') + 1) + "）";
    }

    public static String parseEvent(String command) {
        return command.substring(0, command.indexOf('/')) + "（at: " +command.substring(command.indexOf('/') + 1) + "）";
    }

}
