import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        String input;
        Task[] tasks = new Task[100];
        Scanner inputScanner = new Scanner(System.in);
        input = inputScanner.nextLine();
        while (!input.equals("bye")) {
            String[] inputArray = input.split(" ");
            if (inputArray[0].equals("list")) {
                for (int i = 0; i < Task.getTaskCount(); i++) {
                    System.out.printf("%d.%s" + System.lineSeparator() ,i + 1,tasks[i].getTaskInfo());
                }
            } else if (inputArray[0].equals("mark")) {
                Task targetTask = tasks[Integer.parseInt(inputArray[1]) - 1];
                targetTask.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.printf("  %s" + System.lineSeparator(), targetTask.getTaskInfo());
            } else if (inputArray[0].equals("unmark")) {
                Task targetTask = tasks[Integer.parseInt(inputArray[1]) - 1];
                targetTask.markAsUnDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.printf("  %s" + System.lineSeparator(), targetTask.getTaskInfo());

            }  else if (inputArray[0].equals("todo")) {
                String description = separateInputString(input);
                Todo newTodo = new Todo(description);
                tasks[Task.getTaskCount() -1] = newTodo;
                newTodo.echo();
            }
            else if (inputArray[0].equals("deadline")) {
                String deadlineDetail = separateInputString(input);
                int separatorIndex = deadlineDetail.indexOf("/by");
                String description = deadlineDetail.substring(0,separatorIndex);
                String by = deadlineDetail.substring(separatorIndex + 3).trim();
                Deadline newDeadline = new Deadline(description, by);
                tasks[Task.getTaskCount() - 1] = newDeadline;
                newDeadline.echo();
            }else if (inputArray[0].equals("event")) {
                String deadlineDetail = separateInputString(input);
                int separatorIndex = deadlineDetail.indexOf("/at");
                String description = deadlineDetail.substring(0,separatorIndex);
                String time = deadlineDetail.substring(separatorIndex + 3).trim();
                Event newEvent = new Event(description, time);
                tasks[Task.getTaskCount() - 1] = newEvent;
                newEvent.echo();
            } else {
                Task newTask = new Task(input);
                tasks[Task.getTaskCount() - 1] = newTask;
                newTask.echo();
            }
            input = inputScanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static String separateInputString(String input) {
        int indexOfSeparator = input.indexOf(" ");
        return input.substring(indexOfSeparator + 1);
    }
}
