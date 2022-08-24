import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello, my name is Duke! \n What can I do for you?");
        Scanner myObj = new Scanner(System.in);
        String[] todoList = new String[100];
        String userInput;
        boolean bye = false;

        Commands commandType;
        while (!bye){
            userInput = myObj.nextLine();
            try{
                commandType = Commands.valueOf(userInput.toUpperCase());
                switch (commandType) {
                    case LIST -> {
                        System.out.println("____________________________________________________________");
                        for (int i = 0; i < todoList.length; i++) {
                            if (todoList[i] != null) {
                                System.out.println("\t" + (i + 1) + ")  " + todoList[i]);
                            } else {
                                break;
                            }
                        }
                        System.out.println("____________________________________________________________");
                    }
                    case BYE -> bye = true;
                }
            }
            catch(Exception e){
                for(int i = 0; i < todoList.length; i++)
                    if(todoList[i] == null) {
                        todoList[i] = userInput;
                        break;
                    }
                System.out.println("____________________________________________________________");
                System.out.println("\t Added: " + userInput);
                System.out.println("____________________________________________________________");
            }
        }
        System.out.println("____________________________________________________________");
        System.out.println("\tBye. Hope to see you soon!");
        System.out.println("____________________________________________________________");
    }

    enum Commands{
        LIST,
        BYE
    }
}
