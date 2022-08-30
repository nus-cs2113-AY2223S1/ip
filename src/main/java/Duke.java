import java.util.Scanner;

public class Duke {

    public static final String BYE = "bye";

    public static void main(String[] args) {
        printWelcomeMessage();
        String[] taskLists = new String[100];
        int count = 0;


        String userInput = getUserInput();

        while(!userInput.equals("bye")){
            switch(userInput){
            case "list":
                printList(taskLists, count);
                break;

            default:
                printEchoInput(userInput);
                taskLists[count] = userInput;
                count++;
            }
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
                "    ____________________________________________________________\n"
                + "     added: "
                + userInput
                + "\n    ____________________________________________________________";
        System.out.println(echoInput);
    }

    public static void printWelcomeMessage() {
        String welcomeMessage =
                "    ____________________________________________________________\n"
                + "     Hello! I'm Duke\n"
                + "     What can I do for you?\n"
                + "    ____________________________________________________________";
        System.out.println(welcomeMessage);
    }

    public static void printByeMessage() {
        String byeMessage =
                "    ____________________________________________________________\n"
                + "     Bye. Hope to see you again soon!\n"
                + "    ____________________________________________________________";
        System.out.println(byeMessage);
    }

    public static void printList(String[] taskLists, int count){
        System.out.println("    ____________________________________________________________");
        for (int i=0; i<count; i++){
            int index = i + 1;
            System.out.println("     " + index + ". " + taskLists[i]);
        }
        System.out.println("    ____________________________________________________________");
    }


}
