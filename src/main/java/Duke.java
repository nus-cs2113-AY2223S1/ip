import java.util.Scanner;

public class Duke {

    public static void greetUser(){
        String greeting = "____________________________________________________________" + System.lineSeparator()
        + "Hello! My name is Axel. :-)" + System.lineSeparator()
        + "How may I help you today?" + System.lineSeparator()
        + "____________________________________________________________" + System.lineSeparator();
        System.out.println(greeting);
    }

    public static void sayByeToUser(){
        String message = "____________________________________________________________" + System.lineSeparator()
                + "Goodbye. Hope to see you again soon!" + System.lineSeparator()
                + "____________________________________________________________";
        System.out.println(message);
    }

    public static void main(String[] args) {
        String logo = "   _____                .__   \n" +
                "  /  _  \\ ___  ___ ____ |  |  \n" +
                " /  /_\\  \\\\  \\/  // __ \\|  |  \n" +
                "/    |    \\>    <\\  ___/|  |__\n" +
                "\\____|__  /__/\\_ \\\\___  >____/\n";
        System.out.println(logo);
        greetUser();
        Scanner input = new Scanner(System.in);
        TaskManager TaskManager1 = new TaskManager();
        boolean isFinished = false; //variable to indicate if the program should be terminated
        while(!isFinished){
            String curr = input.nextLine();
            if(curr.equals("bye")){
                isFinished = true;
                sayByeToUser();
            }
            else if(curr.equals("list")){
                TaskManager1.listTasks();
            }
            else{
                String[] text = curr.split(" ");
                if(text[0].equals("mark")){
                    int taskNumber = Integer.parseInt(text[1]);
                    TaskManager1.markAsDone(taskNumber);
                }
                else if(text[0].equals("unmark")){
                    int taskNumber = Integer.parseInt(text[1]);
                    TaskManager1.markAsUndone(taskNumber);
                }
                else{
                    String task = "";
                    for(String word : text){
                        task = task + " " + word;
                    }
                    TaskManager1.addTask(task);
                }
            }
        }
    }
}
