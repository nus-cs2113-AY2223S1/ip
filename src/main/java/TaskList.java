public class TaskList{
    public static final int MAX = 100;
    protected Task[] tasks;
    protected int taskCounter = 0;

    public TaskList(int max){
        tasks = new Task[max];
    }
    public void addTodo(String commandAction, String fullCommand) {
        String description = Parser.parseDescription(fullCommand); //throw error when todo is empty
        tasks[taskCounter] = new Task(commandAction, description);
        System.out.println("Got it. I've added this task:");
        tasks[taskCounter].printTask();
        System.out.println("Now there are "+(taskCounter+1)+" tasks in the list.");
        taskCounter++;
    }

    public void addDeadline(String commandAction, String fullCommand) {
        String description = Parser.parseDescription(fullCommand); //throw error when deadline is empty, or no time attached
        tasks[taskCounter] = new Task(commandAction, description);
        System.out.println("Got it. I've added this task:");
        tasks[taskCounter].printTask();
        System.out.println("Now there are "+(taskCounter+1)+" tasks in the list.");
        taskCounter++;
    }

    public void addEvent(String commandAction, String fullCommand) {
        String description = Parser.parseDescription(fullCommand); //throw error when event is empty, or no time attached
        tasks[taskCounter] = new Task(commandAction, description);
        System.out.println("Got it. I've added this task:");
        tasks[taskCounter].printTask();
        System.out.println("Now there are "+(taskCounter+1)+" tasks in the list.");
        taskCounter++;
    }

    public void printList() {
        System.out.println("Here are the tasks in your list:");
        for(int i=0; i<taskCounter; i++){
            System.out.print((i+1) + ".");
            tasks[i].printTask();
        }
    }

    public void markDone(String fullCommand) {
        String itemNumber = Parser.parseDescription(fullCommand);
        tasks[Integer.parseInt(itemNumber)-1].markAsDone(true);
        System.out.println("Nice! I've marked this task as done:");
        tasks[Integer.parseInt(itemNumber)-1].printTask();
    }

    public void markUndone(String fullCommand) {
        String itemNumber = Parser.parseDescription(fullCommand);
        tasks[Integer.parseInt(itemNumber)-1].markAsDone(false);
        System.out.println("OK, I've marked this task as not done yet:");
        tasks[Integer.parseInt(itemNumber)-1].printTask();
    }
}