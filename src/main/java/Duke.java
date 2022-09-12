import java.io.*;
import java.util.ArrayList;
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

        // String line = "---------------------------------------------";
        // String greet = "Hello! I'm Duke";
        // String question = "What can I do for you?";
        // System.out.println(line + "\n" + greet + "\n" + question + "\n" + line);

        // boolean run = true;
        // String[] tasks = new String[100];
        // String[] marks = new String[100];
        // String[] type = new String[100];
        // int count = 1;

        // while(run){
        //     Scanner in = new Scanner(System.in);
        //     String text;
        //     text = in.nextLine();
        //     if(text.equals("bye")){
        //         System.out.println(line);
        //         System.out.println("Bye. Hope to see you again soon!");
        //         System.out.println(line);
        //         run = false;
        //     }
        //     else if (text.equals("list")){
        //         System.out.println(line);
        //         System.out.println("Here are the tasks in your list:");
        //         for(int i = 1; i< count; i++){
        //             System.out.println(i + ". " + type[i] + marks[i] + " " + tasks[i]);
        //         }
        //         System.out.println(line);
        //     }

        //     else if (text.contains("mark")){
        //         System.out.println(line);
        //         String strNum;
        //         int num;
        //         if(text.contains("unmark")){ //Unmark
        //             System.out.println("OK, I've marked this task as not done yet:");
        //             strNum = text.substring(7, text.length());
        //             num = Integer.parseInt(strNum);
        //             marks[num] = "[ ]";
        //             System.out.println(marks[num] + " " + tasks[num]);

        //         }
        //         else{ //Mark
        //             System.out.println("Nice! I've marked this task as done:");
        //             strNum = text.substring(5, text.length());
        //             num = Integer.parseInt(strNum);
        //             marks[num] = "[X]";
        //             System.out.println(marks[num] + " " + tasks[num]);
        //         }

        //         System.out.println(line);
        //     }

        //     else if (text.contains("todo")) {
        //         System.out.println(line);
        //         System.out.println("Got it. I've added this task");
        //         text = text.substring(5, text.length());
        //         System.out.println("  [T][ ] " + text);
        //         System.out.println("Now you have " + count + " tasks in the list.");
        //         System.out.println(line);
        //         tasks[count] = text;
        //         type[count] = "[T]";
        //         marks[count] = "[ ]";
        //         count++;
        //     }

        //     else if (text.contains("deadline")) {
        //         System.out.println(line);
        //         System.out.println("Got it. I've added this task");

        //         // Modify text according to deadline
        //         text = text.substring(9, text.length());
        //         int indexOfSlash = text.indexOf("/",9);
        //         String day = text.substring(indexOfSlash+4, text.length());
        //         String task = text.substring(0, indexOfSlash);
        //         text = task + "(by: " + day + ")";

        //         System.out.println("  [D][ ] " + text);
        //         System.out.println("Now you have " + count + " tasks in the list.");
        //         System.out.println(line);
        //         tasks[count] = text;
        //         type[count] = "[D]";
        //         marks[count] = "[ ]";
        //         count++;
        //     }
        //     else if (text.contains("event")) {
        //         System.out.println(line);
        //         System.out.println("Got it. I've added this task");

        //         // Modify text according to deadline
        //         text = text.substring(6, text.length());
        //         int indexOfSlash = text.indexOf("/",9);
        //         String day = text.substring(indexOfSlash+4, text.length());
        //         String task = text.substring(0, indexOfSlash);
        //         text = task + "(at: " + day + ")";

        //         System.out.println("  [E][ ] " + text);
        //         System.out.println("Now you have " + count + " tasks in the list.");
        //         System.out.println(line);
        //         tasks[count] = text;
        //         type[count] = "[E]";
        //         marks[count] = "[ ]";
        //         count++;
        //     }
        // }

        // Level 5
//
//        String line = "---------------------------------------------";
//        String greet = "Hello! I'm Duke";
//        String question = "What can I do for you?";
//        System.out.println(line + "\n" + greet + "\n" + question + "\n" + line);
//
//        boolean run = true;
//        String[] tasks = new String[100];
//        String[] marks = new String[100];
//        String[] type = new String[100];
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
//                    System.out.println(i + ". " + type[i] + marks[i] + " " + tasks[i]);
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
//            else if (text.contains("todo")) {
//                if(text.equals("todo") || text.equals("todo ")){
//                    System.out.println(line);
//                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
//                    System.out.println(line);
//                } else{
//                    System.out.println(line);
//                    System.out.println("Got it. I've added this task");
//                    text = text.substring(5, text.length());
//                    System.out.println("  [T][ ] " + text);
//                    System.out.println("Now you have " + count + " tasks in the list.");
//                    System.out.println(line);
//                    tasks[count] = text;
//                    type[count] = "[T]";
//                    marks[count] = "[ ]";
//                    count++;
//                }
//
//            }
//
//            else if (text.contains("deadline")) {
//                System.out.println(line);
//                System.out.println("Got it. I've added this task");
//
//                // Modify text according to deadline
//                text = text.substring(9, text.length());
//                int indexOfSlash = text.indexOf("/",9);
//                String day = text.substring(indexOfSlash+4, text.length());
//                String task = text.substring(0, indexOfSlash);
//                text = task + "(by: " + day + ")";
//
//                System.out.println("  [D][ ] " + text);
//                System.out.println("Now you have " + count + " tasks in the list.");
//                System.out.println(line);
//                tasks[count] = text;
//                type[count] = "[D]";
//                marks[count] = "[ ]";
//                count++;
//            }
//            else if (text.contains("event")) {
//                System.out.println(line);
//                System.out.println("Got it. I've added this task");
//
//                // Modify text according to deadline
//                text = text.substring(6, text.length());
//                int indexOfSlash = text.indexOf("/",9);
//                String day = text.substring(indexOfSlash+4, text.length());
//                String task = text.substring(0, indexOfSlash);
//                text = task + "(at: " + day + ")";
//
//                System.out.println("  [E][ ] " + text);
//                System.out.println("Now you have " + count + " tasks in the list.");
//                System.out.println(line);
//                tasks[count] = text;
//                type[count] = "[E]";
//                marks[count] = "[ ]";
//                count++;
//            }
//            else{
//                System.out.println(line);
//                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
//                System.out.println(line);
//            }
//        }

        // Level 6
        // Change from Array to ArrayList!
//
//        String line = "---------------------------------------------";
//        String greet = "Hello! I'm Duke";
//        String question = "What can I do for you?";
//        System.out.println(line + "\n" + greet + "\n" + question + "\n" + line);
//
//        boolean run = true;
//
//        ArrayList<String> tasks = new ArrayList<String>();
//        ArrayList<String> marks = new ArrayList<String>();
//        ArrayList<String> type = new ArrayList<String>();
//
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
//                    System.out.println(i + ". " + type.get(i-1) + marks.get(i-1) + " " + tasks.get(i-1));
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
//                    marks.set(num-1, "[ ]");  // To Modify
//                    System.out.println(marks.get(num-1) + " " + tasks.get(num-1));
//
//                }
//                else{ //Mark
//                    System.out.println("Nice! I've marked this task as done:");
//                    strNum = text.substring(5, text.length());
//                    num = Integer.parseInt(strNum);
//                    marks.set(num-1, "[X]"); // To Modify
//                    System.out.println(marks.get(num-1) + " " + tasks.get(num-1));
//                }
//
//                System.out.println(line);
//            }
//
//            else if (text.contains("todo")) {
//                if(text.equals("todo") || text.equals("todo ")){
//                    System.out.println(line);
//                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
//                    System.out.println(line);
//                } else{
//                    System.out.println(line);
//                    System.out.println("Got it. I've added this task");
//                    text = text.substring(5, text.length());
//                    System.out.println("  [T][ ] " + text);
//                    System.out.println("Now you have " + count + " tasks in the list.");
//                    System.out.println(line);
//                    tasks.add(text);
//                    type.add("[T]");
//                    marks.add("[ ]");
//                    count++;
//                }
//            }
//
//            else if (text.contains("deadline")) {
//                System.out.println(line);
//                System.out.println("Got it. I've added this task");
//
//                // Modify text according to deadline
//                text = text.substring(9, text.length());
//                int indexOfSlash = text.indexOf("/",9);
//                String day = text.substring(indexOfSlash+4, text.length());
//                String task = text.substring(0, indexOfSlash);
//                text = task + "(by: " + day + ")";
//
//                System.out.println("  [D][ ] " + text);
//                System.out.println("Now you have " + count + " tasks in the list.");
//                System.out.println(line);
//                tasks.add(text);
//                type.add("[D]");
//                marks.add("[ ]");
//                count++;
//            }
//            else if (text.contains("event")) {
//                System.out.println(line);
//                System.out.println("Got it. I've added this task");
//
//                // Modify text according to deadline
//                text = text.substring(6, text.length());
//                int indexOfSlash = text.indexOf("/",9);
//                String day = text.substring(indexOfSlash+4, text.length());
//                String task = text.substring(0, indexOfSlash);
//                text = task + "(at: " + day + ")";
//
//                System.out.println("  [E][ ] " + text);
//                System.out.println("Now you have " + count + " tasks in the list.");
//                System.out.println(line);
//                tasks.add(text);
//                type.add("[E]");
//                marks.add("[ ]");
//                count++;
//            }
//            else if (text.contains("delete")) {
//                System.out.println(line);
//                System.out.println("Noted. I've removed this task:");
//
//                // Modify text according to deadline
//                String strNum = text.substring(7, text.length());
//                Integer num = Integer.valueOf(strNum);
//
//
//                System.out.println("  " + type.get(num-1) + marks.get(num-1) + " " + tasks.get(num-1));
//
//                tasks.remove(num-1);
//                type.remove(num-1);
//                marks.remove(num-1);
//                count--;
//                Integer actCount = count - 1;
//
//                System.out.println("Now you have " + actCount + " tasks in the list.");
//                System.out.println(line);
//            }
//
//            else{
//                System.out.println(line);
//                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
//                System.out.println(line);
//            }
//        }

        // Level 7
        // Change from Array to ArrayList!

        ArrayList<String> tasks = new ArrayList<String>();
        ArrayList<String> marks = new ArrayList<String>();
        ArrayList<String> type = new ArrayList<String>();

        int count = 1;

        // Creates file if it is not created yet
        File file = new File("duke.txt");
        try {
            // Use relative path instead of relative path
            file.createNewFile();  // creates file if it has not been created
            System.out.println("duke.txt file found at " + file.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Load file data into system accordingly
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
            String line = reader.readLine();
            while (line != null) {

                // Load data to system
                String letterType = line.substring(0, 1);
                String numberMark = line.substring(4, 5);
                String taskName = line.substring(8, line.length());

                String strMark = null;
                if (numberMark.equals("1")) {
                    strMark = "[X]";
                } else if (numberMark.equals("0")) {
                    strMark = "[ ]";
                }

                tasks.add(taskName);
                type.add("[" + letterType + "]");
                marks.add(strMark);
                count++;

                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String line = "---------------------------------------------";
        String greet = "Hello! I'm Duke";
        String question = "What can I do for you?";
        System.out.println(line + "\n" + greet + "\n" + question + "\n" + line);

        boolean run = true;

        while (run) {
            Scanner in = new Scanner(System.in);
            String text;
            text = in.nextLine();
            if (text.equals("bye")) {
                System.out.println(line);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                run = false;
            } else if (text.equals("list")) {
                System.out.println(line);
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i < count; i++) {
                    System.out.println(i + ". " + type.get(i - 1) + marks.get(i - 1) + " " + tasks.get(i - 1));
                }
                System.out.println(line);
            } else if (text.contains("mark")) {
                System.out.println(line);
                String strNum;
                int num;
                if (text.contains("unmark")) { //Unmark
                    System.out.println("OK, I've marked this task as not done yet:");
                    strNum = text.substring(7, text.length());
                    num = Integer.parseInt(strNum);
                    marks.set(num - 1, "[ ]");  // To Modify
                    System.out.println(marks.get(num - 1) + " " + tasks.get(num - 1));

                } else { //Mark
                    System.out.println("Nice! I've marked this task as done:");
                    strNum = text.substring(5, text.length());
                    num = Integer.parseInt(strNum);
                    marks.set(num - 1, "[X]"); // To Modify
                    System.out.println(marks.get(num - 1) + " " + tasks.get(num - 1));


                }

                updateFile(count, tasks, marks, type);

                System.out.println(line);
            } else if (text.contains("todo")) {
                if (text.equals("todo") || text.equals("todo ")) {
                    System.out.println(line);
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    System.out.println(line);
                } else {
                    System.out.println(line);
                    System.out.println("Got it. I've added this task");
                    text = text.substring(5, text.length());
                    System.out.println("  [T][ ] " + text);
                    System.out.println("Now you have " + count + " tasks in the list.");
                    System.out.println(line);
                    tasks.add(text);
                    type.add("[T]");
                    marks.add("[ ]");
                    count++;

                    updateFile(count, tasks, marks, type);
                }
            } else if (text.contains("deadline")) {
                System.out.println(line);
                System.out.println("Got it. I've added this task");

                // Modify text according to deadline
                text = text.substring(9, text.length());
                int indexOfSlash = text.indexOf("/", 9);
                String day = text.substring(indexOfSlash + 4, text.length());
                String task = text.substring(0, indexOfSlash);
                text = task + "(by: " + day + ")";

                System.out.println("  [D][ ] " + text);
                System.out.println("Now you have " + count + " tasks in the list.");
                System.out.println(line);
                tasks.add(text);
                type.add("[D]");
                marks.add("[ ]");
                count++;

                updateFile(count, tasks, marks, type);
            } else if (text.contains("event")) {
                System.out.println(line);
                System.out.println("Got it. I've added this task");

                // Modify text according to deadline
                text = text.substring(6, text.length());
                int indexOfSlash = text.indexOf("/", 9);
                String day = text.substring(indexOfSlash + 4, text.length());
                String task = text.substring(0, indexOfSlash);
                text = task + "(at: " + day + ")";

                System.out.println("  [E][ ] " + text);
                System.out.println("Now you have " + count + " tasks in the list.");
                System.out.println(line);
                tasks.add(text);
                type.add("[E]");
                marks.add("[ ]");
                count++;

                updateFile(count, tasks, marks, type);
            } else if (text.contains("delete")) {
                System.out.println(line);
                System.out.println("Noted. I've removed this task:");

                // Modify text according to deadline
                String strNum = text.substring(7, text.length());
                Integer num = Integer.valueOf(strNum);


                System.out.println("  " + type.get(num - 1) + marks.get(num - 1) + " " + tasks.get(num - 1));

                tasks.remove(num - 1);
                type.remove(num - 1);
                marks.remove(num - 1);
                count--;
                Integer actCount = count - 1;

                System.out.println("Now you have " + actCount + " tasks in the list.");
                System.out.println(line);

                updateFile(count, tasks, marks, type);
            } else {
                System.out.println(line);
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println(line);
            }
        }
    }


    public static void updateFile(Integer count, ArrayList<String> tasks, ArrayList<String> marks, ArrayList<String> type){
        File file = new File("duke.txt");
        try{
            file.delete();
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (FileWriter myWriter = new FileWriter("duke.txt");
             BufferedWriter info = new BufferedWriter(myWriter)){
            for(int i = 1; i < count ; i++){
                String typeLetter = type.get(i-1).substring(1,2);  // from e.g. "[E]"
                String numMark = null;
                if (marks.get(i-1).equals("[X]")){
                    numMark = "1";
                }
                else if (marks.get(i-1).equals("[ ]")){
                    numMark = "0";
                }
                info.write(typeLetter + " | " + numMark + " | " + tasks.get(i-1));
                info.newLine();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

