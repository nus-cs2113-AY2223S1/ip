import java.util.Scanner;
import java.util.Vector;

public class Duke {
    public class Task {
        protected String description;
        protected boolean isDone;
    
        public Task(String description) { // initiating an instance
            this.description = description;
            this.isDone = false;

        }
    
        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }
    
        public void markAsDone(){
            this.isDone = true;
        }

        public void markAsNotDone(){
            this.isDone = false;
        }

        public String getDescription(){
            return this.description;
        }
    }

    // Driver code
    public static void main(String[] args) {
        // Greeting message
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke_HTT. \n What can I do for you?");
        
        // Take user input
        Scanner scan = new Scanner ( System.in ); 
        String inData = scan.nextLine();

        // Processing input - 1st time
        Vector<Task> tasks = new Vector<>();
        
        if (inData.equals("bye")){
            System.out.println(" \n Bye. Hope to see you again soon!");
        } else if (inData.equals("list")){
            for (Task task:tasks){
                System.out.println("Here are the tasks in your list: \n" 
                                    + (tasks.indexOf(task) + 1) 
                                    + "." + "[" + task.getStatusIcon() + "] " 
                                    + task.getDescription());   
            }
        } else{
            Task t = new Task(inData);
            tasks.add(t);
            System.out.println("added: " + t.getDescription());
        }

        // Processing input - loop
        Boolean isBye = false;

        while (!isBye) {

            inData = scan.nextLine();
            
            if (inData.equals("bye")){
                isBye = true;
                System.out.println(" \n Bye. Hope to see you again soon!");
            } else if (inData.equals("list")){
                for (Task task:tasks){
                    System.out.println("Here are the tasks in your list: \n" 
                                        + (tasks.indexOf(task) + 1) 
                                        + "." + "[" + task.getStatusIcon() + "] " 
                                        + task.getDescription());
                }
            } else if (inData.contains("mark") && !inData.contains("unmark")){
                int inDataIndex = Integer.parseInt(inData.substring(5));
                inDataIndex--;
                tasks.get(inDataIndex).markAsDone();
                Task task = tasks.get(inDataIndex);
                System.out.println("Nice! I've marked this task as done: \n" + "[" 
                + task.getStatusIcon() + "] " + task.getDescription());
            } else if (inData.contains("unmark")){
                int inDataIndex = Integer.parseInt(inData.substring(7));
                inDataIndex--;
                tasks.get(inDataIndex).markAsNotDone();
                Task task = tasks.get(inDataIndex);
                System.out.println("OK, I've marked this task as not done yet: \n" + "[" 
                + task.getStatusIcon() + "] " + task.getDescription());
            } else{
                Task t = new Task(inData);
                tasks.add(t);
                System.out.println("added: " + t.getDescription());
            }
        }  
    }
}
