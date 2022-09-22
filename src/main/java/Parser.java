public class Parser {

    public static String parse(String command) {
        String[] items = command.split(" ");
        return items[0];
    }

    public static String parseDescription(String command) {
        return command.substring(command.indexOf(' ') + 1);
    }


}
