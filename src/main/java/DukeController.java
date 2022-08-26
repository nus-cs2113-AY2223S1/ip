import java.util.ArrayList;
import java.util.Scanner;

public class DukeController {
    protected ArrayList<Task> taskList = new ArrayList<Task>(0);
    protected Scanner in = new Scanner(System.in);

    public void printTaskList(){
        for (int i = 0; i < taskList.size(); i++){
            int listIndex = i+1;
            Task taskToPrint = taskList.get(i);
            System.out.println(" "+listIndex+".["+taskToPrint.getStatusIcon()+"] "+ taskToPrint.description);
        }
        printNewLine();
    }

    public void addTask(String input){
        System.out.println(" added: " + input);
        Task newTask = new Task(input);
        taskList.add(newTask);
        printNewLine();
    }

    public String getInput(){
        String input = in.nextLine();
        printNewLine();
        return input;
    }

    public void printNewLine(){
        for (int i = 0; i < 60; i++) {
            System.out.print('_');
        }
        System.out.print('\n');
    }

    public void printWelcome(){
        printNewLine();
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        printNewLine();
    }

    public void printGoodbye(){
        System.out.println(" Bye. Hope to see you again soon!");
        printNewLine();
    }

    public void markTask(int index){
        index--;
        Task task = taskList.get(index);
        task.updateStatus(true);
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("  ["+task.getStatusIcon()+"] "+task.description);
        printNewLine();

    }

    public void unmarkTask(int index){
        index--;
        Task task = taskList.get(index);
        task.updateStatus(false);
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("  ["+task.getStatusIcon()+"] "+task.description);
        printNewLine();

    }
}
