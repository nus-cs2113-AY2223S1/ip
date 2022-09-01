import java.util.Objects;
import java.util.Scanner;
public class Duke {

    public static final int MAX_TASK = 100;

    public static void welcome() {
        Task.printHorizontalLine();
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        Task.printHorizontalLine();
    }

    public static void blah() {
        Task.printHorizontalLine();
        System.out.println("     blah");
        Task.printHorizontalLine();
    }

    public static void bye() {
        Task.printHorizontalLine();
        System.out.println("     Bye. Hope to see you again soon!");
        Task.printHorizontalLine();
    }

    public static void main(String[] args) {

        welcome();
        String line;
        Scanner in = new Scanner(System.in);
        Task[] tasks = new Task[MAX_TASK];

        request:
        while(true){
            line = in.nextLine();
            if(Objects.equals(line, "list")) {
                Task.printTaskList(tasks);
            }
            else if (Objects.equals(line, "blah")) {
                blah();
            }
            else if(Objects.equals(line, "bye")) {
                bye();
                break request;
            }
            else if (Objects.equals(line.split(" ")[0], "mark")) {
                int id = Integer.parseInt(line.split(" ")[1]) - 1;
                tasks[id].setDone();
                tasks[id].printMark();
            }
            else if (Objects.equals(line.split(" ")[0], "unmark")) {
                int id = Integer.parseInt(line.split(" ")[1]) - 1;
                tasks[id].setNotDone();
                tasks[id].printUnmark();
            }
            else if (Objects.equals(line.split(" ")[0], "todo")) {
                String description = line.replaceFirst("todo ", "");
                tasks[Todo.getNumberOfTasks()] = new Todo(description);
                int id = Todo.getNumberOfTasks() - 1;
                tasks[id].printNewTask();
            }
            else if (Objects.equals(line.split(" ")[0], "deadline")) {
                String[] DescriptionBy = line.replaceFirst("deadline ", "").split(" /by ");
                String description = DescriptionBy[0];
                String by = DescriptionBy[1];
                tasks[Deadline.getNumberOfTasks()] = new Deadline(description, by);
                int id = Task.getNumberOfTasks() - 1;
                tasks[id].printNewTask();
            }
            else if (Objects.equals(line.split(" ")[0], "event")) {
                String[] DescriptionAt = line.replaceFirst("event ", "").split(" /at ");
                String description = DescriptionAt[0];
                String at = DescriptionAt[1];
                tasks[Event.getNumberOfTasks()] = new Event(description, at);
                int id = Task.getNumberOfTasks() - 1;
                tasks[id].printNewTask();
            }
            else{
                tasks[Task.getNumberOfTasks()] = new Task(line);
                int id = Task.getNumberOfTasks() - 1;
                tasks[id].printNewTask();
            }
        }
    }
}
