import java.util.Scanner;

public class Duke {
    public static void greet(){
        String entryBanner = "____________________________________________________________\n"
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + "____________________________________________________________";
        System.out.println(entryBanner);
    }

    public static void echo(String userInput){
        String echoReply = "____________________________________________________________\n"
                + userInput + "\n"
                + "____________________________________________________________";
        System.out.println(echoReply);
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
        while(true){
            userInput = in.nextLine();
            if(userInput.equals("bye")){
                break;
            }else{
                echo(userInput);
            }
        }
        quit();
    }
}
