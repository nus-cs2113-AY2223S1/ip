import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static final String DASH = "    ____________________________________________________________";
    static final String INDENT = "    ";

    public static void printGreeting() {
        String logo = "    *******     *******   *******       **\n"
                + "    /**////**   **/////** /**////**     ****\n"
                + "    /**    /** **     //**/**   /**    **//**\n"
                + "    /**    /**/**      /**/*******    **  //**\n"
                + "    /**    /**/**      /**/**///**   **********\n"
                + "    /**    ** //**     ** /**  //** /**//////**\n"
                + "    /*******   //*******  /**   //**/**     /**\n"
                + "    ///////     ///////   //     // //      //\n";

        System.out.println(DASH);
        System.out.println(logo + "\n" + INDENT + "Kon'nichiwa! Doraemon desu.\n" + INDENT + "What can I do for you?");
        System.out.println(DASH);
    }

    public static void markDone(ArrayList<Task> tasks, String input, boolean isDone) {
        System.out.println(DASH);
        try {
            int taskIndex = Integer.parseInt(input) - 1;
            tasks.get(taskIndex).setStatus(isDone);
            if (isDone) {
                System.out.println(INDENT + "Subarashi! Good job in completing your task, Nobita:");
            } else {
                System.out.println(INDENT + "Gambate Nobita, complete it soon! Don't procrastinate:");
            }
            System.out.println(INDENT + "* " + tasks.get(taskIndex).getTaskDetails());
        } catch (NumberFormatException e) {
            System.out.println(INDENT + "Nobita, the format is: mark <taskIndex> or unmark <taskIndex>, e.g. mark 1.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println(INDENT + "Nobita, task " + input + " does not exist in Doramon's 4D pocket.");
        }
        System.out.println(DASH);
    }

    public static void printNewTask(String taskDetails, int numberOfTasks) {
        System.out.println(DASH);
        System.out.println(INDENT + "Nobita, wake up. Here's your new task:");
        System.out.println(INDENT + "* " + taskDetails);
        System.out.println(INDENT + "Now you have " + numberOfTasks + " tasks in Doraemon's 4D pocket.");
        System.out.println(DASH);
    }

    public static void main(String[] args) {
        printGreeting();
        boolean isLoop = true;
        String command, input;
        String[] inputSplits;
        Scanner in = new Scanner(System.in);
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
                System.out.println(DASH);
                System.out.println(INDENT + "Sayonara. Hope to see you again soon!");
                System.out.println(DASH);
                isLoop = false;
                break;
            case "list":
                System.out.println(DASH);
                System.out.println(INDENT + "Here are the tasks stored in Doraemon's 4D pocket:");
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
                Todo todo = new Todo(input);
                tasks.add(todo);
                printNewTask(todo.getTaskDetails(), tasks.size());
                break;
            case "deadline":
                inputSplits = input.split(" /by ");
                Deadline deadline = new Deadline(inputSplits[0], inputSplits[1]);
                tasks.add(deadline);
                printNewTask(deadline.getTaskDetails(), tasks.size());
                break;
            case "event":
                inputSplits = input.split(" /at ");
                Event event = new Event(inputSplits[0], inputSplits[1]);
                tasks.add(event);
                printNewTask(event.getTaskDetails(), tasks.size());
                break;
            default:
                System.out.println(DASH);
                System.out.println(INDENT + "Oh no, Doraemon didn't understand your command.");
                System.out.println(DASH);
            }
        }
    }
}
