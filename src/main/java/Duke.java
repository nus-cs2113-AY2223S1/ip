import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello, my name is Duke! \n What can I do for you?");
        Scanner myObj = new Scanner(System.in);
        String userInput;
        while (true){
            userInput = myObj.nextLine();
            if (userInput.toLowerCase().replaceAll("\\s+","").equals("bye")){
                break;
            }
            System.out.println(userInput);
        }
        System.out.println("Bye. Hope to see you soon!");
    }
}
