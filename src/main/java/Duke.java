import java.util.Scanner;
public class Duke {
    public static void Echo(){
        boolean isRunning = true;
        while(isRunning){
            String line;
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            if(line.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                isRunning = false;
            }
            else {
                System.out.println(line);
            }
        }
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm Duke\n");
        System.out.println("What can I do for you?\n");
        Echo();
    }
}
