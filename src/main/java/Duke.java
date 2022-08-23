import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Menu dukeMenu = new Menu();
        String userInput = "";
        Scanner in = new Scanner(System.in);

        dukeMenu.greet();
        while(!userInput.equals("bye")) {
            userInput = in.nextLine();
            String[] inputParts = userInput.split(" ");
            switch (inputParts[0]) {
            case "list":
                dukeMenu.list();
                break;
            case "mark":
                if(inputParts.length == 2){
                    try{
                        dukeMenu.mark(Integer.parseInt(inputParts[1]));
                    }catch (NumberFormatException exception) {
                        dukeMenu.showErrorMessage();
                    }
                }else{
                    dukeMenu.showErrorMessage();
                }
                break;
            case "unmark":
                if(inputParts.length == 2){
                    try{
                        dukeMenu.unmark(Integer.parseInt(inputParts[1]));
                    }catch (NumberFormatException exception) {
                        dukeMenu.showErrorMessage();
                    }
                }else{
                    dukeMenu.showErrorMessage();
                }
                break;
            case "bye":
                dukeMenu.quit();
                break;
            default:
                dukeMenu.addTask(userInput);
                break;
            }
        }
    }
}
