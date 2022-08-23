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
        String[] items = new String[100];
        int counter = 1;
        int matchCount = 0;
        while(true) {
            String line = in.nextLine();
            if (line.equals("bye")) {
                System.out.println(goodbye);
                break;
            } else if (line.equals("list")) {

                for (String item : items) {
                    if (item != null) {
                        System.out.println(counter + ". " + item);
                    }
                    counter += 1;
                }
            } else {
                System.out.println("added " + line);
                items[matchCount] = line;
                matchCount += 1;
            }
        }
    }
}
