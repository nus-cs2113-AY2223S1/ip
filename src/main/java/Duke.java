import java.util.Scanner;
import java.util.Arrays;

public class Duke {

    /**
     * Returns a String array of saveData cutting off
     * the NULL elements that have not been filled
     *
     * @param dataToStore A String array that stores the data
     * @param userInput The input of the user
     * @param indexData To index the element of the String array dataToStore
     * @return A String array of saveData
     */
    public static String[] saveData(String[] dataToStore, String userInput, int indexData){
        dataToStore[indexData] = userInput;
        System.out.println("\t added: " + userInput);
        return Arrays.copyOf(dataToStore, indexData + 1);
    }

    /**
     * Prints out a list of save data from the user inputs
     *
     * @param dataOfInputs A String array of the saved data
     */
    public static void printList(String[] dataOfInputs){
        int numberOrder = 1;
        for(String data: dataOfInputs){
            System.out.println("\t " + numberOrder + ". " + data);
            numberOrder++;
        }
    }

    public static void main(String[] args) {
        String logo = "___________.__                ___.     \n"
                + "\\_   _____/|  | _____    _____|  |__  \n"
                + " |    __)  |  | \\__  \\  /  ___/  |  \\ \n"
                + " |     \\   |  |__/ __ \\_\\___ \\|   Y  \\\n"
                + " \\___  /   |____(____  /____  >___|  /\n"
                + "     \\/              \\/     \\/     \\/ \n";
        System.out.println("Hello from\n" + logo);
        String lineDivider = "____________________________________________________________\n";
        System.out.println("\t" + lineDivider
                + "\t Hello! I'm Flash\n"
                + "\t What can I do for you?\n"
                + "\t" + lineDivider);

        String userInput;
        Scanner in = new Scanner(System.in);
        String[] dataToStore = new String[100];
        String[] dataList = new String[]{};
        int indexData = 0;

        do{
            //Enable user to enter text
            userInput = in.nextLine();
            System.out.print("\t" + lineDivider);

            if(!userInput.equals("bye")) {
                if(!userInput.equals("list")) {
                    dataList = saveData(dataToStore, userInput, indexData);
                    indexData++;
                }
                else{
                    printList(dataList);
                }
            }

            else{
                System.out.println("\t Bye. Hope to see you again soon!");
            }

            System.out.println("\t" + lineDivider);
        }
        while(!userInput.equals("bye"));
    }
}
