import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {

        String GREETING = "____________________________________________________________\n" +
                " __      __                       \n" +
                "/  \\    /  \\ ____   ____    ____  \n" +
                "\\   \\/\\/   // __ \\ /    \\  / ___\\ \n" +
                " \\        /\\  ___/|   |  \\/ /_/  >\n" +
                "  \\__/\\  /  \\___  >___|  /\\___  / \n" +
                "       \\/       \\/     \\//_____/  "+
                "\n Hello! I'm Weng!\n" +
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
//        }
//        Level-1
//        String[] myList;
//        myList = new String[100];
//        Scanner sc = new Scanner(System.in);
//        String input = sc.nextLine();
//        int numberOfInputs = 0;
//        while(!input.equals("list")){
//            myList[numberOfInputs] = input;
//            numberOfInputs++;
//            String output = "____________________________________________________________\n " +
//                    "Added: " +
//                    input +
//                    "\n____________________________________________________________\n";
//            System.out.println(output);
//            input = sc.nextLine();
//        }
//        for(int i = 0; i < numberOfInputs; i++){
//            System.out.print(i + 1);
//            System.out.println(". " + myList[i]);
//        }
//        Level-2


        Task[] myList;
        myList = new Task[1000];
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        int numberOfInputs = 0;
        while(!input.equals("bye")) {
            String words[] = input.split(" ");
            if (input.equals("list")) {
                for (int i = 0; i < numberOfInputs; i++) {
                    System.out.print(i + 1);
                    System.out.print(".");
                    System.out.print(myList[i].getStatusIcon());
                    System.out.println(myList[i].description);
                }
            }
            else if (words[0].equals("mark")) {//pending check if second character is number AND smaller than numberOfInputs
                int index = Integer.parseInt(words[1]);
                myList[index-1].markAsDone();
                System.out.println("____________________________________________________________\n" +
                        "     Nice! I've marked this task as done:\n" +
                        myList[index-1].getStatusIcon() + " " + myList[index-1].description +
                        "    ____________________________________________________________");
            }
            else if (words[0].equals("unmark")) {//pending check if second character is number AND smaller than numberOfInputs
                int index = Integer.parseInt(words[1]);
                myList[index-1].markAsNotDone();
                System.out.println("____________________________________________________________\n" +
                        "     Alright! I've marked this task as not done yet:\n" +
                        myList[index-1].getStatusIcon() + " " + myList[index-1].description +
                        "    ____________________________________________________________");
            } else {
                myList[numberOfInputs] = new Task(input);
                numberOfInputs++;
                String output = "____________________________________________________________\n " +
                    "Added: " +
                    input +
                    "\n____________________________________________________________\n";
            System.out.println(output);
            }
            input = sc.nextLine();
        }
        String EXIT_MESSAGE = "____________________________________________________________\n" +
                " Bye. Hope to cya again soon!\n" +
                "____________________________________________________________";
        System.out.println(EXIT_MESSAGE);


    }
}
