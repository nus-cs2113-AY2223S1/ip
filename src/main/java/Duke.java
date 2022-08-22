import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        String input;
        String[] texts = new String[100];
        int textCount = 0;
        Scanner in = new Scanner(System.in);
        input = in.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                for (int i = 0; i < textCount; i++) {
                    System.out.println(i + 1 + ". " + texts[i]);
                }
            }
            else {
                texts[textCount] = input;
                textCount++;
                System.out.println("added: " + input);
            }
            input = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
