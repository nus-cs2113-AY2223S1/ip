import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Duke {
    public static ArrayList<Task> list = new ArrayList<Task>();

    public static void handleInput() {
        String input;
        Scanner in = new Scanner(System.in);
        input = in.nextLine();
        String[] line = input.split(" ");

        if (line[0].equals("bye")) {
            return;
        } else {
            switch (line[0]) {
            case "list":
                System.out.println("        ____________________________________________");
                for (int i = 1; i <= list.size(); i += 1) {
                    String markDoneStatus = list.get(i-1).isDone()?"[X]":"[ ]";
                    System.out.println("        " + markDoneStatus + " " + i + ". " + list.get(i-1).getTaskName());
                }
                System.out.println("        ____________________________________________");
                break;

            case "mark":
                Task tempTaskDone = new Task(list.get(Integer.parseInt(line[1])-1).getTaskName(), list.get(Integer.parseInt(line[1])-1).isDone());
                tempTaskDone.setDone(true);
                list.set(Integer.parseInt(line[1])-1, tempTaskDone);

                String markDone = list.get(Integer.parseInt(line[1])-1).isDone()?"[X]":"[ ]";
                System.out.println("        ____________________________________________");
                System.out.println("        Nice! I've marked this task as done:");
                System.out.println("            " + markDone + " " + list.get(Integer.parseInt(line[1])-1).getTaskName());
                System.out.println("        ____________________________________________");
                break;

            case "unmark":
                Task tempTaskNotDone = new Task(list.get(Integer.parseInt(line[1])-1).getTaskName(), list.get(Integer.parseInt(line[1])-1).isDone());
                tempTaskNotDone.setDone(false);
                list.set(Integer.parseInt(line[1])-1, tempTaskNotDone);

                String unmarkDone = list.get(Integer.parseInt(line[1])-1).isDone()?"[X]":"[ ]";
                System.out.println("        ____________________________________________");
                System.out.println("        Ok. I've marked this task as not done yet:");
                System.out.println("            " + unmarkDone + " " + list.get(Integer.parseInt(line[1])-1).getTaskName());
                System.out.println("        ____________________________________________");
                break;

            default:
                list.add(new Task(Arrays.toString(line).replace(",", "").replace("[", "").replace("]", "")));
                System.out.println("        ____________________________________________");
                System.out.println("        " + Arrays.toString(line).replace(",", "").replace("[", "").replace("]", ""));
                System.out.println("        ____________________________________________");
                break;
            }

            handleInput();
        }

        return;
    }

    public static void main(String[] args) {
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
        handleInput();

        System.out.println("____________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________");
    }
}
