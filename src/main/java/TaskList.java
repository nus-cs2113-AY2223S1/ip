public class TaskList {
    Task[] list = new Task[100];
    private int index = 0;

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
    public void unmarkStatus(String input) {
        String[] instructions = input.split(" ");
        int number = Integer.parseInt(instructions[1]);
        list[number-1].isDone = false;
        printLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  [ ] " + list[number-1].description);
        printLine();
    }

    public void markStatus(String input) {
        String[] instructions = input.split(" ");
        int number = Integer.parseInt(instructions[1]);
        list[number-1].isDone = true;
        printLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  [X] " + list[number-1].description);
        printLine();
    }



    public void printList() {
        printLine();
        for (int i = 0; i < index; i++) {
            String status = list[i].getStatusIcon();
            System.out.println(i + 1 + ".[" + status + "] " + list[i].description);
        }
        printLine();
    }

    public void start() {
        printLine();
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        printLine();
    }
    public void end() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }
    public void addTask(String input) {
        Task t = new Task(input);
        list[index] = t;
        index++;
        printLine();
        System.out.println("added: " + input);
        printLine();
    }
}
