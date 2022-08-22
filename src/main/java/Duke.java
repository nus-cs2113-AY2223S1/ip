import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String inData = "0";
        Scanner scan = new Scanner(System.in);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println("____________________________________________________________");

        while (!inData.equals("bye")) {

            inData = scan.nextLine();
            System.out.println("____________________________________________________________");
            System.out.println(inData);
            if (!inData.equals("bye")) {
                System.out.println("____________________________________________________________");
            }
        }
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
