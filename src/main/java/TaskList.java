import java.util.ArrayList;

public class TaskList{
    public static final int MAX = 100;
    protected ArrayList<Task> tasks;
    protected int taskCounter = 0;

    public TaskList(int max){
        tasks = new ArrayList<>(max);
    }
    public void addTodo(String commandAction, String fullCommand) {
        String description = Parser.parseDescription(fullCommand);
        tasks.add(taskCounter, new Task(commandAction, description));
        System.out.println("Got it. I've added this task:");
        tasks.get(taskCounter).printTask();
        System.out.println("Now there are "+(taskCounter+1)+" tasks in the list.");
        taskCounter++;
    }

    public void addDeadline(String commandAction, String fullCommand) {
        String description = Parser.parseDescription(fullCommand);
        tasks.add(taskCounter, new Task(commandAction, Parser.parseDeadline(description)));
        System.out.println("Got it. I've added this task:");
        tasks.get(taskCounter).printTask();
        System.out.println("Now there are "+(taskCounter+1)+" tasks in the list.");
        taskCounter++;
    }

    public void addEvent(String commandAction, String fullCommand) {
        String description = Parser.parseDescription(fullCommand);
        tasks.add(taskCounter, new Task(commandAction, Parser.parseEvent(description)));
        System.out.println("Got it. I've added this task:");
        tasks.get(taskCounter).printTask();
        System.out.println("Now there are "+(taskCounter+1)+" tasks in the list.");
        taskCounter++;
    }

    public void printList() {
        if(taskCounter==0){
            System.out.println("There are 0 tasks in your list!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for(int i=0; i<taskCounter; i++){
                System.out.print((i+1) + ".");
                tasks.get(i).printTask();
            }
        }
    }

    public void markDone(String fullCommand) {
        String itemNumber = Parser.parseDescription(fullCommand);
        if (Integer.parseInt(itemNumber) <= taskCounter){
            tasks.get(Integer.parseInt(itemNumber) - 1).markAsDone(true);
            System.out.println("Nice! I've marked this task as done:");
            tasks.get(Integer.parseInt(itemNumber) - 1).printTask();
        } else {
            System.out.println("There is no task " + itemNumber + " in your list");
        }
    }

    public void markUndone(String fullCommand) {
        String itemNumber = Parser.parseDescription(fullCommand);
        if (Integer.parseInt(itemNumber) <= taskCounter){
            tasks.get(Integer.parseInt(itemNumber) - 1).markAsDone(false);
            System.out.println("OK, I've marked this task as not done yet:");
            tasks.get(Integer.parseInt(itemNumber) - 1).printTask();
        } else {
            System.out.println("There is no task " + itemNumber + " in your list");
        }
    }

    public void deleteTask(String fullCommand) {
        String itemNumber = Parser.parseDescription(fullCommand);
        if (Integer.parseInt(itemNumber) <= taskCounter){
            System.out.println("Noted. I've removed this task:");
            tasks.get(Integer.parseInt(itemNumber) - 1).printTask();
            tasks.remove(Integer.parseInt(itemNumber) - 1);
            taskCounter--;
        } else {
            System.out.println("There is no task " + itemNumber + " in your list");
        }

    }
}