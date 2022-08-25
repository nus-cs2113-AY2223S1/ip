import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {

        String GREETING = "____________________________________________________________\n" +
                " Hello! I'm Weng!\n" +
                " What can I do for ya?\n" +
                "____________________________________________________________\n";
        System.out.println("\n" + GREETING);
//        String EXIT_MESSAGE = "____________________________________________________________\n" +
//                " Bye. Hope to cya again soon!\n" +
//                "____________________________________________________________";
//        Scanner sc = new Scanner(System.in);
//        String input = sc.nextLine();
//
//        while(!input.equals("bye")){
//            String output = "____________________________________________________________\n " +
//                    input +
//                    "\n____________________________________________________________\n";
//            System.out.println(output);
//            input = sc.nextLine();
//        } Level-1
        String[] myList;
        myList = new String[100];
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        int numberOfInputs = 0;
        while(!input.equals("list")){
            myList[numberOfInputs] = input;
            numberOfInputs++;
            String output = "____________________________________________________________\n " +
                    "Added: " +
                    input +
                    "\n____________________________________________________________\n";
            System.out.println(output);
            input = sc.nextLine();
        }
        for(int i = 0; i < numberOfInputs; i++){
            System.out.print(i + 1);
            System.out.println(". " + myList[i]);
        }

    }
}
