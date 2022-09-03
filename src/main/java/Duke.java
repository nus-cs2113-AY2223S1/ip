
import java.util.Scanner;
public class Duke {
    static String inputText = "";
    static Task[] tasks = new Task[100];
    static int taskCount = 0;
    static boolean isRunning = true;
    public static void main(String[] args) {
        showWelcomeMessage();
        handleUserInput();
    }

    public static void showWelcomeMessage(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Duke\n" + "What can I do for you?\n" +
                "____________________________________________________________\n" );
    }
    
    public static void showExitMessage(){
        System.out.println(" Bye. Hope to see you again soon!\n" +
                "____________________________________________________________");
    }
    
    public static void handleListMessage(){
        System.out.println(taskCount);
        System.out.println("____________________________________________________________");
        for(int i=0;i< taskCount;i++){
            System.out.println("\t"+(i+1)+".["+ tasks[i].getStatusIcon()+"] "+ tasks[i].description );
        }
        System.out.println("____________________________________________________________");
    }
    
    public static void addNewTask(){
        tasks[taskCount++] = new Task(inputText);
        System.out.println("____________________________________________________________\n" +
                "\tadded: "+inputText +"\n"+
                "____________________________________________________________\n");
    }
    
    public static void handleMarkMessage(){
        int position = Integer.parseInt(inputText.substring(inputText.length()-1));
        if ((inputText.substring(0,4).equals("mark"))){
            tasks[position-1].markAsDone();
            System.out.println("Nice! I've marked this task as done:");

        }else{
            tasks[position-1].markAsUndone();
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println("["+ tasks[position-1].getStatusIcon()+"] "+tasks[position-1].description);
    }

    public static void handleUserInput(){
        Scanner in = new Scanner(System.in);
        
        while(isRunning){
            inputText = in.nextLine();
            inputText.trim();
            if(inputText.equals("bye")) {
                isRunning = false;
                showExitMessage();
            } else if (inputText.equals("list")) {
                handleListMessage();
            }else if ((inputText.substring(0,4).equals("mark")) || inputText.substring(0,6).equals("unmark") ) {
                handleMarkMessage();
            }
            else{
                addNewTask();
            }

        }

       
    }
}
