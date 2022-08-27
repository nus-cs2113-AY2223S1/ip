import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");

        String line;
        Scanner in = new Scanner(System.in);
        Task[] taskList = new Task[100];
        request:
        while(true){
            line = in.nextLine();
            if(Objects.equals(line, "list")) {
                System.out.println("    ____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < Task.getNumberOfTasks(); i++) {
                    taskList[i].printTask();
                }
                System.out.println("    ____________________________________________________________");
            } else if (Objects.equals(line, "blah")) {
                System.out.println("    ____________________________________________________________");
                System.out.println("     blah");
                System.out.println("    ____________________________________________________________");
            } else if(Objects.equals(line, "bye")) {
                System.out.println("    ____________________________________________________________");
                System.out.println("     Bye. Hope to see you again soon!");
                System.out.println("    ____________________________________________________________");
                break request;
            }
            else if (Objects.equals(line.split(" ")[0], "mark")) {
                int id = Integer.parseInt(line.split(" ")[1]) - 1;
                taskList[id].setDone();
                System.out.println("    ____________________________________________________________");
                System.out.println("     Nice! I've marked this task as done:");
                System.out.println("       [X] " + taskList[id].getDescription());
                System.out.println("    ____________________________________________________________");
            }
            else if (Objects.equals(line.split(" ")[0], "unmark")) {
                int id = Integer.parseInt(line.split(" ")[1]) - 1;
                taskList[id].setNotDone();
                System.out.println("    ____________________________________________________________");
                System.out.println("     OK, I've marked this task as not done yet:");
                System.out.println("       [] " + taskList[id].getDescription());
                System.out.println("    ____________________________________________________________");
            }
            else{
                taskList[Task.getNumberOfTasks()] = new Task(line);
                System.out.println("    ____________________________________________________________");
                System.out.println("     added: " + line);
                System.out.println("    ____________________________________________________________");
            }
        }
    }
}
