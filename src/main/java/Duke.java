import java.util.Scanner;
import java.util.Arrays;
public class Duke {
    private static final String LOGO = "     ____.  _____ ______________   ____.___  _________\n"
            + "    |    | /  _  \\\\______   \\   \\ /   /|   |/   _____/\n"
            + "    |    |/  /_\\  \\|       _/\\   Y   / |   |\\_____  \\ \n"
            + "/\\__|    /    |    \\    |   \\ \\     /  |   |/        \\\n"
            + "\\________\\____|__  /____|_  /  \\___/   |___/_______  /\n"
            + "                 \\/       \\/                       \\/ \n";
    public static final String DASH_SEPARATOR = "------------------------------------------------\n";
    public static void formatOutput(String stringToOutput) {
        System.out.println(DASH_SEPARATOR + stringToOutput + DASH_SEPARATOR);
    }
    public static void greet() {
        String greet = "Hello! I'm\n" + LOGO + "What can I do for you?\n"
                + "Enter \"bye\" to exit.\n";
        formatOutput(greet);
    }
    public static void bye() {
        String bye = "Bye. Hope to see you again soon!\n";
        formatOutput(bye);
    }

    public static void main(String[] args) {
        greet();
        TaskManager manager = new TaskManager();
        manager.doTasks();
        bye();
    }
}
