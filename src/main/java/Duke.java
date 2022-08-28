import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
       /*
       String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        */

        System.out.println(
                "____________________________________________________________\n" +
                        " Hello! I'm Duke\n" +
                        " What can I do for you?\n" +
                        "____________________________________________________________\n"
        );
        System.out.println(
                "list\n"
                        +"____________________________________________________________\n"
                        +"list\n"
                        +"____________________________________________________________\n"
        );
        Scanner sc = new Scanner(System.in);
        boolean isFinished = false;

        do {
            String read = sc.nextLine();

            if(read.equals("bye")){
                isFinished = true;
            }
            else{
                System.out.println("____________________________________________________________\n"
                        + read + "\n"
                        + "____________________________________________________________\n"
                );
            }
        }while(isFinished != true);
        System.out.println("____________________________________________________________\n"
                + "Bye. Hope to see you again soon! \n"
                + "____________________________________________________________\n"
        );
    }
}
