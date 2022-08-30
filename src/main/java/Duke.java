import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {

//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        // Level 0

//        String line = "---------------------------------------------";
//        String greet = "Hello! I'm Duke";
//        String question = "What can I do for you?";
//        System.out.println(line + "\n" + greet + "\n" + question + "\n" + line);
//        System.out.println("Bye. Hope to see you again soon!");
//        System.out.println(line);

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

//        String line = "---------------------------------------------";
//        String greet = "Hello! I'm Duke";
//        String question = "What can I do for you?";
//        System.out.println(line + "\n" + greet + "\n" + question + "\n" + line);
//
//        boolean run = true;
//        String[] tasks = new String[100];
//        int count = 0;
//
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
//            else if (text.equals("list")){
//                System.out.println(line);
//                for(int i = 0; i< count; i++){
//                    System.out.println(i+1 + ". " + tasks[i]);
//                }
//                System.out.println(line);
//            }
//            else {
//                System.out.println(line);
//                System.out.println("added: " + text);
//                System.out.println(line);
//                tasks[count] = text;
//                count++;
//            }
//        }

        // Level 3

//        String line = "---------------------------------------------";
//        String greet = "Hello! I'm Duke";
//        String question = "What can I do for you?";
//        System.out.println(line + "\n" + greet + "\n" + question + "\n" + line);
//
//        boolean run = true;
//        String[] tasks = new String[100];
//        String[] marks = new String[100];
//        int count = 1;
//
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
//            else if (text.equals("list")){
//                System.out.println(line);
//                System.out.println("Here are the tasks in your list:");
//                for(int i = 1; i< count; i++){
//                    System.out.println(i + ". " + marks[i] + " " + tasks[i]);
//                }
//                System.out.println(line);
//            }
//
//            else if (text.contains("mark")){
//                System.out.println(line);
//                String strNum;
//                int num;
//                if(text.contains("unmark")){ //Unmark
//                    System.out.println("OK, I've marked this task as not done yet:");
//                    strNum = text.substring(7, text.length());
//                    num = Integer.parseInt(strNum);
//                    marks[num] = "[ ]";
//                    System.out.println(marks[num] + " " + tasks[num]);
//
//                }
//                else{ //Mark
//                    System.out.println("Nice! I've marked this task as done:");
//                    strNum = text.substring(5, text.length());
//                    num = Integer.parseInt(strNum);
//                    marks[num] = "[X]";
//                    System.out.println(marks[num] + " " + tasks[num]);
//                }
//
//                System.out.println(line);
//            }
//
//            else {
//                System.out.println(line);
//                System.out.println("added: " + text);
//                System.out.println(line);
//                tasks[count] = text;
//                marks[count] = "[ ]";
//                count++;
//            }
//        }

        // Level 4

        String line = "---------------------------------------------";
        String greet = "Hello! I'm Duke";
        String question = "What can I do for you?";
        System.out.println(line + "\n" + greet + "\n" + question + "\n" + line);

        boolean run = true;
        String[] tasks = new String[100];
        String[] marks = new String[100];
        String[] type = new String[100];
        int count = 1;

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
                System.out.println("Here are the tasks in your list:");
                for(int i = 1; i< count; i++){
                    System.out.println(i + ". " + type[i] + marks[i] + " " + tasks[i]);
                }
                System.out.println(line);
            }

            else if (text.contains("mark")){
                System.out.println(line);
                String strNum;
                int num;
                if(text.contains("unmark")){ //Unmark
                    System.out.println("OK, I've marked this task as not done yet:");
                    strNum = text.substring(7, text.length());
                    num = Integer.parseInt(strNum);
                    marks[num] = "[ ]";
                    System.out.println(marks[num] + " " + tasks[num]);

                }
                else{ //Mark
                    System.out.println("Nice! I've marked this task as done:");
                    strNum = text.substring(5, text.length());
                    num = Integer.parseInt(strNum);
                    marks[num] = "[X]";
                    System.out.println(marks[num] + " " + tasks[num]);
                }

                System.out.println(line);
            }

            else if (text.contains("todo")) {
                System.out.println(line);
                System.out.println("Got it. I've added this task");
                text = text.substring(5, text.length());
                System.out.println("  [T][ ] " + text);
                System.out.println("Now you have " + count + " tasks in the list.");
                System.out.println(line);
                tasks[count] = text;
                type[count] = "[T]";
                marks[count] = "[ ]";
                count++;
            }

            else if (text.contains("deadline")) {
                System.out.println(line);
                System.out.println("Got it. I've added this task");

                // Modify text according to deadline
                text = text.substring(9, text.length());
                int indexOfSlash = text.indexOf("/",9);
                String day = text.substring(indexOfSlash+4, text.length());
                String task = text.substring(0, indexOfSlash);
                text = task + "(by: " + day + ")";

                System.out.println("  [D][ ] " + text);
                System.out.println("Now you have " + count + " tasks in the list.");
                System.out.println(line);
                tasks[count] = text;
                type[count] = "[D]";
                marks[count] = "[ ]";
                count++;
            }
            else if (text.contains("event")) {
                System.out.println(line);
                System.out.println("Got it. I've added this task");

                // Modify text according to deadline
                text = text.substring(6, text.length());
                int indexOfSlash = text.indexOf("/",9);
                String day = text.substring(indexOfSlash+4, text.length());
                String task = text.substring(0, indexOfSlash);
                text = task + "(at: " + day + ")";

                System.out.println("  [E][ ] " + text);
                System.out.println("Now you have " + count + " tasks in the list.");
                System.out.println(line);
                tasks[count] = text;
                type[count] = "[E]";
                marks[count] = "[ ]";
                count++;
            }
        }



    }
}
