import java.util.Scanner;

public class Duke {
    public static void greet(){
        String entryBanner = "____________________________________________________________\n"
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + "____________________________________________________________";
        System.out.println(entryBanner);
    }
    public static void add(String userInput){
        String echoReply = "____________________________________________________________\n"
                + "added: " + userInput + "\n"
                + "____________________________________________________________";
        System.out.println(echoReply);
    }
    public static void list(String[] inputList, int count){
        String listing = "____________________________________________________________\n";
        for(int i=0; i<count; ++i){
            listing += String.format("%d. %s\n", i+1, inputList[i]);
        }
        listing += "____________________________________________________________";
        System.out.println(listing);
    }
    public static void quit(){
        String exitBanner = "____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________";
        System.out.println(exitBanner);
    }
    public static void main(String[] args) {
        greet();
        String userInput = "";
        Scanner in = new Scanner(System.in);
        String[] listings = new String[100];
        int count = 0;

        while(!userInput.equals("bye")) {
            userInput = in.nextLine();
            switch (userInput) {
            case "list":
                list(listings, count);
                break;
            case "bye":
                quit();
                break;
            default:
                listings[count] = userInput;
                add(userInput);
                count++;
                break;
            }
        }
    }
}
