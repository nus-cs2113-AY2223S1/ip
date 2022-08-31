import java.util.ArrayList;
import java.util.List;
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
        List<String> inputs = new ArrayList<>();
        boolean isRunning = true;
        while(isRunning){
            inputText = in.nextLine();
            if(inputText.equals("bye")) {
                isRunning = false;
            }
            else if (inputText.equals("list")) {
                System.out.println("____________________________________________________________");
                for(int i=0;i< inputs.size();i++){
                    System.out.println("\t"+(i+1)+". "+ inputs.get(i) );
                }
                System.out.println("____________________________________________________________");
            }
            else{
                inputs.add(inputText);
                System.out.println("____________________________________________________________\n" +
                        "\tadded: "+inputText +"\n"+
                        "____________________________________________________________\n");
            }

        }

        System.out.println(" Bye. Hope to see you again soon!\n" +
                    "____________________________________________________________");
    }
}
