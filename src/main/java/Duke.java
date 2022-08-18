import java.util.Locale;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String welcomeText = "Hello! I am not Duke.\n"
                + "I can echo you now.\n";
        System.out.println(welcomeText);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.toLowerCase().trim().equals("bye")) {
                break;
            }
            System.out.println(input);
        }
        System.out.println("Byebye!!!");
    }
}
