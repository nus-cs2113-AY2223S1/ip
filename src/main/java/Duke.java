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

        while (true) {
            command = in.nextLine();
            if (!command.equals("bye")) {
                System.out.println("    ____________________________________________________________");
                System.out.println("    " + command);
                System.out.println("    ____________________________________________________________");
            }
            else {
                System.out.println("    ____________________________________________________________");
                System.out.println("    Sayonara. Hope to see you again soon! ✪");
                System.out.println("    ____________________________________________________________");
                break;
            }
        }
    }
}
