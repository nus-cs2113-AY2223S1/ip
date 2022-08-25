import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = "  ____                 _     \n" +
                " |  _ \\ ___  __ _  ___| |__  \n" +
                " | |_) / _ \\/ _` |/ __| '_ \\ \n" +
                " |  __/  __/ (_| | (__| | | |\n" +
                " |_|   \\___|\\__,_|\\___|_| |_|\n" +
                "                             ";
        System.out.println(logo);

        String intro = "__________________________________________________ \n"
                + "  Hello! I'm Peach  \n"
                + "  What can I do for you? \n"
                + "__________________________________________________ \n";

        System.out.println(intro);
        Task[] inputs = new Task[100];
        int itemCount = 0;
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        while (input.equals("bye") == false){
            System.out.println("__________________________________________________ \n");
            if (input.equals("list")){
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < itemCount; i++){
                    System.out.println(Integer.toString(i +1) + " " + inputs[i].description);
                }
                System.out.println("__________________________________________________ \n");
            } else if (input.contains("unmark")){
                System.out.println("Okiii... This task has been marked as not done yet");
                int taskNumber = Integer.parseInt(input.substring(input.indexOf("unmark ") + 7));
                if (taskNumber < itemCount){
                    inputs[taskNumber].markAsUndone();
                    System.out.println(inputs[taskNumber].description);
                }
                else{
                    System.out.println("Sorryyy!!! The index is out of bound!");
                }

                System.out.println("__________________________________________________ \n");
            } else if (input.contains("mark")){
                System.out.println("Okiii... This task has been marked as done");
                int taskNumber = Integer.parseInt(input.substring(input.indexOf("mark ")+ 5));
                if (taskNumber < itemCount) {
                    inputs[taskNumber].markAsDone();
                    System.out.println(inputs[taskNumber].description);
                }
                else{
                    System.out.println("Sorryyy!!! The index is out of bound!");
                }
                System.out.println("__________________________________________________ \n");

            } else{
                input = "[ ] " + input;
                inputs[itemCount] = new Task(input);
                itemCount++;
                System.out.println("added– "+ input);
                System.out.println("__________________________________________________ \n");
            }

            in = new Scanner(System.in);
            input = in.nextLine();

        }

        System.out.println("Bye. Hope to see you again soon! \n");

    }
}



