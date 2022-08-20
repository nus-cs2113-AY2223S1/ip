import java.util.Scanner;

public class Duke {

    public static void printWithFormat(String input) {
        String output = "";
        String[] split = input.split("\n");
        String line = "    ____________________________________________________________";
        System.out.println(line);
        for (String string: split) {
            output = output.concat( "     " + string + "\n");
        }
        System.out.print(output);
        System.out.println(line);
    }


    public static void main(String[] args) {
        String input, output;
        TaskManager taskManager = new TaskManager();
        Scanner sc = new Scanner(System.in);

        output = "Hello! I'm DuckyMoMo\n"
                + "What can I do for you?";
        printWithFormat(output);

        input = sc.nextLine();
        while (true) {
            String[] arguments = input.split("[ \t]+");

            switch (arguments[0]) {
            case "bye" :
                printWithFormat("Byeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
                return;
            case "list":
                output = taskManager.getTasks();
                break;
            case "mark":
                output = taskManager.markTask(Integer.parseInt(arguments[1]));
                break;
            case "unmark":
                output = taskManager.unmarkTask(Integer.parseInt(arguments[1]));
                break;
            default:
                output = taskManager.addTask(input);
            }
            printWithFormat(output);

            input = sc.nextLine();
        }

    }
}
