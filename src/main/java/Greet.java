import java.util.Scanner;

public class Greet {
    private String hello = "Hello! I'm Duke";
    private String greeting = "What can I do for you?";
    private String goodbye = "Bye. Hope to see you again soon!";
    // method to print greetings
    public void printGreet() {
        System.out.println(hello);
        System.out.println(greeting);
        Scanner in = new Scanner(System.in);
        while(true) {
            String line = in.nextLine();
            if (line.equals("bye")) {
                System.out.println(goodbye);
                break;
            } else {
                System.out.println(line);
            }
        }

    }
}
