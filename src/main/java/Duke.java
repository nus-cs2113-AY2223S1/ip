import java.util.Scanner;

public class Duke {
    public static void printGreeting() {
        String logo = "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⣤⣴⣶⣶⣶⣶⣶⠶⣶⣤⣤⣀⠀⠀⠀⠀⠀⠀ \n"
                + "⠀⠀⠀⠀⠀⠀⠀⢀⣤⣾⣿⣿⣿⠁⠀⢀⠈⢿⢀⣀⠀⠹⣿⣿⣿⣦⣄⠀\n"
                + "⠀⠀⠀⠀⠀⠀⣴⣿⣿⣿⣿⣿⠿⠀⠀⣟⡇⢘⣾⣽⠀⠀⡏⠉⠙⢛⣿⣷⡖⠀\n"
                + "⠀⠀⠀⠀⠀⣾⣿⣿⡿⠿⠷⠶⠤⠙⠒⠀⠒⢻⣿⣿⡷⠋⠀⠴⠞⠋⠁⢙⣿⣄ \n"
                + "⠀⠀⠀⠀⢸⣿⣿⣯⣤⣤⣤⣤⣤⡄⠀⠀⠀⠀⠉⢹⡄⠀⠀⠀⠛⠛⠋⠉⠹⡇ \n"
                + "⠀⠀⠀⠀⢸⣿⣿⠀⠀⠀⣀⣠⣤⣤⣤⣤⣤⣤⣤⣼⣇⣀⣀⣀⣛⣛⣒⣲⢾⡷ \n"
                + "⢀⠤⠒⠒⢼⣿⣿⠶⠞⢻⣿⣿;i⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠁⠀⣼⠃\n"
                + "⢮⠀⠀⠀⠀⣿⣿⣆⠀⠀⠻⣿⡿⠛⠉⠉⠁⠀⠉⠉⠛⠿⣿⣿⠟⠁⠀⣼⠃\n"
                + "⠈⠓⠶⣶⣾⣿⣿⣿⣧⡀⠀⠈⠒⢤⣀⣀⡀⠀⠀⣀⣀⡠⠚⠁⠀⢀⡼⠃\n"
                + "⠀⠀⠀⠈⢿⣿⣿⣿⣿⣿⣷⣤⣤⣤⣤⣭⣭⣭⣭⣭⣥⣤⣤⣤⣴⣟⠁\n";

        System.out.println("    ____________________________________________________________");
        System.out.println(logo + "\n    Kon'nichiwa! Doraemon desu ≧◉◡◉≦\n    What can I do for you?");
        System.out.println("    ____________________________________________________________");
    }

    public static void main(String[] args) {
        printGreeting();
        Scanner in = new Scanner(System.in);
        String command;
        String[] items = new String[100];
        int numberOfItems = 0;

        while (true) {
            command = in.nextLine();
            if (command.equals("list")) {
                System.out.println("    ____________________________________________________________");
                for (int i = 0; i < numberOfItems; i++) {
                    System.out.println("    \uD83D\uDC49 " + (i+1) + ". " + items[i]);
                }
                System.out.println("    ____________________________________________________________");
            }
            else if (command.equals("bye")){
                System.out.println("    ____________________________________________________________");
                System.out.println("    Sayonara. Hope to see you again soon! ✪");
                System.out.println("    ____________________________________________________________");
                break;
            }
            else {
                System.out.println("    ____________________________________________________________");
                items[numberOfItems++] = command;
                System.out.println("    added: " + command);
                System.out.println("    ____________________________________________________________");
            }
        }
    }
}
