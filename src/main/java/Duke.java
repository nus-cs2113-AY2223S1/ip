import java.util.Scanner;

public class Duke {
    static final String LOGO = "$$$$$$$\\                  $$\\           \n" +
            "$$  __$$\\                 $$ |          \n" +
            "$$ |  $$ |$$\\   $$\\  $$$$$$$ | $$$$$$\\  \n" +
            "$$ |  $$ |$$ |  $$ |$$  __$$ |$$  __$$\\ \n" +
            "$$ |  $$ |$$ |  $$ |$$ /  $$ |$$$$$$$$ |\n" +
            "$$ |  $$ |$$ |  $$ |$$ |  $$ |$$   ____|\n" +
            "$$$$$$$  |\\$$$$$$  |\\$$$$$$$ |\\$$$$$$$\\ \n" +
            "\\_______/  \\______/  \\_______| \\_______|";
    static final String GREET = "I am Dude.\n" +
            "Type something in, dude.";
    static final String BYE = "Catch you later, dude.";

    public static void runCommand(String command) {
        if (command.startsWith("bye")) {
            System.out.println(BYE);
            System.exit(0);
        } else {
            System.out.println(command);
        }
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public static void main(String[] args) {

        System.out.println(LOGO);
        System.out.println();
        System.out.println(GREET);
        System.out.println();

        Scanner input = new Scanner(System.in);

        while (true) {
            runCommand(input.nextLine());
        }

    }
}
