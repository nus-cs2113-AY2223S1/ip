public class Menu {
    private Task[] tasks;
    private int taskCount;

    public Menu() {
        this.tasks = new Task[100];
        this.taskCount = 0;
    }

    public static void greet(){
        String output = "____________________________________________________________\n"
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + "____________________________________________________________";
        System.out.println(output);
    }

    public void addTask(String userInput){
        this.tasks[this.taskCount] = new Task(userInput);
        this.taskCount++;
        String output = "____________________________________________________________\n"
                + "added: " + userInput + "\n"
                + "____________________________________________________________";
        System.out.println(output);
    }

    public void list(){
        String output = "____________________________________________________________\n"
                + "Here are the tasks in your list:\n";
        for(int i=0; i<this.taskCount; i++){
            output += String.format("%d.[%s] %s\n", i+1, this.tasks[i].getStatusIcon(), this.tasks[i].getTaskName());
        }
        output += "____________________________________________________________";
        System.out.println(output);
    }

    public void mark(int taskIndex){
        if(taskIndex > 0 && taskIndex <= this.taskCount){
            this.tasks[taskIndex-1].setDone(true);
            String output = "____________________________________________________________\n"
                    + "Nice! I've marked this task as done:\n"
                    + "\t[X] " + this.tasks[taskIndex-1].getTaskName() + "\n"
                    + "____________________________________________________________";
            System.out.println(output);
        }else{
            this.showErrorMessage();
        }

    }

    public void unmark(int taskIndex){
        if(taskIndex > 0 && taskIndex <= this.taskCount) {
            this.tasks[taskIndex - 1].setDone(false);
            String output = "____________________________________________________________\n"
                    + "OK, I've marked this task as not done yet:\n"
                    + "[ ] " + this.tasks[taskIndex - 1].getTaskName() + "\n"
                    + "____________________________________________________________";
            System.out.println(output);
        }else{
            this.showErrorMessage();
        }
    }

    public static void quit(){
        String output = "____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________";
        System.out.println(output);
    }

    public static void showErrorMessage(){
        String output = "____________________________________________________________\n"
                + "Invalid input!\n"
                + "____________________________________________________________";
        System.out.println(output);
    }
}
