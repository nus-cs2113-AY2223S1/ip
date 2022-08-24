import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
//        line = in.nextLine();

        do{
            line = in.nextLine();
            if(line.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                return;
            }
            else{
                System.out.println("\t_____________________");
                System.out.println("\t" + line);
                System.out.println("\t_____________________\n");
            }

        }while(!line.equals("bye"));
    }
}
