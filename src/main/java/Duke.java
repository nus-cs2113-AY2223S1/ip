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
                    System.out.printf("%d.[%s] %s" + System.lineSeparator() ,i + 1, tasks[i].getStatusIcon(),tasks[i].getDescription());
                }
            } else if (inputArray[0].equals("mark")) {
                Task targetTask = tasks[Integer.parseInt(inputArray[1]) - 1];
                targetTask.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.printf("  [%s] %s" + System.lineSeparator(), targetTask.getStatusIcon(), targetTask.getDescription());
            } else if (inputArray[0].equals("unmark")) {
                Task targetTask = tasks[Integer.parseInt(inputArray[1]) - 1];
                targetTask.markAsUnDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.printf("  [%s] %s" + System.lineSeparator(), targetTask.getStatusIcon(), targetTask.getDescription());

            } else {
                Task newTask = new Task(input);
                tasks[Task.getTaskCount() - 1] = newTask;
                System.out.println("added: " + input);
            }
            input = inputScanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
