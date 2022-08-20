import java.util.Scanner;

public class Duke {

    /**
     * Formats messages to be printed on the terminal
     * @param input String Message to be formatted
     */
    public static void formatAndPrint(String input) {
        String output = "";
        String[] split = input.split("\n");

        System.out.println("    ____________________________________________________________");
        for (String string: split) {
            output = output.concat( "     " + string + "\n");
        }
        System.out.print(output);
        System.out.println("    ____________________________________________________________");
    }


    public static void main(String[] args) {
        String input, output;
        TaskManager taskManager = new TaskManager();
        Scanner sc = new Scanner(System.in);

        output = "Hello! I'm DuckyMoMo\n"
                + "What can I do for you?";
        formatAndPrint(output);

        input = sc.nextLine();
        while (true) {
            String[] arguments = input.split("[ \t]+");

            switch (arguments[0]) {
            case "bye":
                formatAndPrint("Byeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
                return;
            case "list":
                output = taskManager.getAllTasks();
                break;
            case "mark":
                output = taskManager.markTask(Integer.parseInt(arguments[1]));
                break;
            case "unmark":
                output = taskManager.unmarkTask(Integer.parseInt(arguments[1]));
                break;
            default:
                output = taskManager.addTask(input);
                break;
            }
            formatAndPrint(output);

            input = sc.nextLine();
        }

    }
}
