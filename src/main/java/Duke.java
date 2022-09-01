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
//        String[] inputArray = new String[100];
        Task[] taskArray = new Task[100];
        int addCount = 0;
        do {
            input = in.nextLine();
            if(input.equals("bye")){
                System.out.println("------------------------------------");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("------------------------------------");
            } else if (input.equals("list")){
                System.out.println("------------------------------------");
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= addCount; i++){
                    System.out.println(i + "."+ "[" +taskArray[i-1].getStatusIcon() + "] " + taskArray[i-1].getDescription());
                }
                System.out.println("------------------------------------");
            } else if (input.contains("unmark")){
                // split the input into two parts - mark , and the 2nd part - convert to int
                // then use Task method to mark it
                String[] inputWords = input.split(" ");
                int choiceToUnMark = Integer.parseInt(inputWords[1]);
                taskArray[choiceToUnMark - 1].unMarkTask();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("\t[" +taskArray[choiceToUnMark-1].getStatusIcon() + "] " + taskArray[choiceToUnMark-1].getDescription());
                System.out.println("------------------------------------");

            } else if (input.contains("mark")){
                // split the input into two parts - mark , and the 2nd part - convert to int
                // then use Task method to mark it
                String[] inputWords = input.split(" ");
                int choiceToMark = Integer.parseInt(inputWords[1]);
                taskArray[choiceToMark - 1].markTask();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("\t[" +taskArray[choiceToMark-1].getStatusIcon() + "] " + taskArray[choiceToMark-1].getDescription());
                System.out.println("------------------------------------");
            }
            else {
//                inputArray[addCount] = input;
                taskArray[addCount] = new Task(input);
                System.out.println("------------------------------------");
                System.out.println("added: "+ input);
                System.out.println("------------------------------------");
                addCount +=1;
            }
        } while (!input.equals("bye"));


    }
}
