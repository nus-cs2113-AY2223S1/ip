import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String line;
        Scanner in = new Scanner(System.in);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can i do for you?");
        String[] List = new String[100];
        int ListNo = 0;
        do{
            line = in.nextLine();
            if (line.equals("bye")){
                break;
            }else if(line.compareTo("list") != 0) {
                System.out.println("added: " + line);
                List[ListNo] = line;
                ListNo++;
            }else if(line.compareTo("list") == 0){
                for(int i = 0; i < ListNo; i++) {
                    System.out.println((i+1) + ". " + List[i]);
                }
            }
        } while (line.compareTo("bye") != 0);
        System.out.println("Bye. Hope to see you again soon!");
    }
}
