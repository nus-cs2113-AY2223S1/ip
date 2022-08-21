import java.awt.*;
import java.util.Scanner;

public class Duke {
    private boolean isActive = true;
    private String[] tasks = new String[100];
    private int taskCount = 0;
    private void greet() {
        String greetMessage = "Hello! I'm Banana\n"
                + "How can I help you?";
        reply(greetMessage);
    }
    private void bye() {
        String byeMessage = "Good bye. Hope to see you again soon!";
        reply(byeMessage);
    }
    private void reply (String message) {
        // Split this string to easily indent new line when replying
        String[] messageArray = message.split("\n");
        System.out.println("*************************************************************");
        //System.out.print(message);
        for (String item: messageArray) {
            //
            System.out.print("      ");
            System.out.print(item + '\n');
        }
        System.out.println("*************************************************************");
    }
    private void confirmAdd(String task) {
        this.reply("added: " + task);
    }
    private void printList(String[] tasks) {
        int i = 0;
        if (tasks[0] == null) {
            this.reply("There is nothing in the list right now");
        } else {
            System.out.println("*************************************************************");
            for (; i < 100 && tasks[i] != null; i++) {
                System.out.println("      " + String.valueOf(i + 1) + ". " + tasks[i]);
            }
            System.out.println("*************************************************************");
        }
    }
    private void parseMessage(String message) {
        switch(message) {
        case "bye":
            this.bye();
            this.isActive = false;
            break;
        case "list":
            this.printList(tasks);
            break;
        default:
            tasks[taskCount++] = message;
            this.confirmAdd(message);
        }
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();

        Scanner commands = new Scanner(System.in);
        String command;
        while (duke.isActive) {
            command = commands.nextLine();
            duke.parseMessage(command);
        }
    }
}
