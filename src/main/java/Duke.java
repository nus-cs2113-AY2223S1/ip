import java.util.Scanner;

public class Duke {

    /**
     *
     */
    private static final int MAXLISTNUM = 100;
    private static Task[] taskList = new Task[MAXLISTNUM];
    private static int listLength = 0;

    public static void main(String[] args) {
        Util.showWelcomeMessage();
        handleUserInput();
    }

    private static void handleUserInput() {
        Scanner scanner = new Scanner(System.in);
        int index;
        String response;
        while (true) {
            String inputText = scanner.nextLine();
            while (inputText.trim().isEmpty())
                inputText = scanner.nextLine();
            final String[] split = inputText.trim().split("\\s+", 2);
            final String[] commandTypeAndParams = split.length == 2 ? split : new String[] { split[0], "" };
            final String commandType = commandTypeAndParams[0];
            final String commandArgs = commandTypeAndParams[1];
            switch (commandType) {
            case "todo":
                listLength++;
                taskList[listLength] = new Todo(commandArgs);
                response = taskList[listLength].getResponse();
                Util.printTaskResponse(response, listLength);
                break;
            case "deadline":
                listLength++;
                taskList[listLength] = new Deadline(commandArgs);
                response = taskList[listLength].getResponse();
                Util.printTaskResponse(response, listLength);
                break;
            case "event":
                listLength++;
                taskList[listLength] = new Event(commandArgs);
                response = taskList[listLength].getResponse();
                Util.printTaskResponse(response, listLength);
                break;
            case "list":
                Util.printSplitLine();
                System.out.println("     Here are the tasks in your list:");
                for (int i = 1; i <= listLength; i++) {
                    System.out.println("     " + String.valueOf(i) + "." + taskList[i].getResponse());
                }
                Util.printSplitLine();
                System.out.println();
                break;
            case "mark":
                index = Integer.valueOf(commandArgs);
                taskList[index].setStringState(true);
                Util.printSplitLine();
                System.out.println("    Nice! I've marked this task as done:");
                System.out.println("     "+taskList[index].getResponse());
                Util.printSplitLine();
                break;
            case "unmark":
                index = Integer.valueOf(commandArgs);
                taskList[index].setStringState(false);
                Util.printSplitLine();
                System.out.println("    OK, I've marked this task as not done yet:");
                System.out.println("     "+taskList[index].getResponse());
                Util.printSplitLine();
                break;
            case "bye":
                System.out.println("    Bye. Hope to see you again soon!");
                Util.printSplitLine();
                System.exit(0);
            default:
                taskList[listLength] = new Task(inputText);
                listLength++;
                Util.printSplitLine();
                System.out.println("    " + "added: " + inputText);
                Util.printSplitLine();
                System.out.println();
                break;
            }
        }
    }
}
