/**
 * Main Duke class
 * runs the main operations of the Application
 */

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
        File f =  new File("data.txt");
        if(f.exists()) {
            Scanner s = new Scanner(f);
            while(s.hasNext()) {
                storedTasks.add(s.nextLine());
            }
            handleStoredTasks();
        }
            FileWriter fw = new FileWriter("data.txt");
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

    /**
     * Adds new task to collection based on command type.
     * @param command which specifies whether it is todo, deadline, or an event
     */
    public static void addNewTask(String command) {
        TaskList.addTask(command, tasks, inputText);
    }

    /**
     * Deletes task from the collection at the position specified by user
     * @param position specifies which task number to delete
     */
    public static void handleDeleteCommand(int position) {
        TaskList.deleteTask(position,tasks);
    }

    /**
     * handles any command from user to mark or unmark a task
     * @param position specifies which task in the collection to mark
     */
    public static void handleMarkMessage(int position){

        if ((inputText.substring(0,4).equals("mark"))){
            TaskList.markTask(position, tasks);
        }else{
            TaskList.unmarkTask(position, tasks);
        }
    }

    /**
     * handles a command from user to search for all tasks with a specific word
     * @param word specifies which word to search for in collection
     */
      public static void handleFindMessage(String word){
        TaskList.findTask(tasks,word);
    }

    /**
     * Runs the program, takes input from user and carries out all operations till the user enters bye
     * when user enters bye, program is closed
     * based on the type of command entered by the user, different functions are called.
     */
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
                       int position = Parser.getPositionFromInput(inputText);
                        handleMarkMessage(position);
                    }else if(command.equals("delete")){
                        handleDeleteCommand(Parser.getDeletePosition(inputText));
                    } else if (command.equals("find")) {
                       String word = Parser.parseFindCommand(inputText);
                       handleFindMessage(word);
                   } else{
                        addNewTask(Parser.getTaskType(inputText));
                    }
            }
    }
}
