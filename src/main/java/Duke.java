import java.util.Scanner;

public class Duke {
    private boolean isActive = true;
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
    private void parseMessage(String message) {
        switch(message) {
        case "bye":
            this.bye();
            this.isActive = false;
            break;
        default:
            reply(message);
        }
    }
    public static void main(String[] args) {
        Duke banana = new Duke();
        banana.greet();

        Scanner commands = new Scanner(System.in);
        String command;
        while (banana.isActive) {
            command = commands.nextLine();
            banana.parseMessage(command);
        }
    }
}
