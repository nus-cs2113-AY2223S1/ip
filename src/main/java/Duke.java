import java.util.*;

public class Duke {
    public static ArrayList<Task> ListOfTasks = new ArrayList<>();
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        proceed();

    }
    public static void proceed(){

        Communication.greet();
        Scanner sc = new Scanner(System.in);









        boolean exit = false;
        while (!exit){
            String inputString = sc.nextLine();
            if (inputString.equals("bye")){
                exit = true;
                Communication.bye();
                break;
            }
            else if (inputString.equals("list")){
                Communication.list(ListOfTasks);

            }
            else if (inputString.equals("read book")){
                ListOfTasks.add(new Task("read book"));
                System.out.println("added: read book");

            }
            else if (inputString.equals("return book")){
                ListOfTasks.add(new Task("return book"));
                System.out.println("added: return book");

            }
            else{
                Communication.copy(inputString);
            }
        }
    }
}
