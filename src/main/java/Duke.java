import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {

//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        // Level 1

//        String line = "---------------------------------------------";
//        String greet = "Hello! I'm Duke";
//        String question = "What can I do for you?";
//        System.out.println(line + "\n" + greet + "\n" + question + "\n" + line);
//
//        boolean run = true;
//        while(run){
//            Scanner in = new Scanner(System.in);
//            String text;
//            text = in.nextLine();
//            if(text.equals("bye")){
//                System.out.println(line);
//                System.out.println("Bye. Hope to see you again soon!");
//                System.out.println(line);
//                run = false;
//            }
//            else {
//                System.out.println(line);
//                System.out.println(text);
//                System.out.println(line);
//            }
//        }

        // Level 2

        String line = "---------------------------------------------";
        String greet = "Hello! I'm Duke";
        String question = "What can I do for you?";
        System.out.println(line + "\n" + greet + "\n" + question + "\n" + line);

        boolean run = true;
        String[] tasks = new String[100];
        int count = 0;

        while(run){
            Scanner in = new Scanner(System.in);
            String text;
            text = in.nextLine();
            if(text.equals("bye")){
                System.out.println(line);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                run = false;
            }
            else if (text.equals("list")){
                System.out.println(line);
                for(int i = 0; i< count; i++){
                    System.out.println(i+1 + ". " + tasks[i]);
                }
                System.out.println(line);
            }
            else {
                System.out.println(line);
                System.out.println("added: " + text);
                System.out.println(line);
                tasks[count] = text;
                count++;
            }
        }

    }
}
