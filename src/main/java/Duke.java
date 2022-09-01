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
        String[] words;
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")){
            words = line.split(" ");
            if (line.equals("list")){
                dukeList.printingList();
            } else if (words[0].equals("unmark")){
                int TaskNumber = Integer.parseInt(words[1]);
                dukeList.unmarkingItem(TaskNumber);
            } else if (words[0].equals("mark")) {
                int TaskNumber = Integer.parseInt(words[1]);
                dukeList.markingItem(TaskNumber);
            } else {
                dukeList.addTask(line);
            }
            line = in.nextLine();
        }
        Message.printingExit();
    }

}
