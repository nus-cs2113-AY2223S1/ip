import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("------------------------------------");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("------------------------------------");
        Scanner in = new Scanner(System.in);
        String input;
        String[] inputArray = new String[100];
        int addCount = 0;
        do {
            input = in.nextLine();
            if(input.equals("bye")){
                System.out.println("------------------------------------");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("------------------------------------");
            } else if (input.equals("list")){
                System.out.println("------------------------------------");
                for (int i = 1; i <= addCount; i++){
                    System.out.println(i + ". "+ inputArray[i-1]);
                }
                System.out.println("------------------------------------");
            }
            else {
                inputArray[addCount] = input;
                System.out.println("------------------------------------");
                System.out.println("added: "+ input);
                System.out.println("------------------------------------");
                addCount +=1;
            }
        } while (!input.equals("bye"));


    }
}
