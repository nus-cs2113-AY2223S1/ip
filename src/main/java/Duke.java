import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Duke {
    public static final int DEADLINE_STRING_LENGTH = 9;     // "deadline "
    public static final int BY_SEPARATOR_LENGTH = 4;        // "/by  "
    public static final int EVENT_STRING_LENGTH = 6;        // "event "
    public static final int AT_SEPARATOR_LENGTH = 4;        // "/at  "
    public static ArrayList<Task> list = new ArrayList<Task>();

    public static void printList(ArrayList<Task> list) {
            System.out.println("        ____________________________________________");
            for (int i = 1; i <= list.size(); i += 1) {
                String taskType = list.get(i-1).getTaskType();
                String markDoneStatus = list.get(i-1).isDone()?"[X]":"[ ]";

                switch(taskType) {
                case "ToDo":
                    System.out.println("        [T]" + markDoneStatus + " " + i + ". " + list.get(i-1).getTaskName());
                    break;

                case "Deadline":
                    String deadline = list.get(i-1).getTaskDeadline();
                    System.out.println("        [D]" + markDoneStatus + " " + i + ". " + list.get(i-1).getTaskName() +
                            " (by: " + deadline + ")");
                    break;

                case "Event":
                    String eventTime = list.get(i-1).getEventTime();
                    System.out.println("        [E]" + markDoneStatus + " " + i + ". " + list.get(i-1).getTaskName() +
                            " (at: " + eventTime + ")");
                    break;

                default:
                    break;
                }
            }
            System.out.println("        ____________________________________________");
    }

    private static void printGoodbyeMessage() {
        System.out.println("____________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________");
    }

    private static void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________");
        System.out.println("Hello! I'm Duke.");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________");
    }

    public static void handleInput() throws InvalidCommandException {
        String input = "";
        Scanner in = new Scanner(System.in);
        input = in.nextLine();
        String[] line = input.split(" ");
        boolean checkedInitialLine = false;

        while (!line[0].equals("bye")) {
            if (checkedInitialLine) {
                input = in.nextLine();
                line = input.split(" ");
            } else {
                checkedInitialLine = true;
            }

            switch (line[0]) {
            case "list":
                printList(list);
                break;

            case "mark":
                list.get(Integer.parseInt(line[1])-1).setDone(true);

                String markDone = list.get(Integer.parseInt(line[1])-1).isDone()?"[X]":"[ ]";
                System.out.println("        ____________________________________________");
                System.out.println("        Nice! I've marked this task as done:");
                System.out.println("            " + markDone + " " +
                        list.get(Integer.parseInt(line[1])-1).getTaskName());
                System.out.println("        ____________________________________________");
                break;

            case "unmark":
                list.get(Integer.parseInt(line[1])-1).setDone(false);

                String unmarkDone = list.get(Integer.parseInt(line[1])-1).isDone()?"[X]":"[ ]";
                System.out.println("        ____________________________________________");
                System.out.println("        Ok. I've marked this task as not done yet:");
                System.out.println("            " + unmarkDone + " " +
                        list.get(Integer.parseInt(line[1])-1).getTaskName());
                System.out.println("        ____________________________________________");
                break;

            case "todo":
                try {
                    if (line.length == 1) {
                        throw new InvalidTodoCommandException();
                    }

                    String[] todoName = Arrays.copyOfRange(line, 1, line.length);
                    list.add(new ToDo(Arrays.toString(todoName).replace(",", "")
                            .replace("[", "").replace("]", "")));

                    System.out.println("        ____________________________________________");
                    System.out.println("        Got it. I've added this task:");
                    System.out.println("        [T][ ] " + Arrays.toString(todoName).replace(",", "")
                            .replace("[", "").replace("]", ""));
                    System.out.println("        Now you have " + list.size() + " tasks in the list.");
                    System.out.println("        ____________________________________________");
                } catch (InvalidTodoCommandException e) {
                    System.out.println("OOPS!!! The description of a todo cannot be empty.");
                }
                break;

            case "deadline":
                int byIndex = input.indexOf("/by");
                String deadlineName = String.copyValueOf(input.toCharArray(), DEADLINE_STRING_LENGTH,
                        byIndex-1-DEADLINE_STRING_LENGTH);
                String taskDeadline = String.copyValueOf(input.toCharArray(), byIndex+ BY_SEPARATOR_LENGTH,
                        input.length()-byIndex-BY_SEPARATOR_LENGTH);
                list.add(new Deadline(deadlineName, taskDeadline));

                System.out.println("        ____________________________________________");
                System.out.println("        Got it. I've added this task:");
                System.out.println("        [D][ ] " + deadlineName + " (by: " + taskDeadline + ")");
                System.out.println("        Now you have " + list.size() + " tasks in the list.");
                System.out.println("        ____________________________________________");
                break;

            case "event":
                int atIndex = input.indexOf("/at");
                String eventName = String.copyValueOf(input.toCharArray(), EVENT_STRING_LENGTH,
                        atIndex-1- EVENT_STRING_LENGTH);
                String eventTime = String.copyValueOf(input.toCharArray(), atIndex+ AT_SEPARATOR_LENGTH,
                        input.length()-atIndex-AT_SEPARATOR_LENGTH);
                list.add(new Event(eventName, eventTime));

                System.out.println("        ____________________________________________");
                System.out.println("        Got it. I've added this task:");
                System.out.println("        [E][ ] " + eventName + " (at: " + eventTime + ")");
                System.out.println("        Now you have " + list.size() + " tasks in the list.");
                System.out.println("        ____________________________________________");
                break;

            default:
                try {
                    throw new InvalidCommandException();
                } catch (InvalidCommandException e) {
                    System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
        }
        return;
    }

    public static void main(String[] args) throws InvalidCommandException {
        printWelcomeMessage();
        handleInput();
        printGoodbyeMessage();
    }
}
