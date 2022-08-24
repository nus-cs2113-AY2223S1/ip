import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line = new String();
        Scanner in = new Scanner(System.in);
        System.out.println("Duke: Hello! I'm Duke");
        System.out.println("Duke: What can I do for you?");


        String s1 = "bye";
        while (true) {
            line = in.nextLine();
            if (line.equals(s1)) {
                System.out.println("Duke: Bye. Hope to see you again soon!");
                break;
            } else {
                System.out.println("Duke: " + line);
            }
        }

    }
}
