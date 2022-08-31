import java.util.Scanner;

public class Duke {
    static final String LOGO = "   _____                .__   \n" +
            "  /  _  \\ ___  ___ ____ |  |  \n" +
            " /  /_\\  \\\\  \\/  // __ \\|  |  \n" +
            "/    |    \\>    <\\  ___/|  |__\n" +
            "\\____|__  /__/\\_ \\\\___  >____/\n";
    public static void greetUser() {
        String greeting = "____________________________________________________________\n"
        + "Hello! My name is Axel. :-)\n"
        + "How may I help you today?\n"
        + "____________________________________________________________";
        System.out.println(greeting);
    }

    public static void sayByeToUser() {
        String message = "____________________________________________________________\n"
        + "Goodbye. Hope to see you again soon!\n"
        + "____________________________________________________________";
        System.out.println(message);
    }

    public static void main(String[] args) {
        System.out.println(LOGO);
        greetUser();
        Scanner input = new Scanner(System.in);
        TaskManager TaskManager1 = new TaskManager();
        boolean isProgramFinished = false; //variable to indicate if the program should be terminated
        while(!isProgramFinished) {
            String curr = input.nextLine();
            if(curr.equals("bye")) {
                isProgramFinished = true;
                sayByeToUser();
            } else if(curr.equals("list")) {
                TaskManager1.listTasks();
            } else {
                TaskManager1.handleInput(curr);
            }
        }
    }
}
