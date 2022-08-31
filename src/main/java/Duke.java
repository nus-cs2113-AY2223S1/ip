import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        showWelcomeMessage();
        handleUserInput();
    }

    public static void showWelcomeMessage(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Duke\n" + "What can I do for you?\n" +
                "____________________________________________________________\n" );
    }

    public static void handleUserInput(){
        Scanner in = new Scanner(System.in);
        String inputText = "";
        boolean isRunning = true;
        while(isRunning){
            inputText = in.nextLine();
            if(inputText.equals("bye")){
                isRunning = false;
            }
            else{
                System.out.println("____________________________________________________________\n" +
                        "     "+inputText +"\n"+
                        "____________________________________________________________\n");
            }

        }

        System.out.println(" Bye. Hope to see you again soon!\n" +
                    "____________________________________________________________");
    }
}
