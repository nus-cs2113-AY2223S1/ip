import java.util.Arrays;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        //        String logo = " ____        _        \n"
        //                + "|  _ \\ _   _| | _____ \n"
        //                + "| | | | | | | |/ / _ \\\n"
        //                + "| |_| | |_| |   <  __/\n"
        //                + "|____/ \\__,_|_|\\_\\___|\n";
        //        System.out.println("Hello from\n" + logo);
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");

        String line;
        Scanner in = new Scanner(System.in);
        String[] list = new String[100];
        int listCount = 0;
        request:
        while(true){
            line = in.nextLine();
            switch (line) {
            case "list":
                System.out.println("    ____________________________________________________________");
//                System.out.println(Arrays.toString(Arrays.copyOfRange(list, 0, listCount)));
                for(int i = 0; i < listCount; i++){
                    System.out.println("     " + (i+1) + ". " + list[i]);
                }
                System.out.println("    ____________________________________________________________");
                break;
            case "blah":
                System.out.println("    ____________________________________________________________");
                System.out.println("     blah");
                System.out.println("    ____________________________________________________________");
                break;
            case "bye":
                System.out.println("    ____________________________________________________________");
                System.out.println("     Bye. Hope to see you again soon!");
                System.out.println("    ____________________________________________________________");
                break request;
            default:
                list[listCount] = line;
                listCount ++;
                System.out.println("    ____________________________________________________________");
                System.out.println("     added: " + line);
                System.out.println("    ____________________________________________________________");
                break;
            }
        }
    }
}
