import java.util.*;

public class Duke {
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
            else{
                Communication.copy(inputString);
            }
        }
    }
}
