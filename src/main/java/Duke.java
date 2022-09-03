import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void printGreeting() {
        String logo = "    *******     *******   *******       **\n"
                + "    /**////**   **/////** /**////**     ****\n"
                + "    /**    /** **     //**/**   /**    **//**\n"
                + "    /**    /**/**      /**/*******    **  //**\n"
                + "    /**    /**/**      /**/**///**   **********\n"
                + "    /**    ** //**     ** /**  //** /**//////**\n"
                + "    /*******   //*******  /**   //**/**     /**\n"
                + "    ///////     ///////   //     // //      //\n";

        System.out.println("    ____________________________________________________________");
        System.out.println(logo + "\n    Kon'nichiwa! Doraemon desu.\n    What can I do for you?");
        System.out.println("    ____________________________________________________________");
    }

    public static void markDone(ArrayList<Task> tasks, String input, boolean isDone) {
        int taskIndex = Integer.parseInt(input) - 1;
        tasks.get(taskIndex).setStatus(isDone);

        System.out.println("    ____________________________________________________________");
        if (isDone) {
            System.out.println("    Subarashi! Good job in completing your task, Nobita:");
        } else {
            System.out.println("    Gambate Nobita, complete it soon! Don't procrastinate:");
        }
        System.out.println("    * " + tasks.get(taskIndex).getTaskDetails());
        System.out.println("    ____________________________________________________________");
    }

    public static void printNewTask(String taskDetails, int numberOfTasks) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Nobita, wake up. Here's your new task:");
        System.out.println("    * " + taskDetails);
        System.out.println("    Now you have " + numberOfTasks + " tasks in Doraemon's 4D pocket.");
        System.out.println("    ____________________________________________________________");
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
                System.out.println("    ____________________________________________________________");
                System.out.println("    Sayonara. Hope to see you again soon!");
                System.out.println("    ____________________________________________________________");
                isLoop = false;
                break;
            case "list":
                System.out.println("    ____________________________________________________________");
                System.out.println("    Here are the tasks stored in Doraemon's 4D pocket:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println("    " + (i + 1) + "." + tasks.get(i).getTaskDetails());
                }
                System.out.println("    ____________________________________________________________");
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
                System.out.println("    ____________________________________________________________");
                System.out.println("    Oh no, Doraemon didn't understand your command.");
                System.out.println("    ____________________________________________________________");
            }
        }
    }
}
