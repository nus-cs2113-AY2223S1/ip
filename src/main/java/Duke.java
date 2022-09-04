import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static final String DASH = "    ____________________________________________________________";

    public static void printGreeting() {
        String logo = "    *******     *******   *******       **\n"
                + "    /**////**   **/////** /**////**     ****\n"
                + "    /**    /** **     //**/**   /**    **//**\n"
                + "    /**    /**/**      /**/*******    **  //**\n"
                + "    /**    /**/**      /**/**///**   **********\n"
                + "    /**    ** //**     ** /**  //** /**//////**\n"
                + "    /*******   //*******  /**   //**/**     /**\n"
                + "    ///////     ///////   //     // //      //\n";

        System.out.println(String.format("%s\n%s\n%s\n%s", DASH, logo, InfoText.INFO_WELCOME, DASH));
    }

    public static void markDone(ArrayList<Task> tasks, String input, boolean isDone) {
        System.out.println(DASH);
        try {
            int taskIndex = Integer.parseInt(input) - 1;
            tasks.get(taskIndex).setStatus(isDone);
            if (isDone) {
                System.out.println(String.format("%s", InfoText.INFO_TASK_MARKED));
            } else {
                System.out.println(String.format("%s", InfoText.INFO_TASK_UNMARKED));
            }
            System.out.println(String.format("    * %s", tasks.get(taskIndex).getTaskDetails()));
        } catch (NumberFormatException e) {
            System.out.println(String.format("%s", ErrorText.ERROR_INVALID_STATUS_FORMAT));
        } catch (IndexOutOfBoundsException e) {
            System.out.println(String.format("%s", ErrorText.ERROR_INVALID_STATUS_TASK));
        }
        System.out.println(DASH);
    }

    public static void printNewTask(String taskDetails, int numberOfTasks) {
        System.out.println(DASH);
        System.out.println(String.format("%s", InfoText.INFO_TASK_ADDED));
        System.out.println(String.format("    * %s", taskDetails));
        System.out.println(String.format("%s", String.format(String.valueOf(InfoText.INFO_TASK_COUNT), numberOfTasks)));
        System.out.println(DASH);
    }

    public static void main(String[] args) {
        printGreeting();
        boolean isLoop = true;
        Scanner in = new Scanner(System.in);
        String command, input;
        String[] inputSplits;
        ArrayList<Task> tasks = new ArrayList<>();

        while (isLoop) {
            input = in.nextLine();
            if (input.contains(" ")) {
                command = input.split(" ")[0];
                input = input.substring(input.indexOf(' ') + 1);
            } else {
                command = input;
            }

            switch (command) {
            case "bye":
                System.out.println(String.format("%s\n%s\n%s", DASH, InfoText.INFO_WELCOME, DASH));
                isLoop = false;
                break;
            case "list":
                System.out.println(DASH);
                System.out.println(String.format("%s", InfoText.INFO_LIST));
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println("    " + (i + 1) + "." + tasks.get(i).getTaskDetails());
                }
                System.out.println(DASH);
                break;
            case "mark":
                markDone(tasks, input, true);
                break;
            case "unmark":
                markDone(tasks, input, false);
                break;
            case "todo":
                if (input.equals("todo") || input.isBlank()) {
                    System.out.println(String.format("%s\n%s\n%s", DASH, ErrorText.ERROR_INVALID_TODO_FORMAT, DASH));
                    break;
                }
                Todo todo = new Todo(input);
                tasks.add(todo);
                printNewTask(todo.getTaskDetails(), tasks.size());
                break;
            case "deadline":
                try {
                    inputSplits = input.split(" /by ");
                    Deadline deadline = new Deadline(inputSplits[0], inputSplits[1]);
                    tasks.add(deadline);
                    printNewTask(deadline.getTaskDetails(), tasks.size());
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(String.format("%s\n%s\n%s", DASH, ErrorText.ERROR_INVALID_DEADLINE_FORMAT, DASH));
                }
                break;
            case "event":
                try {
                    inputSplits = input.split(" /at ");
                    Event event = new Event(inputSplits[0], inputSplits[1]);
                    tasks.add(event);
                    printNewTask(event.getTaskDetails(), tasks.size());
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(String.format("%s\n%s\n%s", DASH, ErrorText.ERROR_INVALID_EVENT_FORMAT, DASH));
                }
                break;
            default:
                System.out.println(String.format("%s\n%s\n%s", DASH, ErrorText.ERROR_INVALID_COMMAND, DASH));
                break;
            }
        }
    }
}
