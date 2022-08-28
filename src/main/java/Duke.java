import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;
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
        Scanner sc = new Scanner(System.in);
        boolean isFinished = false;
        ArrayList<String> list_items = new ArrayList<String>();
        int noOfItems = 0;


        do {
            String read = sc.nextLine();

            if(read.equals("bye")){
                isFinished = true;
            } else if (read.equals("list")) {
                int i = 1;
                System.out.println(
                        "____________________________________________________________"
                );
                Iterator<String> itr = list_items.iterator();
                while(itr.hasNext()) {
                    System.out.println(
                            + i + ". " + itr.next()
                    );
                    i++;
                }
                System.out.println(
                        "____________________________________________________________\n"
                );
            }
            else{
                list_items.add(read);
                noOfItems ++;
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
