import java.util.Scanner;

public class Duke {

    public static final String BYE = "bye";

    public static void main(String[] args) {
        printWelcomeMessage();

        String userInput = getUserInput();
        while (!userInput.equals(BYE)){
            printEchoInput(userInput);
            userInput = getUserInput();
        }

        printByeMessage();

    }

    public static String getUserInput(){
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        return line;
    }

    public static void printEchoInput(String userInput){
        String echoInput =
                "____________________________________________________________\n"
                + " "
                + userInput
                + "\n____________________________________________________________";
        System.out.println(echoInput);
    }

    private static void printWelcomeMessage() {
        String welcomeMessage =
                "____________________________________________________________\n"
                        + " Hello! I'm Duke\n"
                        + " What can I do for you?\n"
                        +"____________________________________________________________";
        System.out.println(welcomeMessage);
    }

    private static void printByeMessage() {
        String byeMessage =
                "____________________________________________________________\n"
                + " Bye. Hope to see you again soon!\n"
                + "____________________________________________________________";
        System.out.println(byeMessage);
    }


}
