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
                if(line.contains("mark")){
                    int i = line.indexOf(" ") + 1;
                    Task t = new Task(List[Integer.parseInt(line.substring(i))-1]);
                    t.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[" + t.getStatusIcon() + "] " + List[Integer.parseInt(line.substring(i))-1]);
                } else if(line.contains("unmark")) {
                    int i = line.indexOf(" ") + 1;
                    Task t = new Task(List[Integer.parseInt(line.substring(i))-1]);
                    t.unmarkAsNotDone();
                    System.out.println("Ok, I've marked this task as not done yet:");
                    System.out.println("[" + t.getStatusIcon() + "] " + List[Integer.parseInt(line.substring(i))-1]);
                } else {
                    System.out.println("added: " + line);
                    List[ListNo] = line;
                    ListNo++;
                }
            }else if(line.compareTo("list") == 0){
                for(int i = 0; i < ListNo; i++) {
                    Task t = new Task(List[i]);
                    System.out.println((i+1) + ".[ " + t.getStatusIcon() + "] " + List[i]);
                }
            }
        } while (line.compareTo("bye") != 0);
        System.out.println("Bye. Hope to see you again soon!");
    }
}
