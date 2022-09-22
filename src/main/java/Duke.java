
import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    static String inputText = "";
    static ArrayList<Task> tasks = new ArrayList<>();
    static int taskCount = 0;
    static boolean isRunning = true;
    public static void main(String[] args) throws NullCommandException {
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
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println("____________________________________________________________" );
    }
    
    public static void showExitMessage(){
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
    
    public static void handleListMessage(){
        System.out.println("____________________________________________________________");
        for(int i=0;i< taskCount;i++){
            System.out.println(tasks.get(i).toString() );
        }
        System.out.println("____________________________________________________________");
    }
    
    public static void addNewTask(String command) {
        try {
            if(command.equals("todo")){
                tasks.add( new Todo(inputText.substring(5)));
                taskCount++;
            } else if (command.equals("deadline")) {
                tasks.add(new Deadline(inputText.substring(9,inputText.indexOf('/')),inputText.substring(inputText.indexOf('/')+4)));
                taskCount++;
            }else if(command.equals("event")){
                tasks.add(new Event(inputText.substring(6,inputText.indexOf('/')),inputText.substring(inputText.indexOf('/')+4)));
                taskCount++;
            }else{
                throw new InvalidCommandException();
            }
            System.out.println("____________________________________________________________");
            System.out.println("Got it. I've added this task:\n"+inputText +"\n"+"Now you have "+taskCount+ " tasks in the list.");
            System.out.println("____________________________________________________________");

        }catch (InvalidCommandException e) {
            System.out.println("This is not a valid command.Please re-enter a valid command");
        }



    }
    public static void handleDeleteMessage(int position) {
        String str = tasks.get(position-1).toString();
        tasks.remove(position-1);
        taskCount--;
        System.out.println("____________________________________________________________");
        System.out.println("Noted. I've removed this task:");
        System.out.println(str);
        System.out.println("Now you have "+taskCount+ " tasks in the list.");
        System.out.println("____________________________________________________________");


    }
    
    public static void handleMarkMessage(){
        int position = Integer.parseInt(inputText.substring(inputText.length()-1));
        if ((inputText.substring(0,4).equals("mark"))){
            tasks.get(position-1).markAsDone();
            System.out.println("Nice! I've marked this task as done:");

        }else{
            tasks.get(position-1).markAsUndone();
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println("["+ tasks.get(position-1).getStatusIcon()+"] "+tasks.get(position-1).description);
    }

    public static void handleUserInput() {
        Scanner in = new Scanner(System.in);
            while(isRunning){
                try {
                    inputText = in.nextLine();
                    inputText = inputText.trim();
                    String splitText[] = inputText.split(" ");
                    if(splitText.length == 0 || splitText[0].length() == 0) {
                        throw new NullCommandException();
                    } else if(splitText[0].equals("bye")) {
                        isRunning = false;
                        showExitMessage();
                    } else if (splitText[0].equals("list")) {
                        handleListMessage();
                    }else if ((splitText[0].equals("mark")) || splitText[0].equals("unmark") ) {
                        handleMarkMessage();
                    }else if(splitText[0].equals("delete")){
                        handleDeleteMessage(Integer.parseInt(splitText[1]));
                    }
                    else{
                        addNewTask(splitText[0]);
                    }
                }catch (NullCommandException e){
                    System.out.println("Empty command. Please try again.");
                }
            }


       
    }
}
