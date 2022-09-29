
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;


public class Duke {
    static String inputText = "";
    static ArrayList<Task> tasks = new ArrayList<>();
    static ArrayList<String> storedTasks = new ArrayList<>();
    static int taskCount = 0;
    static boolean isRunning = true;
    public static void main(String[] args) throws NullCommandException, IOException {
        File f =  new File("src/main/java/data.txt");
        if(f.exists()) {
            Scanner s = new Scanner(f);
            while(s.hasNext()) {
                storedTasks.add(s.nextLine());
            }
            handleStoredTasks();
        }
            FileWriter fw = new FileWriter("src/main/java/data.txt");
            UI.welcomeMessage();
            handleUserInput();
        for (Task task : tasks) {
            fw.write(task.toString() + "\n");
        }
            fw.close();
    }

    public static void handleStoredTasks(){
        TaskList.handleStoredTasks(storedTasks,tasks);
    }

    public static void handleListMessage(){
        UI.listMessage(tasks);
    }
    
    public static void addNewTask(String command) {
        TaskList.addTask(command, tasks, inputText);
    }
    public static void handleDeleteCommand(int position) {
        TaskList.deleteTask(position,tasks);
    }
    
    public static void handleMarkMessage(){
        int position = Parser.getPositionFromInput(inputText);
        if ((inputText.substring(0,4).equals("mark"))){
            TaskList.markTask(position, tasks);
        }else{
            TaskList.unmarkTask(position, tasks);
        }
    }
    public static void handleUserInput() {
            while(isRunning){
                    inputText = UI.getInput();
                    String command = Parser.parseCommand(inputText);
                   if(command.equals("bye")) {
                        isRunning = false;
                        UI.exitMessage();
                    } else if (command.equals("list")) {
                        handleListMessage();
                    }else if ((command.equals("mark")) || command.equals("unmark") ) {
                        handleMarkMessage();
                    }else if(command.equals("delete")){
                        handleDeleteCommand(Parser.getDeletePosition(inputText));
                    }
                    else{
                        addNewTask(Parser.getTaskType(inputText));
                    }
            }
    }
}
