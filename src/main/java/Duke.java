import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line;
        String[] stringArray = new String[100];
        int count = 0;
        Scanner in = new Scanner(System.in);
        System.out.println("Duke: Hello! I'm Duke");
        System.out.println("Duke: What can I do for you?");


        String bye = "bye";
        String list = "list";
        while (true) {
            line = in.nextLine();
            if (line.equals(bye)) {
                System.out.println("Duke: Bye. Hope to see you again soon!");
                break;
            } else if (line.equals(list)) {
                for (int i = 0; i < count; i++) {
                    System.out.println(i + ": " + stringArray[i]);
                }
            } else {
                System.out.println("added: " + line);
                stringArray[count] = line;
                count++;
            }
        }

    }
}
