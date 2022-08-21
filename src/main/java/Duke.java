import java.util.*;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputs;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greetings();
        inputs = scanner.nextLine();
        while(!inputs.equals("bye") ) {
            System.out.println(inputs + '\n');
            inputs = scanner.nextLine();
        }
        exit();
    }

    private static void exit() {
        System.out.println(" Bye! Hope to see you again soon :)");
    }

    private static void greetings(){
        System.out.println(" Hello! I'm Duke\n" + " What can I do for you?");
    }
}
