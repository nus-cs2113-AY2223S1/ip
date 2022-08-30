import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        // level 1
        Scanner sc = new Scanner(System.in);
        String echo = sc.next();
        while (!Objects.equals(echo, "bye")){
            System.out.println(echo);
            echo = sc.next();
        }
        System.out.println("Bye. Hope to see you again soon!");

        // level 2
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        int count = 0;
        String[] storage = new String[100];
        while (true){
            Scanner sc2 = new Scanner(System.in);
            String add = sc2.nextLine();
            if(Objects.equals(add, "list")){
                for(int i=0; i<count; i++){
                    System.out.println((i+1)+". " +storage[i]);
                }
                continue;
            } else if (Objects.equals(add, "bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                storage[count] = add;
                System.out.println("added: " + add);
            }
            count++;
        }

    }
}
