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

    public static Command getCommand(String line) {
        Command command;
        if (line.equals("bye")) {
            command = Command.BYE;
        } else if (line.equals("list")) {
            command = Command.LIST;
        } else if (line.startsWith("mark ")) {
            command = Command.MARK;
        } else if (line.startsWith("unmark ")) {
            command = Command.UNMARK;
        } else if (line.startsWith("todo ")) {
            command = Command.TODO;
        } else if (line.startsWith("deadline ")) {
            command = Command.DEADLINE;
        } else if (line.startsWith("event ")) {
            command = Command.EVENT;
        } else {
            command = Command.EMPTY;
        }
        return command;
    }

    public static void markDone(ArrayList<Task> tasks, String line, boolean isDone) {
        int taskIndex = Integer.parseInt(line.replaceAll("[^0-9]", "")) - 1;
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
        String line, newLine, description;
        Scanner in = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        while (isLoop) {
            line = in.nextLine();
            Command command = getCommand(line);

            switch (command) {
            case BYE:
                System.out.println("    ____________________________________________________________");
                System.out.println("    Sayonara. Hope to see you again soon!");
                System.out.println("    ____________________________________________________________");
                isLoop = false;
                break;
            case LIST:
                System.out.println("    ____________________________________________________________");
                System.out.println("    Here are the tasks stored in Doraemon's 4D pocket:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println("    " + (i + 1) + "." + tasks.get(i).getTaskDetails());
                }
                System.out.println("    ____________________________________________________________");
                break;
            case MARK:
                markDone(tasks, line, true);
                break;
            case UNMARK:
                markDone(tasks, line, false);
                break;
            case TODO:
                Todo todo = new Todo(line.substring(5));
                tasks.add(todo);
                printNewTask(todo.getTaskDetails(), tasks.size());
                break;
            case DEADLINE:
                newLine = line.substring(9);
                description = newLine.split(" /by ")[0];
                String dueBy = newLine.split(" /by ")[1];
                Deadline deadline = new Deadline(description, dueBy);
                tasks.add(deadline);
                printNewTask(deadline.getTaskDetails(), tasks.size());
                break;
            case EVENT:
                newLine = line.substring(6);
                description = newLine.split(" /at ")[0];
                String startTime = (newLine.split(" /at ")[1]).split("-")[0];
                String endTime = (newLine.split(" /at ")[1]).split("-")[1];
                Event event = new Event(description, startTime, endTime);
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
