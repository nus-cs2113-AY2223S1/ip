import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Message.printingGreeting();
        echo();
    }

    public static void echo(){
        List dukeList = new List();
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")){
            if (line.equals("list")){
                dukeList.printingList();
            }
            else {
                dukeList.addItem(line);
            }
            line = in.nextLine();
        }
        Message.printingExit();
    }

}
