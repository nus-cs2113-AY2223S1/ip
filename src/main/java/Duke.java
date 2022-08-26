import java.util.Scanner;

public class Duke {

    /**
     *
     */
    private static final int MAXLISTNUM = 100;
    private static String[] list = new String[MAXLISTNUM];
    private static int listLength = 0; 
    public static void main(String[] args) {
        
        showWelcomeMessage();
        handleUserInput();
    }
    private static void showWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("    Hello from\n" + logo);
        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        System.out.println("    ____________________________________________________________");
        System.out.println();
    }

    private static void handleUserInput() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String inputText = scanner.nextLine();
            while (inputText.trim().isEmpty())
                inputText = scanner.nextLine();
            switch (inputText) {
            case "bye":
                System.out.println("    Bye. Hope to see you again soon!");
                System.out.println("    ____________________________________________________________");
                System.exit(0);
            case "list":
                System.out.println("    ____________________________________________________________");
                for (int i=1;i<=listLength;i++){
                    System.out.println("    "+String.valueOf(i)+". "+list[i-1]);
                };
                System.out.println("    ____________________________________________________________");
                System.out.println();
                break;
            default:
                list[listLength] = inputText;
                listLength++;
                System.out.println("    ____________________________________________________________");
                System.out.println("    "+"added: "+inputText);
                System.out.println("    ____________________________________________________________");
                System.out.println();
                break;
            }
        }
    }
}
